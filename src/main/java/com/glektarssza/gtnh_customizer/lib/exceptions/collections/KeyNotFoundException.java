package com.glektarssza.gtnh_customizer.lib.exceptions.collections;

import javax.annotation.Nullable;

import com.glektarssza.gtnh_customizer.lib.exceptions.BaseRuntimeException;

/**
 * An exception that is thrown when a key in a collection cannot be found.
 */
public class KeyNotFoundException extends BaseRuntimeException {
    /**
     * The name of the key which could not be found.
     */
    @Nullable
    public final String keyName;

    /**
     * Create a new instance.
     */
    public KeyNotFoundException() {
        super();
        this.keyName = null;
    }

    /**
     * Create a new instance.
     *
     * @param keyName The name of the key which could not be found.
     */
    public KeyNotFoundException(@Nullable String keyName) {
        super();
        this.keyName = keyName;
    }

    /**
     * Create a new instance.
     *
     * @param keyName The name of the key which could not be found.
     * @param msg The message describing the nature of error that caused the new
     *        instance to be created.
     */
    public KeyNotFoundException(@Nullable String keyName,
        @Nullable String msg) {
        super(msg);
        this.keyName = keyName;
    }

    /**
     * Create a new instance.
     *
     * @param keyName The name of the key which could not be found.
     * @param cause The throwable that caused the new instance to be created.
     */
    public KeyNotFoundException(@Nullable String keyName,
        @Nullable Throwable cause) {
        super(cause);
        this.keyName = keyName;
    }

    /**
     * Create a new instance.
     *
     * @param keyName The name of the key which could not be found.
     * @param msg The message describing the nature of error that caused the new
     *        instance to be created.
     * @param cause The throwable that caused the new instance to be created.
     */
    public KeyNotFoundException(@Nullable String keyName, @Nullable String msg,
        @Nullable Throwable cause) {
        super(msg, cause);
        this.keyName = keyName;
    }
}
