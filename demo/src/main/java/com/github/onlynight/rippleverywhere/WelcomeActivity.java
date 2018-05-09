package com.github.onlynight.rippleverywhere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.onlynight.rippleeverywhere.library.RippleLayout;

public class WelcomeActivity extends AppCompatActivity {

    private RippleLayout rippleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        rippleLayout = (RippleLayout) findViewById(R.id.ripple_layout);
        rippleLayout.getRadiusAnimator().start();

        rippleLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }, 1500);
    }
}
