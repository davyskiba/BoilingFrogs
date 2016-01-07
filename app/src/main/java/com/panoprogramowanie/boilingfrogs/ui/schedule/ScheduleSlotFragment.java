package com.panoprogramowanie.boilingfrogs.ui.schedule;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.model.SpeechSlots;
import com.panoprogramowanie.boilingfrogs.navigation.Navigator;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 07.01.2016.
 */
public class ScheduleSlotFragment extends Fragment {

    private static String SLOT_ARG_KEY="slot_arg";

    public static ScheduleSlotFragment createInstance(SpeechSlots slot){
        Bundle args=new Bundle();
        args.putSerializable(SLOT_ARG_KEY, slot);

        ScheduleSlotFragment result=new ScheduleSlotFragment();
        result.setArguments(args);

        return result;
    }

    @Bind(R.id.button)
    Button button;

    @Bind(R.id.textView)
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_schedule_slot,container,false);
        ButterKnife.bind(this,result);

        SpeechSlots slot=(SpeechSlots)getArguments().getSerializable(SLOT_ARG_KEY);

        textView.setText(slot.getLabelId());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Navigator)getActivity()).navigateToSpeech();
            }
        });

        return result;
    }
}
