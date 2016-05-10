package com.panoprogramowanie.boilingfrogs.ui.speechslot;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panoprogramowanie.boilingfrogs.BoilingFrogs;
import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.ui.list.ListFragment;
import com.panoprogramowanie.boilingfrogs.ui.list.ListItemModel;

import javax.inject.Inject;

/**
 * Created by Wojciech on 07.01.2016.
 */
public class SpeechSlotFragment extends ListFragment {
    private static String SLOT_ID_ARG_KEY = "slot_id_arg";
    private static String SLOT_TITLE_ARG_KEY = "slot_title_arg";

    public static SpeechSlotFragment createInstance(SpeechSlot speechSlot) {
        Bundle args = new Bundle();
        args.putLong(SLOT_ID_ARG_KEY, speechSlot.getId());
        args.putString(SLOT_TITLE_ARG_KEY, speechSlot.getHeader());

        SpeechSlotFragment result = new SpeechSlotFragment();
        result.setArguments(args);

        return result;
    }

    @Inject
    SpeechSlotPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BoilingFrogs.getMainComponent(getActivity()).inject(this);
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

        long slotId = getArguments().getLong(SLOT_ID_ARG_KEY);
        presenter.onResume(slotId);
    }

    @Override
    protected void onItemClicked(ListItemModel itemModel) {
        Speech clickedSpeech = (Speech) itemModel;

        presenter.speechClicked(clickedSpeech);
    }

    @Override
    protected int getListItemLayoutId() {
        return R.layout.schedule_list_item;
    }

    @Override
    public String getActionBarTitle(Context context) {
        return getArguments().getString(SLOT_TITLE_ARG_KEY);
    }
}
