package xyz.bboylin.pigeon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by bboylin on 2019/2/20.
 */
@Target(ElementType.TYPE)
public @interface Dispatcher {
    String path();
}
