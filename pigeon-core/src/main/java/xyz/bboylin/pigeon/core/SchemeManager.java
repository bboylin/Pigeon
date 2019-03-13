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
     * @param context 调用源context
     * @param scheme  scheme
     * @return 是否能正常分发
     */
    public boolean dispatch(Context context, String scheme) {
        if (!hasInitialized) {
            throw new RuntimeException("pigeon has not been initialized yet !");
        }
        if (mInterceptorChain.shouldInterceptSchemeDispatch(context, scheme)) {
            PigeonLog.d(TAG, "scheme intercepted:" + scheme);
            return true;
        }
        SchemeEntity entity = SchemeEntity.parse(scheme);
        if (entity == null) {
            PigeonLog.eWithStackTrace(TAG, "error in parsing scheme to entity: " + scheme);
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
        PigeonLog.endDispatching(scheme);
        return dispatcher.invoke(context, entity.query);
    }

    public void init(@NonNull SchemeConfig config) {
        if (hasInitialized) {
            if (PigeonLog.isDebug()) {
                throw new RuntimeException("pigeon has already been initialized");
            }
            return;
        }
        mSchemeConfig = config;
        hasInitialized = true;
        mInterceptorChain.addInterceptors(config.getInterceptors());
        addDispatchers(config.getDispatchers());
    }
}
