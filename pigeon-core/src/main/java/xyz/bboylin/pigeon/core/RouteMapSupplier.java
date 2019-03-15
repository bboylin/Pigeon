package xyz.bboylin.pigeon.core;

import xyz.bboylin.generated.RouteMap;
import xyz.bboylin.pigeon.utils.OOMSoftReference;

/**
 * Created by bboylin on 2018/10/20.
 *
 * @author bboylin
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
