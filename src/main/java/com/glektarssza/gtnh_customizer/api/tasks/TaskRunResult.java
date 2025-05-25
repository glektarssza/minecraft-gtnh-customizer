package com.glektarssza.gtnh_customizer.api.tasks;

import java.util.Optional;

import javax.annotation.Nonnull;

import com.glektarssza.gtnh_customizer.api.functional.ProcedureWithResult;

/**
 * The result of a task run.
 */
public final class TaskRunResult {
    /**
     * Create a new instance representing a successful task run that does not
     * want to be rerun.
     *
     * @param task The task this instance will represent the result of.
     */
    @Nonnull
    public static final TaskRunResult successNoRerun(@Nonnull ITask task) {
        return success(task, false, false);
    }

    /**
     * Create a new instance representing a successful task run that does want
     * to be rerun but not immediately.
     *
     * @param task The task this instance will represent the result of.
     */
    @Nonnull
    public static final TaskRunResult successRerun(@Nonnull ITask task) {
        return success(task, true, false);
    }

    /**
     * Create a new instance representing a successful task run that does want
     * to be rerun immediately.
     *
     * @param task The task this instance will represent the result of.
     */
    @Nonnull
    public static final TaskRunResult successRerunImmediate(
        @Nonnull ITask task) {
        return success(task, true, true);
    }

    /**
     * Create a new instance representing a successful task run that may want to
     * be rerun.
     *
     * @param task The task this instance will represent the result of.
     * @param wantsRerun Whether the task wants to be rerun at the next chance.
     * @param isRerunImmediate Whether the task wants its next run to be
     *        immediate.
     */
    @Nonnull
    public static final TaskRunResult success(
        @Nonnull ITask task, boolean wantsRerun, boolean isRerunImmediate) {
        return new TaskRunResult(task, true, Optional.empty(), wantsRerun,
            isRerunImmediate);
    }

    /**
     * Create a new instance representing a failed task run.
     *
     * @param task The task this instance will represent the result of.
     */
    @Nonnull
    public static final TaskRunResult failed(@Nonnull ITask task) {
        return new TaskRunResult(task, false, Optional.empty(), false, false);
    }

    /**
     * Create a new instance representing a failed task run due to an exception.
     *
     * @param task The task this instance will represent the result of.
     * @param exception The exception that was thrown during the task run.
     */
    @Nonnull
    public static final TaskRunResult failed(@Nonnull ITask task,
        @Nonnull Throwable exception) {
        return new TaskRunResult(task, false, Optional.of(exception), false,
            false);
    }

    /**
     * The task this instance is the result of.
     */
    @Nonnull
    public final ITask task;

    /**
     * Whether the task run was successful or not.
     */
    public final boolean success;

    /**
     * The exception that was thrown during the task run, if any.
     */
    @Nonnull
    public final Optional<Throwable> exception;

    /**
     * Whether the task wants to be rerun at the next chance.
     *
     * Usually the next chance is when the next trigger occurs.
     */
    public final boolean wantsRerun;

    /**
     * Whether the task wants its next run to be immediate.
     *
     * This flag is only respected if {@link #wantsRerun} is set.
     */
    public final boolean isRerunImmediate;

    /**
     * Create a new instance.
     *
     * @param task The task this instance will represent the result of.
     * @param success Whether the task run was successful or not.
     * @param exception The exception that was thrown during the task run, if
     *        any.
     * @param wantsRerun Whether the task wants to be rerun at the next chance.
     * @param isRerunImmediate Whether the task wants its next run to be
     */
    protected TaskRunResult(@Nonnull ITask task, boolean success,
        @Nonnull Optional<Throwable> exception, boolean wantsRerun,
        boolean isRerunImmediate) {
        this.task = task;
        this.success = success;
        this.exception = exception;
        this.wantsRerun = wantsRerun;
        this.isRerunImmediate = isRerunImmediate;
    }

    /**
     * Check whether this instance represents a successful task run and, if so,
     * return the provided instance.
     *
     * If this instance represents a failed task run, returns this instance.
     *
     * @param other The other instance to return if this instance represents a
     *        successful task run.
     *
     * @return The provided instance if this instance represents a successful
     *         task run, this instance otherwise.
     */
    public TaskRunResult and(@Nonnull TaskRunResult other) {
        if (this.success) {
            return other;
        }
        return this;
    }

    /**
     * Check whether this instance represents a successful task run and, if so,
     * invoke the provided function.
     *
     * If this instance represents a failed task run, returns this instance.
     *
     * @param otherFn The function to invoke if this instance represents a
     *        successful task run.
     *
     * @return The result of the function invocation if this instance represents
     *         a successful task run and the function returns a non-null value,
     *         a new instance representing a failed task run if the function
     *         returns a null value, or this instance otherwise.
     */
    @Nonnull
    public TaskRunResult andThen(
        @Nonnull ProcedureWithResult<TaskRunResult> otherFn) {
        if (this.success) {
            TaskRunResult result = otherFn.invoke();
            if (result != null) {
                return result;
            }
            return TaskRunResult.failed(this.task,
                new NullPointerException("The result of the function is null"));
        }
        return this;
    }

    /**
     * Check whether this instance represents a failed task run and, if so,
     * return the provided instance.
     *
     * If this instance represents a successful task run, returns this instance.
     *
     * @param other The other instance to return if this instance represents a
     *        failed task run.
     *
     * @return The provided instance if this instance represents a failed task
     *         run, this instance otherwise.
     */
    public TaskRunResult or(@Nonnull TaskRunResult other) {
        if (!this.success) {
            return other;
        }
        return this;
    }

    /**
     * Check whether this instance represents a failed task run and, if so,
     * invoke the provided function.
     *
     * If this instance represents a successful task run, returns this instance.
     *
     * @param otherFn The function to invoke if this instance represents a
     *        failed task run.
     *
     * @return The result of the function invocation if this instance represents
     *         a failed task run and the function returns a non-null value, a
     *         new instance representing a failed task run if the function
     *         returns a null value, or this instance otherwise.
     */
    public TaskRunResult orElse(
        @Nonnull ProcedureWithResult<TaskRunResult> otherFn) {
        if (!this.success) {
            TaskRunResult result = otherFn.invoke();
            if (result != null) {
                return result;
            }
            return TaskRunResult.failed(this.task,
                new NullPointerException("The result of the function is null"));
        }
        return this;
    }
}
