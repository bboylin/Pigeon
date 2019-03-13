package xyz.bboylin.anothersamplelib;

import android.content.Context;

/**
 * Created by bboylin on 2019/3/10.
 */
public interface IAppRuntime {
    Context getAppContext();

    void showUniversalToast(Context context, String text);
}
