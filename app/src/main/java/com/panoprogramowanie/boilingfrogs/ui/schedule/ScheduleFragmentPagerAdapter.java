package com.panoprogramowanie.boilingfrogs.ui.schedule;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.ui.speechslot.SpeechSlotFragment;

/**
 * Created by Wojciech on 07.01.2016.
 */
public class ScheduleFragmentPagerAdapter extends FragmentPagerAdapter {


    private SpeechSlot[] slots;

    public ScheduleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);

        this.slots = new SpeechSlot[0];
    }

    @Override
    public Fragment getItem(int position) {
        return SpeechSlotFragment.createInstance(slots[position]);
    }

    @Override
    public int getCount() {
        return slots.length;
    }

    public void setData(SpeechSlot[] slots) {
        this.slots = slots;
        notifyDataSetChanged();
    }
}
