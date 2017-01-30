package baserecyclerview.test.com.commonrecyclerview;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import baserecyclerview.test.com.commonrecyclerview.widgets.Images;

/**
 * Created by T32E on 17/1/25.
 */

public class HomeActivity extends BaseActivity {
    public static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
    }

    private void initView(){
        ImageView imageView = (ImageView) findViewById(R.id.iv_activitybase);
        String imageUrl = Images.imageUrls[0];
        Glide.with(this).load(imageUrl).into(imageView);
    }

}
