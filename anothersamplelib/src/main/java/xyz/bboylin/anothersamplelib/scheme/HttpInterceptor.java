package xyz.bboylin.anothersamplelib.scheme;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import xyz.bboylin.anothersamplelib.AppRuntimeProvider;
import xyz.bboylin.anothersamplelib.IAppRuntime;
import xyz.bboylin.pigeon.annotation.Interceptor;
import xyz.bboylin.pigeon.core.AbstractSchemeInterceptor;
import xyz.bboylin.pigeon.core.SchemeEntity;

/**
 * Created by bboylin on 2019/2/28.
 */
@Interceptor
public class HttpInterceptor extends AbstractSchemeInterceptor {

    @Override
    public String getName() {
        return "HttpInterceptor";
    }

    @Override
    public boolean shouldInterceptSchemeDispatch(Context context, String scheme) {
        Log.d(getName(), "shouldInterceptSchemeDispatch");
        SchemeEntity entity;
        if (TextUtils.isEmpty(scheme) || (entity = SchemeEntity.parse(scheme)) == null) {
            return false;
        }
        if (entity.query != null) {
            String params = entity.query.getParam("params");
            try {
                String url = new JSONObject(params).optString("url");
                boolean intercepted = url.startsWith("http://");
                if (intercepted) {
                    IAppRuntime runtime = AppRuntimeProvider.get();
                    runtime.showUniversalToast(runtime.getAppContext(), "Http禁止通行");
                }
                return intercepted;
            } catch (JSONException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }
}
