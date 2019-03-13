package xyz.bboylin.demo;

import android.content.Context;

import xyz.bboylin.anothersamplelib.IAppRuntime;
import xyz.bboylin.universialtoast.UniversalToast;

/**
 * Created by denglin03 on 2019/3/10.
 */
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
