package com.panoprogramowanie.boilingfrogs.ui.list;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.ui.main.BoilingFrogsFragment;
import com.panoprogramowanie.boilingfrogs.ui.myschedule.recycler.DividerItemDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 11.01.2016.
 */
public abstract class ListFragment extends BoilingFrogsFragment {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private ListItemModelRecyclerViewAdapter adapter;

    @Override
    protected View onCreateFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.list_fragment, container, false);
        ButterKnife.bind(this, result);

        adapter = new ListItemModelRecyclerViewAdapter(getActivity(), R.layout.list_item);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL,R.drawable.list_divider));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ListItemModel clickedItem = adapter.getItem(position);
//                onItemClicked(clickedItem);
//            }
//        });

        return result;
    }

    public void setItems(List<? extends ListItemModel> items) {
        adapter.setItems(items);
    }

    protected abstract void onItemClicked(ListItemModel itemModel);

}
