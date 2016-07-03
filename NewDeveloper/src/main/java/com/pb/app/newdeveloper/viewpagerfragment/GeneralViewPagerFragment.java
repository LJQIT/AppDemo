package com.pb.app.newdeveloper.viewpagerfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.pb.app.newdeveloper.R;
import com.pb.app.newdeveloper.adapter.ViewPageFragmentAdapter;
import com.pb.app.newdeveloper.base.BaseViewPagerFragment;
import com.pb.app.newdeveloper.base.BlogFragment;
import com.pb.app.newdeveloper.base.EventFragment;
import com.pb.app.newdeveloper.base.NewsFragment;
import com.pb.app.newdeveloper.base.QuestionFragment;

public class GeneralViewPagerFragment extends BaseViewPagerFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSetupTabAdapter(ViewPageFragmentAdapter adapter) {
        String[] title = getResources().getStringArray(
                R.array.general_viewpage_arrays);

        adapter.addTab(title[0], "news", NewsFragment.class,
                getBundle(1));
        adapter.addTab(title[1], "latest_blog", BlogFragment.class,
                getBundle(4));
        adapter.addTab(title[2], "question", QuestionFragment.class,
                getBundle("latest"));
        adapter.addTab(title[3], "activity", EventFragment.class,
                getBundle("recommend"));
    }

    private Bundle getBundle(int newType) {
        Bundle bundle = new Bundle();
        bundle.putInt("BUNDLE_KEY_CATALOG", newType);
        return bundle;
    }

    @Override
    protected void setScreenPageLimit() {
        mViewPager.setOffscreenPageLimit(3);
    }

    /**
     * 基类会根据不同的catalog展示相应的数据
     *
     * @param catalog 要显示的数据类别
     * @return
     */
    private Bundle getBundle(String catalog) {
        Bundle bundle = new Bundle();
        bundle.putString("BUNDLE_BLOG_TYPE", catalog);
        return bundle;
    }

    @Override
    public void onClick(View v) {

    }


//    @Override
//    public void onTabReselect() {
//        Fragment fragment = mTabsAdapter.getItem(mViewPager.getCurrentItem());
//        if (fragment != null && fragment instanceof GeneralListFragment) {
//            ((GeneralListFragment) fragment).onTabReselect();
//        }
//    }
}