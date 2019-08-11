package xyz.bboylin.pigeon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by bboylin on 2019/2/14.
 */
@Target(ElementType.METHOD)
public @interface Inject {
    boolean singleton() default false;
}
