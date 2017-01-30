package baserecyclerview.test.com.commonrecyclerview.widgets;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by T32E on 17/1/29.
 */

public class CustormView extends View {

    public static final String TAG = "CustormView";

    private Paint mPaint = new Paint();

    private static final float radius = 200.0f;

    //宽高
    private int mWidth;
    private int mHeight;

    private ValueAnimator animator;
    private float animatedValue;
    private long animatorDuration = 3000;
    private TimeInterpolator timeInterpolator = new DecelerateInterpolator();

    private void initAnimator(long duration){
        if (animator !=null &&animator.isRunning()){
            animator.cancel();
            animator.start();
        }else {
            animator=ValueAnimator.ofFloat(0,855).setDuration(duration);
            animator.setInterpolator(timeInterpolator);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    animatedValue = (float) animation.getAnimatedValue();
                    Log.d(TAG,"animatedValue-->"+animatedValue);
                    invalidate();
                }
            });
            animator.start();
        }
    }

    private void initPaint() {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将画布坐标从原点移到中心位置
        Log.d(TAG,"onDraw()");
        canvas.translate(mWidth / 2, mHeight / 2);
      //  canvas.drawColor(Color.RED);
        initPaint();
        canvas.drawPoint(0, 0, mPaint);
        canvas.drawPoint(-mWidth / 2 * 0.8f, 0, mPaint);
        canvas.drawPoint(mWidth / 2 * 0.8f, 0, mPaint);
        canvas.drawPoint(0, -mHeight / 2 * 0.8f, mPaint);
        canvas.drawPoint(0, mHeight / 2 * 0.8f, mPaint);
        canvas.drawLines(new float[]{mWidth / 2 * 0.8f, 0, mWidth / 2 * 0.8f - 50.0f, -50.0f}, mPaint);
        canvas.drawLines(new float[]{mWidth / 2 * 0.8f, 0, mWidth / 2 * 0.8f - 50.0f, 50.0f}, mPaint);
        canvas.scale(1, -1);
        canvas.drawLines(new float[]{50.0f, mHeight / 2 * 0.8f - 50, 0, mHeight / 2 * 0.8f}, mPaint);
        canvas.drawLines(new float[]{-50.0f, mHeight / 2 * 0.8f - 50, 0, mHeight / 2 * 0.8f}, mPaint);
        canvas.drawLines(new float[]{-mWidth / 2 * 0.8f, 0, mWidth / 2 * 0.8f, 0, 0, -mHeight / 2 * 0.8f, 0, mHeight / 2 * 0.8f}, mPaint);
       // drawCircle(canvas);

    }

    private void drawCircleForAnimator(Canvas canvas){
        RectF rectF = new RectF(-radius,-radius,radius,radius);
        canvas.drawArc(rectF,-90,90,false,mPaint);

    }

    public void showLoading(){
        initAnimator(animatorDuration);
        invalidate();
    }

    public void stopLoading(){
        if(animator!=null && animator.isRunning()){
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    animator.cancel();
                }
            });
        }
    }

    //x*x =2
    private void drawCircle(Canvas canvas){
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(20.0f);
        canvas.drawPoint((float) Math.sqrt(2)*radius/2,(float)Math.sqrt(2)*radius/2,mPaint);
        canvas.drawPoint(-(float) Math.sqrt(2)*radius/2,(float)Math.sqrt(2)*radius/2,mPaint);
        RectF rectF = new RectF(-radius,-radius,radius,radius);
        canvas.drawArc(rectF,0,-180,false,mPaint);
        canvas.drawArc(rectF,-180,180,false,mPaint);
    }


    private void doubanAnimator(Canvas canvas, Paint mPaint){
        mPaint.setStyle(Paint.Style.STROKE);//描边
        mPaint.setStrokeCap(Paint.Cap.ROUND);//圆角笔触
        mPaint.setColor(Color.rgb(97, 195, 109));
        mPaint.setStrokeWidth(15);
        float point = Math.min(mWidth,mWidth)*0.06f/2;
        float r = point*(float) Math.sqrt(2);
        RectF rectF = new RectF(-r,-r,r,r);
        canvas.save();

        // rotate
        if (animatedValue>=135){
            canvas.rotate(animatedValue-135);
        }

        // draw mouth
        float startAngle=0, sweepAngle=0;
        if (animatedValue<135){
            startAngle = animatedValue +5;
            sweepAngle = 170+animatedValue/3;
        }else if (animatedValue<270){
            startAngle = 135+5;
            sweepAngle = 170+animatedValue/3;
        }else if (animatedValue<630){
            startAngle = 135+5;
            sweepAngle = 260-(animatedValue-270)/5;
        }else if (animatedValue<720){
            startAngle = 135-(animatedValue-630)/2+5;
            sweepAngle = 260-(animatedValue-270)/5;
        }else{
            startAngle = 135-(animatedValue-630)/2-(animatedValue-720)/6+5;
            sweepAngle = 170;
        }
        canvas.drawArc(rectF,startAngle,sweepAngle,false,mPaint);

        // draw eye
        canvas.drawPoints(new float[]{
                -point,-point
                ,point,-point
        },mPaint);
        canvas.restore();
    }

    public CustormView(Context context) {
        this(context, null);
    }

    public CustormView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustormView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CustormView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
