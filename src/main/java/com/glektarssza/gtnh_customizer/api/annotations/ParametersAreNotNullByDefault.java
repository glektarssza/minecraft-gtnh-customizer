package com.glektarssza.gtnh_customizer.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.annotation.meta.TypeQualifierDefault;

/**
 * An annotation for scopes that indicates that parameters are not
 * <code>null</code> by default.
 */
@SuppressWarnings("null")
@Documented
@NotNull
@TypeQualifierDefault(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParametersAreNotNullByDefault {}
