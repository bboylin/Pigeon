package xyz.bboylin.anothersamplelib;

import xyz.bboylin.pigeon.annotation.Autowired;
import xyz.bboylin.pigeon.annotation.Inject;

/**
 * Created by bboylin on 2019/3/10.
 */
@Autowired
public class AppRuntimeProvider {
    @Inject(target = "xyz.bboylin.demo.AppRuntime", singleton = true)
    public static IAppRuntime get() {
        return null;
    }
}
