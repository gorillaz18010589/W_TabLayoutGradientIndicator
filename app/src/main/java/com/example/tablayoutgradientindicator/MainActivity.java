package com.example.tablayoutgradientindicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tableLayout;
    View mIndicator;
    ViewPager viewPager;
    TabFragmentAdapter tabFragmentAdapter;

    private int indicatorWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.init
        tableLayout = findViewById(R.id.tab);
        mIndicator = findViewById(R.id.indicator);
        viewPager = findViewById(R.id.viewPager);

            //2.設定View Pager amd Fragment
            //TabFragmentAdapter(@NonNull FragmentManager fm):物件實體化(裡面放入Fragment經理人)
            //tabFragmentAdapteradd.Fragment(Fragment fragment , String title)://指定的Frgment新增title( fragment物件 ,標題 ):
            //ViewPager.setAdapter( PagerAdapter adapter) ://ViewPager設定調變器(這邊用的是FragmentPagerAdapter)
        //Tablayout.setupWithViewPager(@androidx.annotation.Nullable androidx.viewpager.widget.ViewPager viewPager):在Tablayout設定要放入的ViewPager
        tabFragmentAdapter = new TabFragmentAdapter(getSupportFragmentManager());    //自己寫的TabFragmentAdapter
        tabFragmentAdapter.addFragment(OneFragment.newInstance(), "Tab1"); //呼叫自己寫的方法,在Frgament新增標題
        tabFragmentAdapter.addFragment(TwoFragment.newInstance(), "Tab2");
        tableLayout.setTabTextColors(Color.BLUE, Color.RED);

        viewPager.setAdapter(tabFragmentAdapter);
        tableLayout.setupWithViewPager(viewPager);


        //3.設定tab填上一半的顏色
        //View.post(Runnable action):(回傳boolean)
        //View.getWidth():取得螢幕寬(回傳int)
        //tablayout.getTabCount():取得tablayout裡面的註冊數
        //ViewGroup.LayoutParams getLayoutParams():
        //调用View.post()既方便又可以保证指定的任务在视图操作中顺序执行。
        tableLayout.post(new Runnable() {
            @Override
            public void run() {
                indicatorWidth = tableLayout.getWidth() / tableLayout.getTabCount(); //取得tab的寬高除上兩個項目,各一半
                FrameLayout.LayoutParams indicatorLayoutParams = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();
                indicatorLayoutParams.width = indicatorWidth;
                mIndicator.setLayoutParams(indicatorLayoutParams);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //1.抓到用戶滑動ViewPager的監聽方法
            @Override
            public void onPageScrolled(int i, //1.當前顯示得第一頁int index,postion +1 不為0才有效果
                                       float positionOffset, //2.(0,1)抓你滑動的位直偏移輛
                                       int positionOffsetPixels) {//3.以px像素決定座標位置

                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();

                float translationOffset = (positionOffset + i) * indicatorWidth;
                params.leftMargin = (int) translationOffset;
                mIndicator.setLayoutParams(params);

                Log.v("hank", "onPageScrolled, positionOffset:)" + Float.toString(positionOffset));

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
