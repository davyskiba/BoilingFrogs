package com.panoprogramowanie.boilingfrogs.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.panoprogramowanie.boilingfrogs.R;
import com.panoprogramowanie.boilingfrogs.ui.main.BoilingFrogsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wojciech on 11.01.2016.
 */
public abstract class ListFragment extends BoilingFrogsFragment {

    @Bind(R.id.listView)
    ListView listView;

    private ListItemModelAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, result);

        int listItemLayoutId=getListItemLayoutId();
        adapter= new ListItemModelAdapter(getActivity(),listItemLayoutId);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListItemModel clickedItem=adapter.getItem(position);
                onItemClicked(clickedItem);
            }
        });

        return result;
    }

    protected void setItems(ListItemModel[] items){
        adapter.setItems(items);
    }

    protected abstract int getListItemLayoutId();

    protected abstract void onItemClicked(ListItemModel itemModel);

}
