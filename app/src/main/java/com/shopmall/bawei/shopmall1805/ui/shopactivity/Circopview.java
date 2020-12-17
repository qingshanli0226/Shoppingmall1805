package com.shopmall.bawei.shopmall1805.ui.shopactivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Circopview extends View {
    private String num;

    public void setNum(String num){
          this.num=num;
          postInvalidate();
    }

    public Circopview(Context context) {
        super(context);
    }

    public Circopview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Circopview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(10);
        paint.setAntiAlias(true);
        paint.setDither(true);
        canvas.drawCircle(10,10,10,paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setTextSize(13);
        paint.setStrokeWidth(1);
        canvas.drawText(num,4,14,paint);
    }
}
