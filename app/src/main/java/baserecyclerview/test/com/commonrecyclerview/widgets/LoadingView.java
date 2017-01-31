package baserecyclerview.test.com.commonrecyclerview.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by T32E on 17/1/29.
 */

public class LoadingView extends View {

    //定义屏幕的宽度和高度
    private int mWidth, mHeight;
    private static final float radius = 200f;

    private Paint mPaint = new Paint();

    //定义坐标的起始点和结束点
    private PointF mStartPoint,mEndPoint,mControlPoint;


    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    /**
     * 初始化画布和画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        mStartPoint = new PointF(0,0);
        mEndPoint = new PointF(0,0);
        mControlPoint = new PointF(0,0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // drawTaiji(canvas);
      //  drawCircleView(canvas);
      //  drawbolang(canvas);
    }


    private void drawTaiji(Canvas canvas){
        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();

        path1.addCircle(0, 0, 200, Path.Direction.CW);
        path2.addRect(0, -200, 200, 200, Path.Direction.CW);
        path3.addCircle(0, -100, 100, Path.Direction.CW);
        path4.addCircle(0, 100, 100, Path.Direction.CCW);


        path1.op(path2, Path.Op.DIFFERENCE);
        path1.op(path3, Path.Op.UNION);
        path1.op(path4, Path.Op.DIFFERENCE);

        canvas.drawPath(path1, mPaint);
    }


    /**
     * 画波浪曲线
     * @param canvas
     */
    private void drawbolang(Canvas canvas){
        canvas.drawPoint(mStartPoint.x,mStartPoint.y,mPaint);
        canvas.drawPoint(mEndPoint.x,mEndPoint.y,mPaint);
        canvas.drawPoint(mControlPoint.x,mControlPoint.y,mPaint);

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(mStartPoint.x,mStartPoint.y,mControlPoint.x,mControlPoint.y,mPaint);
        canvas.drawLine(mEndPoint.x,mEndPoint.y,mControlPoint.x,mControlPoint.y,mPaint);

        // 绘制贝塞尔曲线
        mPaint.setColor(Color.argb(125,0,125,125));
        mPaint.setStrokeWidth(8);
        Path path = new Path();

        path.moveTo(mStartPoint.x,mStartPoint.y);
        path.quadTo(mControlPoint.x,mControlPoint.y,mEndPoint.x,mEndPoint.y);

        canvas.drawPath(path, mPaint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w/2;
        this.mHeight = h/2;

        //初始化位置信息
        mStartPoint.x = -mWidth;
        mStartPoint.y = 0;

        mEndPoint.x = mWidth;
        mEndPoint.y = 0 ;

        mControlPoint.x = 0;
        mControlPoint.y = mHeight;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mControlPoint.x = event.getX();
        mControlPoint.y = event.getY();
        invalidate();
        return true;
    }

    private void drawCircleView(Canvas canvas) {
        canvas.translate(mWidth/2,mHeight/2);
        canvas.drawCircle(0,0,radius,mPaint);
    }
}
