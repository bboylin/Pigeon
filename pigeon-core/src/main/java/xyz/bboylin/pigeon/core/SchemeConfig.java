package xyz.bboylin.pigeon.core;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.Map;

/**
 * Created by bboylin on 2019/2/13.
 */
public interface SchemeConfig {
    @Nullable
    List<AbstractSchemeInterceptor> getInterceptors();

    @Nullable
    Map<String, AbstractSchemeDispatcher> getDispatchers();

    boolean checkValidScheme(@NonNull SchemeEntity entity);
}
