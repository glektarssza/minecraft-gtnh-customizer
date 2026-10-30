package com.glektarssza.gtnh_customizer.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.meta.TypeQualifier;
import javax.annotation.meta.When;

/**
 * An annotation for things that cannot be <code>null</code>.
 */
@Documented
@TypeQualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({
    ElementType.ANNOTATION_TYPE,
    ElementType.FIELD,
    ElementType.METHOD,
    ElementType.PARAMETER,
    ElementType.LOCAL_VARIABLE,
    ElementType.TYPE_PARAMETER,
    ElementType.TYPE_USE
})
public @interface NotNull {
    /**
     *
     * @return
     */
    public When when() default When.ALWAYS;
}
