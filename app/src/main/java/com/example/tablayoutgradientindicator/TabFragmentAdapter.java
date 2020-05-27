package com.example.tablayoutgradientindicator;
//目標table page
//1.繼承FragmentPagerAdapter
//2.建構式,跟實作Fragment getItem(int position)方法跟 public int getCount()方法
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabFragmentAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    public TabFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    //1.傳回Frgament<陣列>資料結構
    @NonNull
    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    //2.實作方法傳回的是頁面的個數,只要返回陣列的長度就好
    @Override
    public int getCount() {
        return fragmentTitleList.size();
    }

    //3.自己呼叫的取得pageTitle方法
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

    //4.自己寫的設置Fragment title方法
    public void  addFragment(Fragment fragment , String title){
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }


}
