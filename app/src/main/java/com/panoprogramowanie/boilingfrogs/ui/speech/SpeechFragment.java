package com.panoprogramowanie.boilingfrogs.ui.speech;

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
public class SpeechFragment extends BoilingFrogsFragment {

    @Override
    public boolean isToolbarBackEnabled() {
        return true;
    }

    @Override
    public int getToolbarTitle() {
        return R.string.drawer_item_speech;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_speech,container,false);
    }
}
