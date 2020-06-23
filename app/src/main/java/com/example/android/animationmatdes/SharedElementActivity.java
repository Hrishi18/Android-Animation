package com.example.android.animationmatdes;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SharedElementActivity extends AppCompatActivity {
    
    RelativeLayout revealDemo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_elements);

        initPage();
    }

    @Override
    public boolean onSupportNavigateUp() {

        finishAfterTransition();
        return true;
    }

    private void initPage() {



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Shared Element Transition");

        revealDemo = (RelativeLayout) findViewById(R.id.click_reveal);
        revealDemo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                makeCircularRevealAnimation(revealDemo);
            }
        });


        Button btnExit = findViewById(R.id.exit_button);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAfterTransition();
            }
        });
    }

    private void makeCircularRevealAnimation(RelativeLayout revealDemo) {
        final TextView textDesc = (TextView)findViewById(R.id.textDesc);

        int centerX = (revealDemo.getLeft() + revealDemo.getRight())/2;
        int centerY = (revealDemo.getTop() + revealDemo.getBottom())/2;

        float radius = Math.max(textDesc.getWidth(), textDesc.getHeight()) * 2.0f;

        if (textDesc.getVisibility() == View.INVISIBLE) {
            textDesc.setVisibility(View.VISIBLE);
            textDesc.setText(R.string.description);

            ViewAnimationUtils.createCircularReveal(textDesc, centerX, centerY, 0, radius).start();
        } else {
            Animator reveal = ViewAnimationUtils.createCircularReveal(textDesc, centerX, centerY, radius, 0);
            reveal.addListener(new AnimatorListenerAdapter(){
                public void onAnimationEnd(Animator animation) {
                    textDesc.setVisibility(View.INVISIBLE);
                }
            });
            reveal.start();
        }
    }

}
