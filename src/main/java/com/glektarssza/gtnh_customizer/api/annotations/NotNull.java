package com.glektarssza.gtnh_customizer.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * An annotation that indicates the annotated element will never be
 * {@code null}.
 */
@Target({
    ElementType.TYPE_USE,
    ElementType.TYPE_PARAMETER,
    ElementType.METHOD,
    ElementType.PARAMETER,
    ElementType.FIELD,
    ElementType.LOCAL_VARIABLE
})
public @interface NotNull {}
