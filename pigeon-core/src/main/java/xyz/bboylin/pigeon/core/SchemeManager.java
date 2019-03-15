package xyz.bboylin.pigeon.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import xyz.bboylin.pigeon.utils.PigeonLog;

/**
 * Created by bboylin on 2019/2/13.
 */
public class SchemeManager {
    private static final String TAG = "SchemeManager";
    private static volatile SchemeManager sManager;
    private HashMap<String, AbstractSchemeDispatcher> mSchemeDispatchers = new HashMap<>();
    private InterceptorChain mInterceptorChain = new InterceptorChain();
    private SchemeConfig mSchemeConfig;
    private boolean hasInitialized = false;

    private SchemeManager() {
    }

    static SchemeManager getInstance() {
        if (sManager == null) {
            synchronized (SchemeManager.class) {
                if (sManager == null) {
                    sManager = new SchemeManager();
                }
            }
        }
        return sManager;
    }

    private void addDispatcher(String path, AbstractSchemeDispatcher dispatcher) {
        PigeonLog.d(TAG, "addDispatcher , path : " + path);
        mSchemeDispatchers.put(path, dispatcher);
    }

    private void addDispatchers(@Nullable Map<String, AbstractSchemeDispatcher> dispatcherMap) {
        if (dispatcherMap == null) {
            return;
        }
        for (Map.Entry<String, AbstractSchemeDispatcher> entry : dispatcherMap.entrySet()) {
            if (entry != null) {
                addDispatcher(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 分发scheme
     *
     * @param context  调用源context
     * @param uri      scheme
     * @param callback dispatch callback
     * @return 是否能正常分发
     */
    public boolean dispatch(Context context, String uri, PigeonDispatchCallback callback) {
        if (!hasInitialized) {
            throw new RuntimeException("pigeon has not been initialized yet !");
        }
        if (mInterceptorChain.shouldInterceptSchemeDispatch(context, uri)) {
            PigeonLog.d(TAG, "scheme intercepted:" + uri);
            return true;
        }
        SchemeEntity entity = SchemeEntity.parse(uri);
        if (entity == null) {
            PigeonLog.eWithStackTrace(TAG, "error in parsing scheme to entity: " + uri);
            return false;
        }
        if (mSchemeConfig != null && !mSchemeConfig.checkValidScheme(entity)) {
            PigeonLog.eWithStackTrace(TAG, "error, invalid scheme entity");
            return false;
        }
        AbstractSchemeDispatcher dispatcher = mSchemeDispatchers.get(entity.path);
        if (dispatcher == null) {
            PigeonLog.eWithStackTrace(TAG, "error, cannot find dispatcher for:" + entity.path);
            return false;
        }
        PigeonLog.endDispatching(uri);
        return dispatcher.invoke(context, entity.query, callback);
    }

    public void init(@Nullable SchemeConfig config) {
        if (hasInitialized) {
            if (PigeonLog.isDebug()) {
                throw new RuntimeException("pigeon has already been initialized");
            }
            return;
        }
        hasInitialized = true;
        if (config != null) {
            mSchemeConfig = config;
            mInterceptorChain.addInterceptors(config.getInterceptors());
            addDispatchers(config.getDispatchers());
        }
    }
}
