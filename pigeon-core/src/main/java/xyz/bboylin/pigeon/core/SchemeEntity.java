package xyz.bboylin.pigeon.core;

import android.support.annotation.NonNull;

/**
 * Created by bboylin on 2019/2/13.
 */
public class SchemeEntity {
    public String schemeHead;
    public int version;
    public String path;
    public Query query;

    private SchemeEntity(String schemeHead, int version, String path, Query query) {
        this.schemeHead = schemeHead;
        this.version = version;
        this.path = path;
        this.query = query;
    }

    public static SchemeEntity parse(@NonNull String scheme) {
        int indexEndOfHead = scheme.indexOf("://");
        if (indexEndOfHead < 0) {
            return null;
        }
        String schemeHead = scheme.substring(0, indexEndOfHead);
        String schemeWithoutHead = scheme.substring(indexEndOfHead + 3);
        int indexEndOfVersion = schemeWithoutHead.indexOf("/");
        if (indexEndOfVersion < 0) {
            return null;
        }
        String versionStr = schemeWithoutHead.substring(0, indexEndOfVersion);
        if (!versionStr.toLowerCase().matches("v\\d+")) {
            return null;
        }
        int version = Integer.parseInt(versionStr.substring(1));
        String schemeWithoutVersion = schemeWithoutHead.substring(indexEndOfVersion + 1);
        int indexEndOfPath = schemeWithoutVersion.indexOf("?");
        String path = schemeWithoutVersion.substring(0, indexEndOfPath);
        String query = schemeWithoutVersion.substring(indexEndOfPath + 1);
        return new SchemeEntity(schemeHead, version, path, new Query(query));
    }
}
