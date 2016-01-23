package com.panoprogramowanie.boilingfrogs.ui.speakers;

import android.content.Context;
import android.widget.BaseAdapter;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speaker;
import com.panoprogramowanie.boilingfrogs.suppliers.SuppliersProvider;
import com.panoprogramowanie.boilingfrogs.ui.list.ListFragment;
import com.panoprogramowanie.boilingfrogs.ui.list.ListItemModel;
import com.panoprogramowanie.boilingfrogs.ui.list.ListItemModelAdapter;

/**
 * Created by Wojciech on 07.01.2016.
 */
public class SpeakersFragment extends ListFragment {

    @Override
    protected BaseAdapter getAdapter() {
        Speaker[] speakers=((SuppliersProvider)getActivity()).provideScheduleSupplier().getAllSpeakers();
        return new ListItemModelAdapter(getActivity(),R.layout.list_item_speakers,speakers);
    }


    @Override
    protected void onItemClicked(ListItemModel itemModel) {
        Speaker clickedSpeaker=(Speaker)itemModel;
        ((SuppliersProvider) getActivity()).provideNavigator().navigateToSpeaker(clickedSpeaker);
    }


    @Override
    public String getToolbarTitle(Context context) {
        return context.getString(R.string.drawer_item_speakers);
    }
}
