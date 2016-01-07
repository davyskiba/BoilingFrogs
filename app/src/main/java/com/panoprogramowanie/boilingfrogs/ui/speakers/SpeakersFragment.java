package com.panoprogramowanie.boilingfrogs.ui.speakers;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.ui.main.BoilingFrogsFragment;

/**
 * Created by Wojciech on 07.01.2016.
 */
public class SpeakersFragment extends BoilingFrogsFragment {

    @Override
    public boolean isToolbarBackEnabled() {
        return false;
    }

    @Override
    public int getToolbarTitle() {
        return R.string.drawer_item_speakers;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_speakers,container,false);
    }
}
