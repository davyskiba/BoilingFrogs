package com.panoprogramowanie.boilingfrogs.ui.speechslot;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.suppliers.SuppliersProvider;
import com.panoprogramowanie.boilingfrogs.ui.list.ListFragment;
import com.panoprogramowanie.boilingfrogs.ui.list.ListItemModel;

/**
 * Created by Wojciech on 07.01.2016.
 */
public class SpeechSlotFragment extends ListFragment {

    private static String SLOT_POSITION_ARG_KEY="slot_position_arg";
    private static String SLOT_ARG_KEY="slot_arg";

    public static SpeechSlotFragment createInstance(SpeechSlot slot, int slotPosition){
        Bundle args=new Bundle();
        args.putParcelable(SLOT_ARG_KEY, slot);
        args.putInt(SLOT_POSITION_ARG_KEY, slotPosition);

        SpeechSlotFragment result=new SpeechSlotFragment();
        result.setArguments(args);

        return result;
    }

    SpeechSlotPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SuppliersProvider suppliersProvider=((SuppliersProvider) getActivity());
        presenter=new SpeechSlotPresenter(suppliersProvider.provideNavigator());
    }

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    View result=super.onCreateFragmentView(inflater, container, savedInstanceState);
        presenter.takeView(this);
        return result;
    }

    @Override
    public void onResume() {
        super.onResume();

        SpeechSlot slot=getArguments().getParcelable(SLOT_ARG_KEY);
        int slotPosition=getArguments().getInt(SLOT_POSITION_ARG_KEY);

        presenter.onResume(slot,slotPosition);
    }

    @Override
    protected void onItemClicked(ListItemModel itemModel) {
        Speech clickedSpeech=(Speech)itemModel;

        presenter.speechClicked(clickedSpeech);
    }

    @Override
    protected int getListItemLayoutId() {
        return R.layout.list_item_schedule;
    }

    @Override
    public String getToolbarTitle(Context context) {
        SpeechSlot slot=getArguments().getParcelable(SLOT_ARG_KEY);
        return slot.getHeader();
    }
}
