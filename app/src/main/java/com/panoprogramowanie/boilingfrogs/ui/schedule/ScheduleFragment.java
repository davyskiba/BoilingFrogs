package com.panoprogramowanie.boilingfrogs.ui.schedule;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.suppliers.SuppliersProvider;
import com.panoprogramowanie.boilingfrogs.ui.main.BoilingFrogsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 07.01.2016.
 */
public class ScheduleFragment extends BoilingFrogsFragment {

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    private SchedulePresenter presenter;

    private int selectedItem;
    ScheduleFragmentPagerAdapter adapter;

    @Override
    public String getActionBarTitle(Context context) {
        return context.getString(R.string.drawer_item_schedule);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SuppliersProvider provider=((SuppliersProvider) getActivity());
        presenter=new SchedulePresenter(provider.provideScheduleSupplier());
    }

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.bind(this, result);

        adapter=new ScheduleFragmentPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        setupTabs();

        presenter.takeView(this);

        return result;
    }

    public void setData(SpeechSlot[] slots) {
        adapter.setData(slots);

        resetTabs(slots);
    }

    //region Tabs

    private void resetTabs(SpeechSlot[] slots) {
        int selectedTabPosition=selectedItem;

        tabLayout.removeAllTabs();
        addTabs(tabLayout, slots, selectedTabPosition);

        if(tabLayout.getTabCount()>selectedTabPosition && selectedTabPosition>0) {
            tabLayout.getTabAt(selectedTabPosition).select();
            viewPager.setCurrentItem(selectedTabPosition);
            selectedItem=selectedTabPosition;
        }
    }

    private void addTabs(TabLayout tabLayout, SpeechSlot[] slots, int selectedPosition){
        for(int i=0;i<slots.length;i++)
        {
            TabLayout.Tab tab=tabLayout.newTab().setText(slots[i].getHeader());
            if(i==selectedPosition) {
                tab.select();
            }
            tabLayout.addTab(tab);
        }
    }

    private void setupTabs() {
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedItem = tab.getPosition();
                viewPager.setCurrentItem(selectedItem);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //endregion
}
