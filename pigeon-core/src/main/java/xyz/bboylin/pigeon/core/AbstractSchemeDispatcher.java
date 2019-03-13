package xyz.bboylin.pigeon.core;

import android.content.Context;

/**
 * Created by bboylin on 2019/2/13.
 */
public abstract class AbstractSchemeDispatcher {
    public abstract boolean invoke(Context context, Query query);
}
