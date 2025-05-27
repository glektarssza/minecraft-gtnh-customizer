package com.glektarssza.gtnh_customizer.api.tasks.events;

import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

import com.glektarssza.gtnh_customizer.api.tasks.ITaskData;

/**
 * A class that holds task data for a {@link AbstractRenderTickTask}.
 */
public class RenderTickTaskData implements ITaskData {
    /**
     * The render tick event.
     */
    public final RenderTickEvent event;

    /**
     * Create a new instance.
     *
     * @param event The render tick event.
     */
    public RenderTickTaskData(RenderTickEvent event) {
        this.event = event;
    }
}
