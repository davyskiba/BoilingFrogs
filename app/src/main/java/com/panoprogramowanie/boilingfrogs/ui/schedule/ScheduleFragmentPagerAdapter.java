package com.panoprogramowanie.boilingfrogs.ui.schedule;


import android.app.Fragment;
import android.app.FragmentManager;

import androidx.legacy.app.FragmentPagerAdapter;

import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.ui.speechslot.SpeechSlotFragment;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wojciech on 07.01.2016.
 */
public class ScheduleFragmentPagerAdapter extends FragmentPagerAdapter {


    private List<SpeechSlot> slots;

    public ScheduleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);

        this.slots = new LinkedList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return SpeechSlotFragment.createInstance(slots.get(position));
    }

    @Override
    public int getCount() {
        return slots.size();
    }

    public void setData(List<SpeechSlot> slots) {
        this.slots = slots;
        notifyDataSetChanged();
    }
}
