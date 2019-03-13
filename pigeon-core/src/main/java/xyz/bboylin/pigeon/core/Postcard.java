package xyz.bboylin.pigeon.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * 承载跳转信息的实体类
 *
 * @author bboylin
 * @since 2018/10/20
 */
public class Postcard {
    @NonNull
    public Context sourceCtx;
    @NonNull
    public Class destCls;
    @Nullable
    public Bundle extraBundle;

    Postcard(@NonNull Context sourceCtx, @NonNull Class destCls, @Nullable Bundle extraBundle) {
        this.destCls = destCls;
        this.extraBundle = extraBundle;
        this.sourceCtx = sourceCtx;
    }
}
