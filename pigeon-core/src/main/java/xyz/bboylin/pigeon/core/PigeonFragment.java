package xyz.bboylin.pigeon.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseArray;

import xyz.bboylin.pigeon.utils.Preconditions;

/**
 * Created by bboylin on 2018/11/4.
 */
public class PigeonFragment extends Fragment {
    private int mRequestCode;
    private SparseArray<OnResultListener> mListenerSparseArray;

    public PigeonFragment() {
        mRequestCode = 0;
        mListenerSparseArray = new SparseArray<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public synchronized void startActivityForResult(Intent intent, OnResultListener onResultListener) {
        super.startActivityForResult(intent, mRequestCode);
        mListenerSparseArray.put(mRequestCode, onResultListener);
        mRequestCode++;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        OnResultListener listener = mListenerSparseArray.get(requestCode);
        Preconditions.checkArgument(listener != null, String.format("error: fail to find the OnResultListener of " +
                "%s!", getActivity()));
        listener.onResult(resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListenerSparseArray.clear();
        mListenerSparseArray = null;
    }
}