package com.glektarssza.gtnh_customizer.api.v1.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that indicates the annotated thing, and all parameters
 * underneath it, will not be {@code null} by default.
 *
 * @deprecated Use
 *             {@link com.glektarssza.gtnh_customizer.api.v2.annotation.ParametersNotNullByDefault}
 *             instead.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@Deprecated
public @interface ParametersNotNullByDefault {}
