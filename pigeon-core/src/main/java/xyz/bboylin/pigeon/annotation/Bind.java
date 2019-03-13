package xyz.bboylin.pigeon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by bboylin
 */
@Target(ElementType.FIELD)
public @interface Bind {
    String value() default "";
}
