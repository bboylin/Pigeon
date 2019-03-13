package xyz.bboylin.pigeon.core;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import xyz.bboylin.generated.RouteMap;
import xyz.bboylin.pigeon.utils.PigeonLog;
import xyz.bboylin.pigeon.utils.Preconditions;

/**
 * Created by bboylin on 2019/2/12.
 */
public class PostcardBuilder {
    private Context mSourceCtx;
    private String mDestUrl;
    private Bundle mExtraBundle;

    private PostcardBuilder(@NonNull Context from, @NonNull String destUrl) {
        mExtraBundle = new Bundle();
        this.mSourceCtx = Preconditions.checkNotNull(from);
        this.mDestUrl = getProcessedUrl(Preconditions.checkNotNull(destUrl));
    }

    public static PostcardBuilder newInstance(@NonNull Context from, @NonNull String destUrl) {
        return new PostcardBuilder(from, destUrl);
    }

    private String getProcessedUrl(String destUrl) {
        int indexEndOfPath = destUrl.indexOf("?");
        if (indexEndOfPath < 0) {
            return destUrl;
        }
        String url = destUrl.substring(0, indexEndOfPath);
        String query = destUrl.substring(indexEndOfPath + 1);
        String[] kvPairs = query.split("&");
        for (String kvPair : kvPairs) {
            int indexOfEqual = kvPair.indexOf("=");
            if (indexOfEqual < 0) {
                if (PigeonLog.isDebug()) {
                    Log.e("pigeon", "invalid query item :" + kvPair);
                    throw new RuntimeException("invalid url:" + destUrl);
                }
            } else {
                addExtra(kvPair.substring(0, indexOfEqual), kvPair.substring(indexOfEqual + 1));
            }
        }
        return url;
    }

    public PostcardBuilder addExtra(@NonNull String key, String value) {
        mExtraBundle.putString(key, value);
        return this;
    }

    public PostcardBuilder addExtra(@NonNull String key, int value) {
        mExtraBundle.putInt(key, value);
        return this;
    }

    public PostcardBuilder addExtra(@NonNull String key, float value) {
        mExtraBundle.putFloat(key, value);
        return this;
    }

    public PostcardBuilder addExtra(@NonNull String key, double value) {
        mExtraBundle.putDouble(key, value);
        return this;
    }

    public PostcardBuilder addExtra(@NonNull String key, long value) {
        mExtraBundle.putLong(key, value);
        return this;
    }

    public PostcardBuilder addExtra(@NonNull String key, char value) {
        mExtraBundle.putChar(key, value);
        return this;
    }

    public PostcardBuilder addExtra(@NonNull String key, short value) {
        mExtraBundle.putShort(key, value);
        return this;
    }

    public PostcardBuilder addExtra(@NonNull String key, boolean value) {
        mExtraBundle.putBoolean(key, value);
        return this;
    }

    // TODO 支持多种序列化方式
//        public Builder addExtra(@NonNull String key, Serializable value) {
//            mExtraBundle.putSerializable(key, value);
//            return this;
//        }

    public PostcardBuilder addExtra(@NonNull String key, Parcelable value) {
        mExtraBundle.putParcelable(key, value);
        return this;
    }

    public Postcard build() {
        if (mExtraBundle.isEmpty()) {
            mExtraBundle = null;
        }
        RouteMap routeMap = RouteMapSupplier.get();
        Class destCls = routeMap.get(Preconditions.checkNotNull(mDestUrl));
        Preconditions.checkNotNull(destCls, "cannot find \"%s\" in RouteMap, have u forgot to add @RouteNode" +
                "(\"%s\") in some activity ?", mDestUrl, mDestUrl);
        return new Postcard(mSourceCtx, destCls, mExtraBundle);
    }
}
