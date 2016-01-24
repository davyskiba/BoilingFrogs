package com.panoprogramowanie.boilingfrogs.ui.speakers;

import android.content.Context;
import android.os.Bundle;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.suppliers.SuppliersProvider;
import com.panoprogramowanie.boilingfrogs.ui.base.MvpView;
import com.panoprogramowanie.boilingfrogs.ui.list.ListFragment;
import com.panoprogramowanie.boilingfrogs.ui.list.ListItemModel;

/**
 * Created by Wojciech on 07.01.2016.
 */
public class SpeakersListFragment extends ListFragment{
    private SpeakersListPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SuppliersProvider provider=((SuppliersProvider) getActivity());
        presenter=new SpeakersListPresenter(provider.provideScheduleSupplier(),provider.provideNavigator());
        presenter.takeView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onItemClicked(ListItemModel itemModel) {
        Speaker clickedSpeaker=(Speaker)itemModel;
        presenter.speakerClicked(clickedSpeaker);
    }

    @Override
    protected int getListItemLayoutId() {
        return R.layout.list_item_speakers;
    }

    @Override
    public String getToolbarTitle(Context context) {
        return context.getString(R.string.drawer_item_speakers);
    }
}
