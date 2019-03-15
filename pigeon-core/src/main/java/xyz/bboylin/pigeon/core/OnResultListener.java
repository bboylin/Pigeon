package xyz.bboylin.pigeon.core;

import android.content.Intent;

/**
 * Created by bboylin on 2018/10/20.
 *
 * @author bboylin
 * @since 2018/10/20
 */
public interface OnResultListener {
    void onResult(int resultCode, Intent data);
}
