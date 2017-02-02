package baserecyclerview.test.com.commonrecyclerview.adapter;

import android.support.v7.widget.RecyclerView;

import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import baserecyclerview.test.com.commonrecyclerview.R;

/**
 * Created by T32E on 17/1/31.
 */

public class MyHeadAndFooterAdapter extends HeaderAndFooterWrapper<ViewHolder>{
    private RecyclerView.Adapter mAdapter;

    public MyHeadAndFooterAdapter(RecyclerView.Adapter adapter){
        super(adapter);
        mAdapter = adapter;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (isHeaderViewPos(position)) {
            return ;
        }
        if(isFooterViewPos(position)){
            return;
        }
        mAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }

    private int getRealItemCount(){
        return mAdapter.getItemCount();
    }
}
