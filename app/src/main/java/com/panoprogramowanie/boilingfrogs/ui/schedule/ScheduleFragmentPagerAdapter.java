package com.panoprogramowanie.boilingfrogs.ui.schedule;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;

/**
 * Created by Wojciech on 07.01.2016.
 */
public class ScheduleFragmentPagerAdapter extends FragmentPagerAdapter {


    private SpeechSlot[] slots;

    public ScheduleFragmentPagerAdapter(FragmentManager fm,SpeechSlot[] slots) {
        super(fm);

        this.slots=slots;
    }

    @Override
    public Fragment getItem(int position) {
        return ScheduleSlotFragment.createInstance(slots[position]);
    }

    @Override
    public int getCount() {
        return slots.length;
    }
}
