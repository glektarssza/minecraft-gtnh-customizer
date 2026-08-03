package com.glektarssza.gtnh_customizer.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * An annotation that indicates the all parameters in the annotated package or
 * class are not {@code null} by default.
 */
@Target({
    ElementType.PACKAGE,
    ElementType.TYPE
})
public @interface ParametersNotNullByDefault {

}
