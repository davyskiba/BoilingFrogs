package com.panoprogramowanie.boilingfrogs.ui.schedule;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.suppliers.SuppliersProvider;
import com.panoprogramowanie.boilingfrogs.ui.list.ListFragment;
import com.panoprogramowanie.boilingfrogs.ui.list.ListItemModel;
import com.panoprogramowanie.boilingfrogs.ui.list.ListItemModelAdapter;
import com.panoprogramowanie.boilingfrogs.ui.list.ListItemModelView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 07.01.2016.
 */
public class ScheduleSlotFragment extends ListFragment {

    private static String SLOT_ARG_KEY="slot_arg";

    public static ScheduleSlotFragment createInstance(SpeechSlot slot){
        Bundle args=new Bundle();
        args.putParcelable(SLOT_ARG_KEY, slot);

        ScheduleSlotFragment result=new ScheduleSlotFragment();
        result.setArguments(args);

        return result;
    }

    @Override
    protected ArrayAdapter<ListItemModel> getAdapter() {
        SpeechSlot slot=getArguments().getParcelable(SLOT_ARG_KEY);
        return new ListItemModelAdapter(getActivity(),slot.getSpeeches());
    }

    @Override
    protected void onItemClicked(ListItemModel itemModel) {
        Speech clickedSpeech=(Speech)itemModel;
        ((SuppliersProvider) getActivity()).provideNavigator().navigateToSpeech(clickedSpeech);
    }
}
