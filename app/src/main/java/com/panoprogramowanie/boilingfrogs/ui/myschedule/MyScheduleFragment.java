package com.panoprogramowanie.boilingfrogs.ui.myschedule;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.koin.KoinModule;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.ui.main.BoilingFrogsFragment;
import com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler.MyScheduleRecyclerViewAdapter;
import com.panoprogramowanie.boilingfrogs.util.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 13.01.2016.
 */
public class MyScheduleFragment extends BoilingFrogsFragment implements MyScheduleRecyclerViewAdapter.OnSlotClickListener {
    @Override
    public String getActionBarTitle(Context context) {
        return context.getString(R.string.drawer_item_my_schedule);
    }

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private MyScheduleRecyclerViewAdapter adapter;

    MySchedulePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = KoinModule.getMySchedulePresenter();
    }

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.my_schedule_fragment, null);
        ButterKnife.bind(this, result);

        setupRecyclerView();

        presenter.takeView(this);

        return result;
    }

    private void setupRecyclerView() {
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.my_schedule_divider));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new MyScheduleRecyclerViewAdapter();
        adapter.setOnSlotClickListener(this);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void setSpeechSlots(List<SpeechSlot> slots) {
        adapter.setItems(slots);
    }

    @Override
    public void onEmptySlotClicked(SpeechSlot slot) {
        presenter.onEmptySlotClicked(slot);
    }

    @Override
    public void onNonEmptySlotClicked(long speechId) {
        presenter.onNonEmptySlotClicked(speechId);
    }
}
