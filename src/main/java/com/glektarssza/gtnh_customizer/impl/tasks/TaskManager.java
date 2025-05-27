package com.glektarssza.gtnh_customizer.impl.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

import com.glektarssza.gtnh_customizer.GTNHCustomizer;
import com.glektarssza.gtnh_customizer.api.tasks.ITask;
import com.glektarssza.gtnh_customizer.api.tasks.ITaskData;
import com.glektarssza.gtnh_customizer.api.tasks.ITaskManager;
import com.glektarssza.gtnh_customizer.api.tasks.TaskRunResult;
import com.glektarssza.gtnh_customizer.api.tasks.events.RenderTickTaskData;
import com.glektarssza.gtnh_customizer.utils.ImmutableTuple;

/**
 * A class which manages {@link ITask} instances as well as subscribing to Forge
 * events in order to execute tasks that are triggered by them.
 */
public class TaskManager implements ITaskManager {
    /**
     * The maximum number of times a task can request immediate reruns.
     */
    private static final int MAX_IMMEDIATE_RERUNS = 100;

    /**
     * The maximum number of times a task can be caught being naughty.
     */
    private static final int MAX_ALLOWED_NAUGHTINESS = 3;

    /**
     * The list of tasks that this instance is managing.
     */
    private final Map<UUID, ITask> tasks = new HashMap<UUID, ITask>();

    /**
     * A list of UUIDs for tasks that are being naughty against how naughty they
     * are being.
     *
     * Tasks that are too naughty will be removed from the task manager.
     *
     * Things that are considered naughty:
     * <ul>
     * <li>Requesting immediate reruns repeatedly and excessively during the
     * same pumping of the event loop.</li>
     * <li>Failing to initialize repeatedly across multiple pumps of the event
     * loop.</li>
     * </ul>
     */
    private final Map<UUID, Integer> naughtyList = new HashMap<UUID, Integer>();

    /**
     * Create a new instance.
     */
    public TaskManager() {
        // -- Purposefully empty
    }

    /**
     * Register a task to be executed.
     *
     * @param task The task to register.
     */
    @Override
    @Nonnull
    public UUID registerTask(@Nonnull ITask task) {
        UUID id = getAvailableId();
        tasks.put(id, task);
        task.setTaskManager(this);
        return id;
    }

    /**
     * Unregister a task from being executed.
     *
     * @param task The task to unregister.
     */
    @Override
    public void unregisterTask(@Nonnull ITask task)
        throws NoSuchElementException {
        UUID id = tryGetTaskId(task);
        if (id == null) {
            throw new NoSuchElementException(
                String.format("No task with ID '%s' is registered",
                    task.getId()));
        }
        tasks.remove(id);
        task.setTaskManager(null);
    }

    /**
     * Unregister a task from being executed.
     *
     * @param task The task to unregister.
     *
     * @return The ID of the unregistered task or {@code null} if the given task
     *         was not registered.
     */
    @Override
    @Nullable
    public UUID tryUnregisterTask(@Nonnull ITask task) {
        UUID id = tryGetTaskId(task);
        if (id == null) {
            return null;
        }
        tasks.remove(id);
        task.setTaskManager(null);
        return id;
    }

    /**
     * Get the ID of the given task.
     *
     * @param task The task to get the ID of.
     *
     * @return The ID of the given task.
     *
     * @throws NoSuchElementException If the given task is not registered.
     */
    @Override
    @Nonnull
    public UUID getTaskId(@Nonnull ITask task) throws NoSuchElementException {
        return tasks.entrySet().stream()
            .filter(entry -> entry.getValue().equals(task))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException(
                String.format("No task with ID '%s' is registered",
                    task.getId())))
            .getKey();
    }

    /**
     * Try to get the ID of the given task.
     *
     * @param task The task to get the ID of.
     *
     * @return The ID of the given task or {@code null} if the given task is not
     *         registered.
     */
    @Override
    @Nullable
    public UUID tryGetTaskId(@Nonnull ITask task) {
        return tasks.entrySet().stream()
            .filter(entry -> entry.getValue().equals(task))
            .findFirst()
            .map(Map.Entry::getKey)
            .orElse(null);
    }

    /**
     * Get the task with the given ID.
     *
     * @param id The ID of the task to get.
     *
     * @return The task with the given ID.
     *
     * @throws NoSuchElementException If no task with the given ID exists.
     */
    @Override
    @Nonnull
    public ITask getTask(@Nonnull UUID id) throws NoSuchElementException {
        ITask result = this.tryGetTask(id);
        if (result == null) {
            throw new NoSuchElementException(
                String.format("No task with ID '%s' is registered", id));
        }
        return result;
    }

    /**
     * Try to get the task with the given ID.
     *
     * @param id The ID of the task to get.
     *
     * @return The task with the given ID or {@code null} if no such task
     *         exists.
     */
    @Override
    @Nullable
    public ITask tryGetTask(@Nonnull UUID id) {
        return tasks.getOrDefault(id, null);
    }

    /**
     * Get the next available ID for a task.
     *
     * @return The next available ID for a task.
     *
     * @throws IllegalStateException If the method fails to generate a unique ID
     *         after multiple attempts.
     */
    private UUID getAvailableId() throws IllegalStateException {
        int tries = 0;
        UUID id;
        do {
            id = UUID.randomUUID();
            tries += 1;
        } while (tries < 100 && tasks.keySet().contains(id));
        if (tries >= 100) {
            throw new IllegalStateException(
                "Failed to generate a unique ID for the task after 100 attempts");
        }
        return id;
    }

    private void pumpEventLoop(ITaskData data) {
        this.tasks.values().stream()
            // -- Filter out tasks that don't accept the given task data
            .filter((task) -> task.acceptsTaskData(data))
            // -- Filter out tasks that are already initialized
            .filter((task) -> !task.isInitialized())
            // -- Initialize the task and return a tuple of the task and whether
            // -- the initialization was successful
            .map((task) -> {
                return new ImmutableTuple<ITask, Boolean>(task,
                    task.initialize());
            })
            // -- Filter out tasks that successfully initialized
            .filter((tuple) -> !tuple.getSecond())
            // -- Get the tasks that failed to initialize
            .map(ImmutableTuple::getFirst)
            // -- Unregister the tasks that failed to initialize so we don't
            // -- keep trying to initialize them again next time
            .forEach((task) -> {
                GTNHCustomizer.LOGGER.warn(
                    "Task '{}' failed to initialize, naughty!", task.getId());
                this.naughtyList.merge(task.getId(), 1, Integer::sum);
            });
        try {
            this.tasks.values().stream()
                // -- Filter out tasks that don't accept the given task data
                .filter((task) -> task.acceptsTaskData(data))
                // -- Filter out tasks that are not running or not ready to run
                .filter((task) -> task.isInitialized() || task.isRunning())
                // -- Run tasks with the given task data
                .forEach((task) -> {
                    int rerunCount = 0;
                    TaskRunResult result = task.run(data);
                    while (result.isRerunImmediate
                        && rerunCount < MAX_IMMEDIATE_RERUNS) {
                        result = task.run(data);
                        rerunCount += 1;
                    }
                    if (rerunCount >= MAX_IMMEDIATE_RERUNS) {
                        GTNHCustomizer.LOGGER.warn(
                            "Task '{}' requested to be rerun immediately too many times, naughty!",
                            task.getId());
                        this.naughtyList.merge(task.getId(), 1, Integer::sum);
                    }
                });
        } catch (Throwable t) {
            GTNHCustomizer.LOGGER.warn(
                "Exception while pumping the task manager event loop!\n{}", t);
        }
        this.tasks.values().stream()
            // -- Filter out tasks that don't accept the given task data
            .filter((task) -> task.acceptsTaskData(data))
            // -- Filter out tasks that are not finished
            .filter(ITask::isFinished)
            // -- Unregister the tasks that are finished
            .forEach(this::unregisterTask);
        List<UUID> removedNaughtyItems = this.naughtyList.entrySet().stream()
            .filter((entry) -> entry.getValue() > MAX_ALLOWED_NAUGHTINESS)
            .map((entry) -> {
                GTNHCustomizer.LOGGER.warn(
                    "Task '{}' is too naughty, removing it from the task manager",
                    entry.getKey());
                ITask task = this.tryGetTask(entry.getKey());
                if (task != null) {
                    this.tryUnregisterTask(task);
                } else {
                    GTNHCustomizer.LOGGER.warn(
                        "Task '{}' was not found in the task manager? Hmmm...",
                        entry.getKey());
                }
                return entry.getKey();
            }).collect(Collectors.toList());
        removedNaughtyItems.forEach(this.naughtyList::remove);
    }

    @SubscribeEvent
    public void onRenderTick(RenderTickEvent event) {
        ITaskData data = new RenderTickTaskData(event);
        this.pumpEventLoop(data);
    }
}
