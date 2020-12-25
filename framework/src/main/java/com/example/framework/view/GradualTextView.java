package com.example.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class GradualTextView extends AppCompatTextView {
    private int width;
    private Paint paint;
    private Rect textBound=new Rect();
    private LinearGradient linearGradient;
    public GradualTextView(@NonNull Context context) {
        super(context);
    }

    public GradualTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GradualTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        width = getWidth();
        paint=new Paint();
        paint.setTextSize(getTextSize());
        String text = getText().toString();
        paint.getTextBounds(text,0,text.length(),textBound);
        linearGradient=new LinearGradient(0,0,textBound.right,0, Color.parseColor("#000000"),Color.parseColor("#ffffff"), Shader.TileMode.REPEAT);
        paint.setShader(linearGradient);
        canvas.drawText(text,getMeasuredWidth()/2-textBound.width()/2,getMeasuredHeight()/2+textBound.height()/2,paint);

    }
}
