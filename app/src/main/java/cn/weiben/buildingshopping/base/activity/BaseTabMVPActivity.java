package cn.weiben.buildingshopping.base.activity;

import android.content.Context;
import android.graphics.Color;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ConvertUtils;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.List;

import cn.weiben.buildingshopping.R;
import cn.weiben.buildingshopping.base.BaseContract;
import cn.weiben.buildingshopping.ui.adapter.CommonPagerAdapter;

public abstract class BaseTabMVPActivity<P extends BaseContract.BasePresenter> extends BaseMVPActivity<P> {

    private List<Fragment> mFragments;
    private List<String> mTabNameList;
    private ViewPager mViewPager;
    private MagicIndicator mMagicIndicator;
    /**
     * 当前显示的tab所在位置，对应fragment所在位置
     */
    private int currentPosition = 0;

    private CommonNavigator commonNavigator;
    private CommonPagerAdapter commonPagerAdapter;


    /**
     * 初始化导航栏页面(必须调用）
     *
     * @param mFragments
     * @param mTabNameList
     */
    public void addFragments(List<Fragment> mFragments, List<String> mTabNameList, ViewPager mViewPager, MagicIndicator mMagicIndicator) {
        this.mFragments = mFragments;
        this.mTabNameList = mTabNameList;
        this.mViewPager = mViewPager;
        this.mMagicIndicator = mMagicIndicator;

        commonNavigator = new CommonNavigator(this);
        commonNavigator.setSkimOver(true);
        commonNavigator.setAdapter(getCommonNavigatorAdapter());
        commonNavigator.setAdjustMode(true);
        this.mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(this.mMagicIndicator, this.mViewPager);
        commonPagerAdapter = new CommonPagerAdapter(this.mFragments, fragmentManager);
        this.mViewPager.setAdapter(commonPagerAdapter);
        this.mViewPager.setOffscreenPageLimit(this.mTabNameList.size());
        commonNavigator.notifyDataSetChanged();
    }


    /**
     * 重写此方法可修改默认的tab 样式
     * @return
     */
    protected CommonNavigatorAdapter getCommonNavigatorAdapter() {
        return new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTabNameList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setBackground(getResources().getDrawable(R.color.colorPrimary));
                clipPagerTitleView.setText(mTabNameList.get(index));
                clipPagerTitleView.setTextSize(ConvertUtils.sp2px(15));
                clipPagerTitleView.setTextColor(Color.parseColor("#55FFFFFF"));
                clipPagerTitleView.setClipColor(Color.WHITE);
                clipPagerTitleView.setOnClickListener(v -> {
                    currentPosition = index;
                    mViewPager.setCurrentItem(index, false);
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        };
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFragments = null;
        mTabNameList = null;
        mViewPager = null;
        mMagicIndicator = null;
        commonNavigator = null;
        commonPagerAdapter = null;
    }
}
