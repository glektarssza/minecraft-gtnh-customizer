package com.glektarssza.gtnh_customizer.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.annotation.meta.TypeQualifierNickname;
import javax.annotation.meta.When;

/**
 * An annotation for things that might be <code>null</code>.
 */
@SuppressWarnings("null")
@Documented
@NotNull(when = When.UNKNOWN)
@TypeQualifierNickname
@Retention(RetentionPolicy.RUNTIME)
public @interface Nullable {}
