package xyz.bboylin.pigeon.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bboylin on 2019/2/13.
 */
class InterceptorChain {
    private ArrayList<AbstractSchemeInterceptor> mInterceptors = new ArrayList<>();

    public void addInterceptor(@NonNull AbstractSchemeInterceptor interceptor) {
        mInterceptors.add(interceptor);
    }

    public final void addInterceptorAtIndex(int index, @NonNull AbstractSchemeInterceptor interceptor) {
        Log.d("InterceptorChain", "addInterceptorAtIndex:" + index + ",name:" + interceptor.getName());
        if (index < 0) {
            addInterceptor(interceptor);
        } else {
            mInterceptors.add(index, interceptor);
        }
    }

    public void addInterceptors(@Nullable List<AbstractSchemeInterceptor> interceptors) {
        if (interceptors != null) {
            mInterceptors.addAll(interceptors);
        }
    }

    InterceptorChain() {
    }

    public boolean shouldInterceptSchemeDispatch(@NonNull Context context, @NonNull String scheme) {
        for (AbstractSchemeInterceptor interceptor : mInterceptors) {
            if (interceptor != null && interceptor.shouldInterceptSchemeDispatch(context, scheme)) {
                return true;
            }
        }
        return false;
    }
}
