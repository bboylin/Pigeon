package xyz.bboylin.anothersamplelib;

import android.content.Context;

import xyz.bboylin.pigeon.annotation.Autowired;
import xyz.bboylin.pigeon.annotation.Inject;

/**
 * Created by bboylin on 2019/3/10.
 */
@Autowired
public class AppRuntimeProvider {
    @Inject(singleton = true)
    public static IAppRuntime get() {
        return null;
    }

//    @Inject
//    public static Context noImpl() {
//        return null;
//    }
}
