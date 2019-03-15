package xyz.bboylin.anothersamplelib.scheme;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import xyz.bboylin.pigeon.annotation.Dispatcher;
import xyz.bboylin.pigeon.core.AbstractSchemeDispatcher;
import xyz.bboylin.pigeon.core.PigeonDispatchCallback;
import xyz.bboylin.pigeon.core.Query;

/**
 * Created by bboylin on 2019/2/28.
 */
@Dispatcher(path = "browser/loadUrl")
public class WebDispatcher extends AbstractSchemeDispatcher {
    private static final String TAG = "WebDispatcher";

    @Override
    public boolean invoke(@NonNull Context context, @NonNull Query query, @Nullable PigeonDispatchCallback callback) {
        String params = query.getParam("params");
        String from = query.getParam("from");
        try {
            JSONObject paramsJson = new JSONObject(params);
            String url = paramsJson.optString("url");
            Uri uri = Uri.parse((url));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(intent);
            Log.d(TAG, String.format("from : %s, load url: %s", from, url));
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
