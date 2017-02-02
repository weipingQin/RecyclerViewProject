package baserecyclerview.test.com.commonrecyclerview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import baserecyclerview.test.com.commonrecyclerview.adapter.DividerItemDecoration;
import baserecyclerview.test.com.commonrecyclerview.adapter.MyHeadAndFooterAdapter;
import baserecyclerview.test.com.commonrecyclerview.widgets.CustormView;
import baserecyclerview.test.com.commonrecyclerview.widgets.RefreshLayout;
import baserecyclerview.test.com.commonrecyclerview.widgets.RefreshView;

/**
 * Created by T32E on 17/1/30.
 */

public class TestRecyclerViewActivity extends BaseActivity implements RefreshLayout.RefreshLayoutDelegate{
    public static final String TAG = "RecyclerViewActivity";

    private RecyclerView mRecyclerView;
    private List<String> mList;

    private View mHeadView;
    private MyHeadAndFooterAdapter mAdapter;

    private View mRefreshTopView;

    private RefreshLayout mRefreshLayout;

    //初始化数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testrecyclerview);
        init();
    }

    private void init() {
        mList = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mList.add("" + (char) i);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRefreshLayout = (RefreshLayout)findViewById(R.id.refresh_layout);
        View customView = LayoutInflater.from(this).inflate(R.layout.recyclerview_headview,null);
        CustormView refreshView = (CustormView) customView.findViewById(R.id.refreshview);
       // mRefreshLayout.setCustomHeaderView(refreshView);
      //  mRefreshLayout.setRefreshListener(refreshView);
      //  mRefreshLayout.setDelegate(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(TestRecyclerViewActivity.this, DividerItemDecoration.VERTICAL_LIST));
        HomeAdapter homeAdapter = new HomeAdapter();
        mAdapter = new MyHeadAndFooterAdapter(homeAdapter);
        mAdapter.addHeaderView(customView);
        refreshView.showLoading();
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setRefreshListener(){
        mRefreshLayout.setDelegate(this);
    }

    @Override
    public void onRefreshLayoutBeginRefreshing(RefreshLayout refreshLayout) {

    }

    @Override
    public boolean onRefreshLayoutBeginLoadingMore(RefreshLayout refreshLayout) {
        return false;
    }


    class HomeAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(TestRecyclerViewActivity.this).inflate(R.layout.recyclerview_item, null);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    //定义一个MyViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_recyclerview);
        }
    }
}
