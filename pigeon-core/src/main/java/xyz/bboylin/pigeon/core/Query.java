package xyz.bboylin.pigeon.core;

import android.util.Log;

import java.net.URLDecoder;
import java.util.HashMap;

/**
 * Created by bboylin on 2019/2/13.
 */
public class Query {
    private static final String TAG = "pigeon";
    private HashMap<String, String> mParams = new HashMap<>();

    Query(String query) {
        parse(query);
    }

    private void parse(String query) {
        int index = query.indexOf("#");
        if (index > 0) {
            query = query.substring(0, index);
        }
        String[] kvPairs = query.split("&");
        for (String kvPair : kvPairs) {
            int indexOfEqual = kvPair.indexOf("=");
            if (indexOfEqual < 0) {
                Log.e(TAG, "skipped invalid query item :" + kvPair);
            } else {
                mParams.put(URLDecoder.decode(kvPair.substring(0, indexOfEqual)), URLDecoder.decode(
                        kvPair.substring(indexOfEqual + 1)));
            }
        }
    }

    public String getParam(String paramKey) {
        return mParams.get(paramKey);
    }
}
