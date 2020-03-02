package com.panoprogramowanie.boilingfrogs.ui.schedule;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panoprogramowanie.boilingfrogs.BoilingFrogs;
import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.ui.main.BoilingFrogsFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 07.01.2016.
 */
public class ScheduleFragment extends BoilingFrogsFragment {

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @Inject
    SchedulePresenter presenter;

    private int selectedItem;
    ScheduleFragmentPagerAdapter adapter;

    @Override
    public String getActionBarTitle(Context context) {
        return context.getString(R.string.drawer_item_schedule);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BoilingFrogs.getMainComponent(getActivity()).inject(this);
    }

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.schedule_fragment, container, false);
        ButterKnife.bind(this, result);

        adapter = new ScheduleFragmentPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        setupTabs();

        presenter.takeView(this);

        return result;
    }

    public void setData(List<SpeechSlot> slots) {
        adapter.setData(slots);

        resetTabs(slots);
    }

    //region Tabs

    private void resetTabs(List<SpeechSlot> slots) {
        int selectedTabPosition = selectedItem;

        tabLayout.removeAllTabs();
        addTabs(tabLayout, slots, selectedTabPosition);

        if (tabLayout.getTabCount() > selectedTabPosition && selectedTabPosition > 0) {
            tabLayout.getTabAt(selectedTabPosition).select();
            viewPager.setCurrentItem(selectedTabPosition);
            selectedItem = selectedTabPosition;
        }
    }

    private void addTabs(TabLayout tabLayout, List<SpeechSlot> slots, int selectedPosition) {
        for (int i = 0; i < slots.size(); i++) {
            TabLayout.Tab tab = tabLayout.newTab().setText(slots.get(i).getTimeLabel());
            if (i == selectedPosition) {
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
