package com.example.kakaofragment.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kakaofragment.MainFragmentActivity;
import com.example.kakaofragment.R;
import com.example.kakaofragment.adapter.MyRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MainFragment extends Fragment {
    public View wrapper;
    private RecyclerView recyclerView;
    private MyRecyclerAdapter myRecyclerAdapter;
    private ArrayList items;
    private FirebaseFirestore firestore;
    private Map<String, Object> result;

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

        recyclerView = (RecyclerView) wrapper.findViewById(R.id.recyclerView);

        myRecyclerAdapter = new MyRecyclerAdapter();

        myRecyclerAdapter.setOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                ListItem item = (ListItem) items.get(position);
                if(item != null) {
                    ProfileFragment profileFragment = new ProfileFragment();

                    //데이터 넘길게 있다면,
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("item", item);
                    profileFragment.setArguments(bundle);
                    ((MainFragmentActivity) getActivity()).goToFragment(profileFragment);
                }
            }
        });
        items = new ArrayList<>();
        firestore = FirebaseFirestore.getInstance();
        firestore.collection("user")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                result = document.getData();
                                items.add(new ListItem(
                                        getResources().getIdentifier(result.get("src").toString(), "drawable",wrapper.getContext().getPackageName()),
                                        result.get("name").toString(),
                                        result.get("status").toString()
                                ));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        myRecyclerAdapter.setItems(items);
        recyclerView.setAdapter(myRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(wrapper.getContext()));
    }
}
