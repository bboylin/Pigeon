package xyz.bboylin.pigeon.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by bboylin on 2019/2/13.
 */
public abstract class AbstractSchemeDispatcher {
    public abstract boolean invoke(@NonNull Context context, @NonNull Query query, @Nullable PigeonDispatchCallback callback);
}
