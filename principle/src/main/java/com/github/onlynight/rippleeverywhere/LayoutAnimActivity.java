package com.github.onlynight.rippleeverywhere;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class LayoutAnimActivity extends AppCompatActivity {

    private RippleLinearLayout rippleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_anim);

        rippleLayout = (RippleLinearLayout) findViewById(R.id.rippleLayout);
        rippleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rippleLayout.startAnimation();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_layout_anim, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuViewAnim) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
