package xyz.bboylin.demo;

import android.os.Bundle;
import android.view.View;

import xyz.bboylin.pigeon.core.Pigeon;
import xyz.bboylin.pigeon.core.Postcard;
import xyz.bboylin.pigeon.core.PostcardBuilder;
import xyz.bboylin.samplelib.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_act_router).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Postcard postcard = PostcardBuilder.newInstance(MainActivity.this, "pigeon://sample/SampleLibActivity")
                .addExtra("demo", "this is demo string from pigeon postcard")
                .build();
        Pigeon.setOff(postcard);
    }
}
