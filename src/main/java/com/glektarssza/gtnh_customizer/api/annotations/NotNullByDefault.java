package com.glektarssza.gtnh_customizer.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation for scopes that indicates that parameters are not
 * <code>null</code> by default.
 */
@Documented
@Target({
    ElementType.PACKAGE,
    ElementType.TYPE,
    ElementType.METHOD,
    ElementType.CONSTRUCTOR
})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullByDefault {}
