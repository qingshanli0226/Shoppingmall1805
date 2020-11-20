package com.shopmall.bawei.shopmall1805;



import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;
import android.content.Intent;

import com.shopmall.bawei.shopmall1805.home.MainActivity;

import framework.BaseActivity;


public class WelcomActivity extends BaseActivity {
    private ImageView ImageOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutId());
    }

    @Override
    protected void OnClickListener() {


    }

    @Override
    protected void initData() {
        ImageOne = (ImageView) findViewById(R.id.Image_one);


        ObjectAnimator scaleX = ObjectAnimator.ofFloat(ImageOne, "scaleX", 1.5f,1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(ImageOne, "scaleY", 1.5f,1.0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.setDuration(2000);
        animatorSet.start();

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Intent intent = new Intent(WelcomActivity.this, MainActivity.class);

                startActivity(intent);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_welcome;
    }
}
