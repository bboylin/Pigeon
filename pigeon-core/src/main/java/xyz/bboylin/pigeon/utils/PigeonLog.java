package xyz.bboylin.pigeon.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.WeakHashMap;

import xyz.bboylin.pigeon.core.Pigeon;

/**
 * Created by bboylin on 2019/2/14.
 */
public class PigeonLog {
    private static final String TAG = "Pigeon";
    private static boolean sDebug = false;
    private static WeakHashMap<String, Long> sWeakHashMap = new WeakHashMap<>();

    private PigeonLog() {
    }

    public static void d(String tag, String msg) {
        if (sDebug) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (sDebug) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (sDebug) {
            Log.e(tag, msg, tr);
        }
    }

    public static void e(String tag, String msg) {
        if (sDebug) {
            Log.e(tag, msg);
        }
    }

    public static void eWithStackTrace(String tag, String msg) {
        if (sDebug) {
            Log.e(tag, Log.getStackTraceString(new Throwable(msg)));
        }
    }

    public static void setDebug(boolean debug) {
        sDebug = debug;
    }

    public static boolean isDebug() {
        return sDebug;
    }

    public static void startDispatching(@NonNull String scheme) {
        if (sDebug) {
            sWeakHashMap.put(scheme, System.currentTimeMillis());
        }
    }

    public static void endDispatching(@NonNull String scheme) {
        if (sDebug && sWeakHashMap.containsKey(scheme)) {
            long startTime = sWeakHashMap.get(scheme);
            Log.d(TAG, String.format("scheme dispatch cost %s ms", System.currentTimeMillis() - startTime));
        }
    }
}
