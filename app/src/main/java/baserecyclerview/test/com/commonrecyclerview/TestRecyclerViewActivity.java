package baserecyclerview.test.com.commonrecyclerview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import baserecyclerview.test.com.commonrecyclerview.adapter.DividerItemDecoration;

/**
 * Created by T32E on 17/1/30.
 */

public class TestRecyclerViewActivity extends BaseActivity {
    public static final String TAG = "RecyclerViewActivity";

    private RecyclerView mRecyclerView;
    private List<String> mList;

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(TestRecyclerViewActivity.this, DividerItemDecoration.VERTICAL_LIST));
        HomeAdapter adapter = new HomeAdapter();
        mRecyclerView.setAdapter(adapter);
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
