package com.panoprogramowanie.boilingfrogs.ui.schedule;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.Speech;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlot;
import com.panoprogramowanie.boilingfrogs.suppliers.SuppliersProvider;
import com.panoprogramowanie.boilingfrogs.ui.view.ListItemModelAdapter;
import com.panoprogramowanie.boilingfrogs.ui.view.ListItemModelView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 07.01.2016.
 */
public class ScheduleSlotFragment extends Fragment {

    private static String SLOT_ARG_KEY="slot_arg";

    public static ScheduleSlotFragment createInstance(SpeechSlot slot){
        Bundle args=new Bundle();
        args.putParcelable(SLOT_ARG_KEY, slot);

        ScheduleSlotFragment result=new ScheduleSlotFragment();
        result.setArguments(args);

        return result;
    }

    @Bind(R.id.listView)
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_schedule_slot, container, false);
        ButterKnife.bind(this, result);

        SpeechSlot slot=getArguments().getParcelable(SLOT_ARG_KEY);

        listView.setAdapter(new ListItemModelAdapter(getActivity(),slot.getSpeeches()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItemModelView speechListItem=(ListItemModelView)view;
                onSpeechClicked((Speech) speechListItem.getModel());
            }
        });

        return result;
    }

    private void onSpeechClicked(Speech speech)
    {
        ((SuppliersProvider)getActivity()).provideNavigator().navigateToSpeech(speech);
    }
}
