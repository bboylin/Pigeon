package xyz.bboylin.samplelib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import xyz.bboylin.pigeon.annotation.Bind;
import xyz.bboylin.pigeon.annotation.RouteNode;
import xyz.bboylin.pigeon.core.Pigeon;

@RouteNode("pigeon://sample/SampleLibActivity")
public class SampleLibActivity extends BaseActivity implements View.OnClickListener {
    private static final String SCHEME_FOR_LOAD_HTTPS =
            "pigeon://v11/browser/loadUrl?params={\"url\":\"https%3a%2f%2fbboylin.github.io%2f\"}&from=SampleLibActivity";
    private static final String SCHEME_FOR_LOAD_HTTP =
            "pigeon://v11/browser/loadUrl?params={\"url\":\"http%3a%2f%2fbboylin.github.io%2f\"}";

    @Bind("demo")
    String demoStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        TextView textView = findViewById(R.id.textView);
        textView.setText(String.format("demoStr:%s", demoStr));
        findViewById(R.id.btn_dispatcher).setOnClickListener(this);
        findViewById(R.id.btn_interceptor).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String uri = "";
        if (id == R.id.btn_interceptor) {
            uri = SCHEME_FOR_LOAD_HTTP;
        } else if (id == R.id.btn_dispatcher) {
            uri = SCHEME_FOR_LOAD_HTTPS;
        }
        Pigeon.dispatch(SampleLibActivity.this, uri);
    }
}
