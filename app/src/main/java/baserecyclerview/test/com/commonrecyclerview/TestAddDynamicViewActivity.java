package baserecyclerview.test.com.commonrecyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

/**
 * Created by T32E on 17/1/26.
 */

public class TestAddDynamicViewActivity extends BaseActivity {

    public static final String TAG = "TestAddDynamicViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createChildViewAddParentView();
    }

    /**
     * 创建子View
     *
     * @return
     */
    private void createChildViewAddParentView() {
        LinearLayout rootView = (LinearLayout) findViewById(R.id.root_layout);
        View view = LayoutInflater.from(this).inflate(R.layout.home_item, rootView, false);
        LinearLayout childView = (LinearLayout) view.findViewById(R.id.ll_home_item);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 1.0f;
        layoutParams.topMargin = getStatusBarHeight();
        LinearLayout childView2 = new LinearLayout(this);
        childView2.setLayoutParams(layoutParams);
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.ic_launcher);
            imageView.setLayoutParams(layoutParams);
            childView.addView(imageView);
        }
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.ic_launcher);
            imageView.setLayoutParams(layoutParams);
            childView2.addView(imageView);
        }
        rootView.addView(childView);
        rootView.addView(childView2);
    }

    private int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
            return 75;

        }
    }

}
