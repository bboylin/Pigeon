package xyz.bboylin.pigeon.core;

import java.util.List;
import java.util.Map;

/**
 * Created by bboylin on 2019/2/13.
 */
public interface SchemeConfig {
    List<AbstractSchemeInterceptor> getInterceptors();

    Map<String, AbstractSchemeDispatcher> getDispatchers();

    boolean checkValidScheme(SchemeEntity entity);
}
