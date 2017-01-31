package baserecyclerview.test.com.commonrecyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import baserecyclerview.test.com.commonrecyclerview.widgets.CustormView;

/**
 * Created by T32E on 17/1/29.
 */

public class TestCustormViewActivity extends BaseActivity {
    public static final String TAG = "TestCustormViewActivity";
    private CustormView mCustormView;
    private TextView mTextValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custorm);
        initView();
    }

    private void initView(){
        mCustormView = (CustormView) findViewById(R.id.myview);
        mTextValue = (TextView)findViewById(R.id.tv_rotate_value);
        mCustormView.showLoading();
        float animatorValue = mCustormView.getAnimatorValue();
        mTextValue.setText(String.valueOf(animatorValue));
    }
}
