package transage.com.viewpager_circulation;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * 这是一个 viewpager无限循环滑动的例子
 */
public class MainActivity extends AppCompatActivity {
    ArrayList<ImageView> listImage = new ArrayList<>();
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter() {
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
                int index = position%listImage.size();
                ImageView imageView = listImage.get(index);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                //获取需要加载视图的父控件
                ViewParent parent = listImage.get(index).getParent();
                //判断父控件是否为空，若为空说明已被ViewPager加载，那么我们要移除改视图，这样就能保证一个视图只有一个父控件，
                if(parent!=null){
                    viewPager.removeView(imageView);
                    Log.i("dongrp","xiaoyu3");
                }
                Log.i("dongrp","为空");
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
            }
        });
    }

    private void initData() {
        ImageView image1 = new ImageView(this);
        image1.setImageResource(R.mipmap.p1);
        listImage.add(image1);
        ImageView image2 = new ImageView(this);
        image2.setImageResource(R.mipmap.p2);
        listImage.add(image2);
        ImageView image3 = new ImageView(this);
        image3.setImageResource(R.mipmap.p3);
        listImage.add(image3);
    }

}
