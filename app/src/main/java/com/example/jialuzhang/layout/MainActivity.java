package com.example.jialuzhang.layout;

import android.os.Handler;
import android.os.health.PidHealthStats;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    ArrayList<ImageView> list = new ArrayList<ImageView>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        int[] images = new int[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};
        final LinearLayout pointGroup = (LinearLayout) findViewById(R.id.pointgroup);
        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(this);
            // 制作底部小圆点
            ImageView pointImage = new ImageView(this);
            pointImage.setImageResource(R.drawable.shape_point_selector);
            imageView.setImageResource(images[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            // 设置小圆点的布局参数
            int PointSize = getResources().getDimensionPixelSize(R.dimen.point_size);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(PointSize, PointSize);
            if (i > 0) {
                params.leftMargin = getResources().getDimensionPixelOffset(R.dimen.point_margin);
                pointImage.setSelected(false);
            } else {
                pointImage.setSelected(true);
            }
            pointImage.setLayoutParams(params);
            list.add(imageView);
            pointGroup.addView(pointImage);
        }
        ViewPageAdapter adapter = new ViewPageAdapter(list);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            int lastPosition;
            @Override
            public void onPageSelected(int position) {
                position = position % list.size();
                pointGroup.getChildAt(position).setSelected(true);
                pointGroup.getChildAt(lastPosition).setSelected(false);
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        final Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentPosition = viewPager.getCurrentItem();

                if(currentPosition == viewPager.getAdapter().getCount() - 1){
                    // 最后一页
                    viewPager.setCurrentItem(0);
                }else{
                    viewPager.setCurrentItem(currentPosition + 1);
                }

                // 一直给自己发消息
                mHandler.postDelayed(this,5000);
            }
        },5000);
    }
}
