package com.glektarssza.gtnh_customizer.api.v1.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation that indicates the annotated thing cannot be {@code null}.
 *
 * @deprecated Use
 *             {@link com.glektarssza.gtnh_customizer.api.v2.annotation.NotNull}
 *             instead.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@Deprecated
public @interface NotNull {}
