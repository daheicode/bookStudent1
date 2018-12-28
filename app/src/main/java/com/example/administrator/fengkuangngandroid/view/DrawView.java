package com.example.administrator.fengkuangngandroid.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
    private final int WIDTH=600;
    private final int HEIGHT=1000;
    private Bitmap cacheBitmap;
    private Paint paint;
    private Canvas cacheCanves;
    private Path path;
    private float preX;
    private float preY;
    private Canvas mCanvas;

    public DrawView(Context context) {
        this(context,null);
    }

    public DrawView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        cacheBitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
        paint = new Paint();
        cacheCanves = new Canvas();
        path = new Path();
        //为bitmap设置一个背景
        cacheBitmap.eraseColor(Color.WHITE);
//        cacheCanves.drawColor(Color.WHITE);
        cacheCanves.setBitmap(cacheBitmap);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                preX=x;
                preY=y;
                break;
                case MotionEvent.ACTION_MOVE:
                    path.quadTo(preX,preY,x,y);
                    preX=x;
                    preY=y;
                    break;
                    case MotionEvent.ACTION_UP:
                        //将绘制的东西直接保存在我们的cacheBitmap中
                        cacheCanves.drawPath(path,paint);
                        //如果我们不清空我们之前的path 就不用缓存我们的数据了
                        path.reset();
                        break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas=canvas;
        Paint paint1 = new Paint();
        canvas.drawBitmap(cacheBitmap,0,0,paint1);
        canvas.drawPath(path,paint);
//        canvas.setBitmap(cacheBitmap);
//        cacheCanves.drawPath(path,paint);
    }

    public void clear(){
//        Paint paint=new Paint();
        if(cacheBitmap!=null){
            cacheBitmap=null;
        }
        cacheBitmap = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
        cacheBitmap.eraseColor(Color.WHITE);
        cacheCanves.setBitmap(cacheBitmap);
//        mCanvas.drawBitmap(cacheBitmap,0,0,paint);
        invalidate();
    }


    public Bitmap getMyBitmap(){
        if(cacheBitmap!=null){
            return cacheBitmap;
        }else{
            throw new IllegalStateException("请先绘制");
        }
    }
}
