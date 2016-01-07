package com.panoprogramowanie.boilingfrogs.ui.schedule;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlots;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 07.01.2016.
 */
public class ScheduleFragment extends Fragment {

    @Bind(R.id.view_pager)
    ViewPager viewPager;

    ScheduleFragmentPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_schedule,container,false);
        ButterKnife.bind(this,result);

        adapter=new ScheduleFragmentPagerAdapter(getFragmentManager(), SpeechSlots.values());
        viewPager.setAdapter(adapter);

        return result;
    }
}
