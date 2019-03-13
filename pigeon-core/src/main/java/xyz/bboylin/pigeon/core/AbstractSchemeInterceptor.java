package xyz.bboylin.pigeon.core;

import android.content.Context;

/**
 * Created by bboylin on 2019/2/13.
 */
public abstract class AbstractSchemeInterceptor {
    public abstract String getName();

    public abstract boolean shouldInterceptSchemeDispatch(Context context, String scheme);
}
