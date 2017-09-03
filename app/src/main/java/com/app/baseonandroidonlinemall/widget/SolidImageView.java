package com.app.baseonandroidonlinemall.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

/**
 * Created by hblolj on 2016/8/22.
 */
public class SolidImageView extends android.support.v7.widget.AppCompatImageView {

    public SolidImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SolidImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SolidImageView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获取控件需要重新绘制的区域
        Rect rect = canvas.getClipBounds();
        rect.bottom--;
        rect.right--;
        Paint paint = new Paint();
        paint.setARGB(100, 211, 211, 211);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        canvas.drawLine(rect.centerX()*2, 0, rect.centerX()*2, rect.centerY()*2, paint);
        //canvas.drawRect(rect, paint);
    }
}
