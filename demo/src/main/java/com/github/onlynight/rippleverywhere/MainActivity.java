package com.github.onlynight.rippleverywhere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBasicDemoClick(View view) {
        startActivity(new Intent(this, BasicDemoActivity.class));
    }

    public void onLayoutAnimDemoClick(View view) {
        startActivity(new Intent(this, LayoutAnimDemoActivity.class));
    }

    public void onRecyclerViewDemoClick(View view) {
        startActivity(new Intent(this, RecyclerViewDemoActivity.class));
    }
    
}
