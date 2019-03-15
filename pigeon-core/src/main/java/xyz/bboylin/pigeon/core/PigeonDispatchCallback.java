package xyz.bboylin.pigeon.core;

public interface PigeonDispatchCallback {
    void onSuccess(String data);

    void onFail(String data);
}
