package xyz.bboylin.anothersamplelib.scheme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import xyz.bboylin.pigeon.annotation.Interceptor;
import xyz.bboylin.pigeon.core.AbstractSchemeInterceptor;

/**
 * Created by bboylin on 2019/3/10.
 */
@Interceptor(index = 0)
public class DemoInterceptor extends AbstractSchemeInterceptor {
    @NonNull
    @Override
    public String getName() {
        return "DemoInterceptor";
    }

    @Override
    public boolean shouldInterceptSchemeDispatch(@NonNull Context context, @NonNull String scheme) {
        Log.d(getName(), "shouldInterceptSchemeDispatch");
        return false;
    }
}
