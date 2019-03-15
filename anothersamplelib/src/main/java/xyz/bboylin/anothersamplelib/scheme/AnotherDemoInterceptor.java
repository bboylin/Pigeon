package xyz.bboylin.anothersamplelib.scheme;

import android.content.Context;
import android.util.Log;

import xyz.bboylin.pigeon.annotation.Interceptor;
import xyz.bboylin.pigeon.core.AbstractSchemeInterceptor;

/**
 * Created by bboylin on 2019/3/10.
 */
@Interceptor(index = 1)
public class AnotherDemoInterceptor extends AbstractSchemeInterceptor {
    @Override
    public String getName() {
        return "AnotherDemoInterceptor";
    }

    @Override
    public boolean shouldInterceptSchemeDispatch(Context context, String scheme) {
        Log.d(getName(), "shouldInterceptSchemeDispatch");
        return false;
    }
}
