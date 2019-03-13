package xyz.bboylin.pigeon.core;

import xyz.bboylin.generated.RouteMap;
import xyz.bboylin.pigeon.utils.OOMSoftReference;

/**
 * Created by denglin03 on 2018/10/20.
 *
 * @author denglin03
 * @since 2018/10/20
 */
class RouteMapSupplier {
    private static OOMSoftReference<RouteMap> sRouterMapSoftReference = new OOMSoftReference<>();

    public static synchronized RouteMap get() {
        if (sRouterMapSoftReference.get() == null) {
            sRouterMapSoftReference.set(new RouteMap());
        }
        return sRouterMapSoftReference.get();
    }
}
