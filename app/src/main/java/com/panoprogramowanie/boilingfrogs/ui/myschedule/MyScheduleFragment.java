package com.panoprogramowanie.boilingfrogs.ui.myschedule;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.suppliers.SuppliersProvider;
import com.panoprogramowanie.boilingfrogs.ui.main.BoilingFrogsFragment;
import com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler.DividerItemDecoration;
import com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler.MyScheduleRecyclerViewAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 13.01.2016.
 */
public class MyScheduleFragment extends BoilingFrogsFragment implements MyScheduleRecyclerViewAdapter.OnSlotClickListener {
    @Override
    public String getToolbarTitle(Context context) {
        return context.getString(R.string.drawer_item_my_schedule);
    }

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    MyScheduleRecyclerViewAdapter adapter;

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_my_schedule, null);
        ButterKnife.bind(this, result);

        SpeechSlot[] slots=((SuppliersProvider)getActivity()).provideScheduleSupplier().getAllSpeechSlots();

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter=new MyScheduleRecyclerViewAdapter(slots);
        adapter.setOnSlotClickListener(this);

        recyclerView.setAdapter(adapter);


        return result;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onEmptySlotClicked(int slotPosition) {
        ((SuppliersProvider)getActivity()).provideNavigator().navigateToSlotDetail(slotPosition);
    }

    @Override
    public void onNonEmptySlotClicked(int slotPosition) {
        SpeechSlot tappedSlot=adapter.getItem(slotPosition);
        ((SuppliersProvider) getActivity()).provideNavigator().navigateToSpeech(slotPosition,tappedSlot.getFavoriteSpeechPath());
    }
}
