package com.panoprogramowanie.boilingfrogs.ui.schedule;

import android.app.Fragment;
import android.content.Context;
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

    private static String SLOT_POSITION_ARG_KEY="slot_position_arg";
    private static String SLOT_ARG_KEY="slot_arg";

    public static ScheduleSlotFragment createInstance(SpeechSlot slot, int slotPosition){
        Bundle args=new Bundle();
        args.putParcelable(SLOT_ARG_KEY, slot);
        args.putInt(SLOT_POSITION_ARG_KEY,slotPosition);

        ScheduleSlotFragment result=new ScheduleSlotFragment();
        result.setArguments(args);

        return result;
    }

    @Override
    protected ArrayAdapter<ListItemModel> getAdapter() {
        SpeechSlot slot=getArguments().getParcelable(SLOT_ARG_KEY);
        return new ListItemModelAdapter(getActivity(),R.layout.list_item_schedule,slot.getSpeeches());
    }

    @Override
    protected void onItemClicked(ListItemModel itemModel) {
        Speech clickedSpeech=(Speech)itemModel;
        int slotPosition=getArguments().getInt(SLOT_POSITION_ARG_KEY);

        ((SuppliersProvider) getActivity()).provideNavigator().navigateToSpeech(slotPosition,clickedSpeech.getPath());
    }

    @Override
    public String getToolbarTitle(Context context) {
        SpeechSlot slot=getArguments().getParcelable(SLOT_ARG_KEY);
        return slot.getHeader();
    }
}
