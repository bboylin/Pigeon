package xyz.bboylin.pigeon.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.SoftReference;

/**
 * 在OOM之前才会回收的软引用
 *
 * @author bboylin
 * @since 2018/11/15
 */
public class OOMSoftReference<T> {

    private SoftReference<T> softRef1;
    private SoftReference<T> softRef2;
    private SoftReference<T> softRef3;

    public OOMSoftReference() {
        softRef1 = null;
        softRef2 = null;
        softRef3 = null;
    }

    public void set(@NonNull T hardReference) {
        softRef1 = new SoftReference<T>(hardReference);
        softRef2 = new SoftReference<T>(hardReference);
        softRef3 = new SoftReference<T>(hardReference);
    }

    @Nullable
    public T get() {
        return (softRef1 == null ? null : softRef1.get());
    }

    public void clear() {
        if (softRef1 != null) {
            softRef1.clear();
            softRef1 = null;
        }
        if (softRef2 != null) {
            softRef2.clear();
            softRef2 = null;
        }
        if (softRef3 != null) {
            softRef3.clear();
            softRef3 = null;
        }
    }
}