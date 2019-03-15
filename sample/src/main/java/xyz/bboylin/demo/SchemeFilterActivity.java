package xyz.bboylin.demo;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import xyz.bboylin.pigeon.core.Pigeon;
import xyz.bboylin.pigeon.core.PigeonDispatchCallback;
import xyz.bboylin.pigeon.core.Postcard;
import xyz.bboylin.pigeon.core.PostcardBuilder;
import xyz.bboylin.universialtoast.UniversalToast;

public class SchemeFilterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();
        String standardUri = Uri.decode(uri.toString().replace("pigeon://bboylin.xyz/", ""));
        boolean handled = Pigeon.dispatch(SchemeFilterActivity.this, standardUri, new PigeonDispatchCallback() {
            @Override
            public void onSuccess(String data) {
                finish();
            }

            @Override
            public void onFail(String data) {
                UniversalToast.makeText(DemoApplication.getInstance(), "internal error", UniversalToast.LENGTH_SHORT).showError();
            }
        });
        if (!handled) {
            Postcard postcard = PostcardBuilder.newInstance(SchemeFilterActivity.this, uri.toString()).build();
            Pigeon.setOff(postcard);
            finish();
        }
    }
}
