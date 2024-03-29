package com.panoprogramowanie.boilingfrogs.ui.speakers;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.koin.KoinModule;
import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.ui.list.ListFragment;
import com.panoprogramowanie.boilingfrogs.ui.list.ListItemModel;

/**
 * Created by Wojciech on 07.01.2016.
 */
public class SpeakersListFragment extends ListFragment {

    SpeakersListPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = KoinModule.getSpeakersListPresenter();
    }


    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = super.onCreateFragmentView(inflater, container, savedInstanceState);
        presenter.takeView(this);
        return result;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onItemClicked(ListItemModel itemModel) {
        Speaker clickedSpeaker = (Speaker) itemModel;
        presenter.speakerClicked(clickedSpeaker);
    }

    @Override
    public String getActionBarTitle(Context context) {
        return context.getString(R.string.drawer_item_speakers);
    }
}
