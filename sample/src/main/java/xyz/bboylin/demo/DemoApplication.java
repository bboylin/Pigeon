package xyz.bboylin.demo;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;
import java.util.Map;

import xyz.bboylin.pigeon.core.AbstractSchemeDispatcher;
import xyz.bboylin.pigeon.core.AbstractSchemeInterceptor;
import xyz.bboylin.pigeon.core.Pigeon;
import xyz.bboylin.pigeon.core.SchemeConfig;
import xyz.bboylin.pigeon.core.SchemeEntity;

/**
 * Created by bboylin on 2019/3/1.
 */
public class DemoApplication extends Application {
    private static final int CURRENT_VERSION = 9;
    private static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        Pigeon.init(new SchemeConfig() {
            @Nullable
            @Override
            public List<AbstractSchemeInterceptor> getInterceptors() {
                return null;
            }

            @Nullable
            @Override
            public Map<String, AbstractSchemeDispatcher> getDispatchers() {
                return null;
            }

            @Override
            public boolean checkValidScheme(@NonNull SchemeEntity entity) {
                return TextUtils.equals(entity.schemeHead, "pigeon") && entity.version >= CURRENT_VERSION;
            }
        });
        Pigeon.openDebug();
        Fresco.initialize(this);
        sApplication = this;
    }

    @NonNull
    public static Application getInstance() {
        return sApplication;
    }
}
