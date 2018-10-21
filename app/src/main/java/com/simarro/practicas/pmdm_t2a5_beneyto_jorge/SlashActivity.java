package com.simarro.practicas.pmdm_t2a5_beneyto_jorge;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class SlashActivity extends AppCompatActivity {

    private final int ANIMATION_DURATION = 3000;
    private RelativeLayout relativeLayout;
    private Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slash);

        relativeLayout = (RelativeLayout) findViewById(R.id.main_layout);
        animation = AnimationUtils.loadAnimation(SlashActivity.this, R.anim.up_translation);
        animation.setDuration(600);

        relativeLayout.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SlashActivity.this, MainActivity.class));
                SlashActivity.this.finish();
            }
        }, ANIMATION_DURATION);


    }
}
