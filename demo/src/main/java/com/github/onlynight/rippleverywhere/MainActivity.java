package com.github.onlynight.rippleverywhere;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.onlynight.rippleeverywhere.library.RippleLayout;

public class MainActivity extends AppCompatActivity {

    private RippleLayout rippleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rippleLayout = (RippleLayout) findViewById(R.id.rippleLayout);
        rippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rippleLayout.getRadiusAnimator().start();
            }
        });
    }
}
