package baserecyclerview.test.com.commonrecyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import baserecyclerview.test.com.commonrecyclerview.widgets.CustormView;

/**
 * Created by T32E on 17/1/29.
 */

public class TestCustormViewActivity extends BaseActivity {
    public static final String TAG = "TestCustormViewActivity";
    private CustormView mCustormView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custorm);
        initView();
    }

    private void initView(){
        mCustormView = (CustormView) findViewById(R.id.myview);
        mCustormView.showLoading();
    }
}
