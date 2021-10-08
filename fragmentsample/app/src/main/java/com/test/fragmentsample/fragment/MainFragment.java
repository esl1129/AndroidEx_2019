package com.test.fragmentsample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.test.fragmentsample.R;
import com.test.fragmentsample.adapter.MyRecyclerAdapter;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    public View wrapper;
    private RecyclerView recyclerView;
    private MyRecyclerAdapter myRecyclerAdapter;
    private ArrayList items;

    private Toast toast;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        wrapper = inflater.inflate(getResources().getLayout(R.layout.fragment_main), null);
        return wrapper;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wrapper.setClickable(true);

        recyclerView = (RecyclerView)wrapper.findViewById(R.id.recyclerView);

        myRecyclerAdapter = new MyRecyclerAdapter();

        myRecyclerAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                toast = Toast.makeText(wrapper.getContext(),position+1 + "번째 인자 호출",Toast.LENGTH_LONG);
                toast.show();
            }
        });
        recyclerView.setAdapter(myRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(wrapper.getContext()));

        items = new ArrayList<>();
        for(int i=1;i<16;i++){
            items.add(new ListItem (R.drawable.p_1,"User" + i, "Status " + i));
        }
        myRecyclerAdapter.setItems(items);
    }
}
