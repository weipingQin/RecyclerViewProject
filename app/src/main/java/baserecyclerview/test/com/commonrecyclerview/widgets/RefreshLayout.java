package baserecyclerview.test.com.commonrecyclerview.widgets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by T32E on 17/2/1.
 */

public class RefreshLayout extends LinearLayout{

    //整个头部控件 下方的refreshView的头部控件
    private LinearLayout mWholeHeaderView;

    //下拉刷新控件
    private View mRefreshHeadView;

    //下拉刷新的控件
    private View mCustomHeadView;

    private View mContentView;

    //下拉刷新和上拉加载更多代理
    private RefreshLayoutDelegate mDelegate;

    private RefreshViewHolder mRefreshViewHolder;

    //下拉刷新控件的高度
    private int mRefreshHeaderViewHeight;

    /**
     * 整个头部控件最小的paddingTop
     */
    private int mMinWholeHeaderViewPaddingTop;
    /**
     * 整个头部控件最大的paddingTop
     */
    private int mMaxWholeHeaderViewPaddingTop;

    ///是否处于加载更多
    private boolean mIsLoadMore = false;

    private int mTouchSlop;

    private RecyclerView mRecyclerView;

    //当前的刷新状态
    private RefreshStatus mCurrentRefreshStatue = RefreshStatus.IDLE;

    public RefreshLayout(Context context) {
        super(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        initWholeHeadView();
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void startChangeWholeHeaderViewPaddingTop(int distance){

    }


    //初始化整个头部控件
    private void initWholeHeadView(){
        mWholeHeaderView = new LinearLayout(getContext());
        mWholeHeaderView.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        mWholeHeaderView.setOrientation(LinearLayout.VERTICAL);
        addView(mWholeHeaderView);
    }

    ///添加自定义View刷新动画
    public void setRefreshListener(CustormView custormView){
        custormView.showLoading();
//        if(mCustomHeadView instanceof RefreshView){
//            ((RefreshView) mCustomHeadView).showLoading();
//        }
    }

    //初始化下拉刷新控件
    private void initRefreshHeaderView(){
        mRefreshHeadView = mRefreshViewHolder.getRefreshHeaderView();
        if(mRefreshHeadView !=null){
            mRefreshHeadView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));

            mRefreshHeaderViewHeight = mRefreshViewHolder.getRefreshHeaderViewHeight();
            mMinWholeHeaderViewPaddingTop = -mRefreshHeaderViewHeight;
            mMaxWholeHeaderViewPaddingTop = (int) (mRefreshHeaderViewHeight * mRefreshViewHolder.getSpringDistanceScale());

            mWholeHeaderView.setPadding(0,mMinWholeHeaderViewPaddingTop,0,0);
            mWholeHeaderView.addView(mRecyclerView);
        }
    }

    //设置下拉刷新自定义控件
    public void setCustomHeaderView(View customHeaderView){
        if(mCustomHeadView !=null && mCustomHeadView.getParent()!=null){
            ViewGroup parent = (ViewGroup)mCustomHeadView.getParent();
            parent.removeView(mCustomHeadView);
        }
        mCustomHeadView = customHeaderView;
        if(mCustomHeadView!=null){
            ViewGroup parent = (ViewGroup)mCustomHeadView.getParent();
            parent.removeView(mCustomHeadView);
            mCustomHeadView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 200));
            mWholeHeaderView.addView(mCustomHeadView);
            //下拉刷新的自定义控件是否可滚动
          //  mIsCustomHeaderViewScrollable = scrollable;
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if(getChildCount() !=2){
            throw  new RuntimeException(RefreshLayout.class.getSimpleName()+"必须只有一个子控件");
        }

        mContentView = getChildAt(1);

        if(mContentView instanceof RecyclerView){
            mRecyclerView = (RecyclerView)mContentView;
        }
    }

    public void setDelegate(RefreshLayoutDelegate delegate){
        mDelegate = delegate;
    }

    public interface RefreshScaleDelegate {
        /**
         * 下拉刷新控件可见时，处理上下拉进度
         *
         * @param scale         下拉过程0 到 1，回弹过程1 到 0，没有加上弹簧距离移动时的比例
         * @param moveYDistance 整个下拉刷新控件paddingTop变化的值，如果有弹簧距离，会大于整个下拉刷新控件的高度
         */
        void onRefreshScaleChanged(float scale, int moveYDistance);
    }

    public interface RefreshLayoutDelegate {
        /**
         * 开始刷新
         */
        void onRefreshLayoutBeginRefreshing(RefreshLayout refreshLayout);
        /**
         * 开始加载更多。由于监听了ScrollView、RecyclerView、AbsListView滚动到底部的事件，所以这里采用返回boolean来处理是否加载更多。否则使用endLoadingMore方法会失效
         *
         * @param refreshLayout
         * @return 如果要开始加载更多则返回true，否则返回false。（返回false的场景：没有网络、一共只有x页数据并且已经加载了x页数据了）
         */
        boolean onRefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout);
    }

    public enum RefreshStatus {
        IDLE, PULL_DOWN, RELEASE_REFRESH, REFRESHING
    }


}
