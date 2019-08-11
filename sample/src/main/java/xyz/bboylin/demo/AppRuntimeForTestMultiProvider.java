package xyz.bboylin.demo;

import android.content.Context;

import xyz.bboylin.anothersamplelib.IAppRuntime;
import xyz.bboylin.pigeon.annotation.Provider;

//@Provider
public class AppRuntimeForTestMultiProvider implements IAppRuntime {
    @Override
    public Context getAppContext() {
        return null;
    }

    @Override
    public void showUniversalToast(Context context, String text) {

    }
}
