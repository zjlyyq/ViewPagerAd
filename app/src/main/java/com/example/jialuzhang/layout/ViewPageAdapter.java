package com.example.jialuzhang.layout;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jialuzhang on 2017/4/12.
 */

public class ViewPageAdapter extends PagerAdapter{
    private List<ImageView> list;
    public ViewPageAdapter(ArrayList<ImageView> list){
        this.list = list;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        position = position % list.size();
        container.removeView(list.get(position));
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % list.size();
        container.addView(list.get(position));
        return  list.get(position);
    }
}
