package com.github.onlynight.rippleeverywhere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RippleImageView imageRipple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageRipple = (RippleImageView) findViewById(R.id.imageRipple);
        imageRipple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageRipple.startAnimation();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLayoutAnim) {
            startActivity(new Intent(this, LayoutAnimActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
