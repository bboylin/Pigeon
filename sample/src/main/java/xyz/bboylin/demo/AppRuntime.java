package xyz.bboylin.demo;

import android.content.Context;

import xyz.bboylin.anothersamplelib.IAppRuntime;
import xyz.bboylin.pigeon.annotation.Provider;
import xyz.bboylin.universialtoast.UniversalToast;

/**
 * Created by bboylin on 2019/3/10.
 */
@Provider
public class AppRuntime implements IAppRuntime {
    @Override
    public Context getAppContext() {
        return DemoApplication.getInstance();
    }

    @Override
    public void showUniversalToast(Context context, String text) {
        UniversalToast.makeText(context, text, UniversalToast.LENGTH_SHORT, UniversalToast.EMPHASIZE)
                .showWarning();
    }
}
