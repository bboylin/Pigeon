package xyz.bboylin.pigeon.core;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by bboylin on 2019/2/13.
 */
public abstract class AbstractSchemeInterceptor {
    @NonNull
    public abstract String getName();

    public abstract boolean shouldInterceptSchemeDispatch(@NonNull Context context, @NonNull String scheme);
}
