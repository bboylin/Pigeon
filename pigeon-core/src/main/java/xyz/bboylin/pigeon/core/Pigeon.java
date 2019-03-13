package xyz.bboylin.pigeon.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import xyz.bboylin.generated.InjectorDispatcher;
import xyz.bboylin.pigeon.utils.PigeonLog;
import xyz.bboylin.pigeon.utils.Preconditions;

/**
 * Created by bboylin on 2018/10/20.
 */
public class Pigeon {
    private static final String TAG = "Pigeon";

    /**
     * 不需要返回结果的跳转
     *
     * @param postcard 承载跳转信息的实体
     */
    public static void setOff(@NonNull Postcard postcard) {
        Intent intent = new Intent(postcard.sourceCtx, postcard.destCls);
        intent.putExtras(postcard.extraBundle);
        postcard.sourceCtx.startActivity(intent);
    }

    /**
     * 需要返回结果的跳转
     *
     * @param postcard         承载跳转信息的实体
     * @param onResultListener onActivityResult的listener
     */
    public static void setOff(@NonNull Postcard postcard, @NonNull OnResultListener onResultListener) {
        Preconditions.checkArgument(postcard.sourceCtx instanceof FragmentActivity, "should use an FragmentActivity " +
                "context for result");
        Intent intent = new Intent(postcard.sourceCtx, postcard.destCls);
        intent.putExtras(postcard.extraBundle);
        FragmentActivity sourceAct = (FragmentActivity) postcard.sourceCtx;
        PigeonFragment pigeonFragment = getPigeonFragment(sourceAct.getSupportFragmentManager());
        pigeonFragment.startActivityForResult(intent, onResultListener);
    }

    /**
     * 参数注入方法，建议在BaseActivity中使用，可以自动从intent中取传的参数和赋值，一劳永逸。
     *
     * @param activity 参数注入的目标activity
     */
    public static void inject(Activity activity) {
        InjectorDispatcher.inject(activity);
    }

    public static boolean dispatch(@NonNull Context context, @NonNull String scheme) {
        Preconditions.checkNotNull(context, "dispatch context must not be null");
        Preconditions.checkNotNull(scheme, "dispatch scheme must not be null");
        PigeonLog.startDispatching(scheme);
        return SchemeManager.getInstance().dispatch(context, scheme);
    }

    public static void init(@Nullable SchemeConfig config) {
        if (config != null) {
            SchemeManager.getInstance().init(config);
        }
    }

    public static void openDebug() {
        PigeonLog.setDebug(true);
    }

    @NonNull
    private static PigeonFragment getPigeonFragment(@NonNull final FragmentManager fragmentManager) {
        PigeonFragment pigeonFragment = findPigeonFragment(fragmentManager);
        if (pigeonFragment == null) {
            pigeonFragment = new PigeonFragment();
            fragmentManager
                    .beginTransaction()
                    .add(pigeonFragment, TAG)
                    .commitNowAllowingStateLoss();
        }
        return pigeonFragment;
    }

    @Nullable
    private static PigeonFragment findPigeonFragment(@NonNull final FragmentManager fragmentManager) {
        return (PigeonFragment) fragmentManager.findFragmentByTag(TAG);
    }
}
