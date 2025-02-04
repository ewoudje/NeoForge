/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.neoforged.neoforge.event;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.Nullable;

/**
 * VanillaGameEvent is fired on the server whenever one of Vanilla's {@link GameEvent GameEvents} fire. <br>
 * <br>
 * This allows for listening to Vanilla's events in a more structured and global way that is not tied to needing a block entity listener. <br>
 * <br>
 * This event is fired on the {@link NeoForge#EVENT_BUS}. <br>
 * <br>
 * Cancel this event to prevent Vanilla from posting the {@link GameEvent} to all nearby {@link net.minecraft.world.level.gameevent.GameEventListener GameEventListeners}.
 **/
public class VanillaGameEvent extends Event implements ICancellableEvent {
    private final Level level;
    private final Holder<GameEvent> vanillaEvent;
    private final Vec3 position;
    private final GameEvent.Context context;

    public VanillaGameEvent(Level level, Holder<GameEvent> vanillaEvent, Vec3 position, GameEvent.Context context) {
        this.level = level;
        this.vanillaEvent = vanillaEvent;
        this.position = position;
        this.context = context;
    }

    /**
     * @return The level the Vanilla {@link GameEvent} occurred.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @return The entity that was the source or "cause" of the {@link GameEvent}.
     */
    @Nullable
    public Entity getCause() {
        return context.sourceEntity();
    }

    /**
     * @return The Vanilla event.
     */
    public Holder<GameEvent> getVanillaEvent() {
        return vanillaEvent;
    }

    /**
     * @return The position the event took place at.
     */
    public Vec3 getEventPosition() {
        return position;
    }

    /**
     * @return the context of the vanilla event
     */
    public GameEvent.Context getContext() {
        return context;
    }
}
