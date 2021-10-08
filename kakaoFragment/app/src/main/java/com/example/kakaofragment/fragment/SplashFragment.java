package com.example.kakaofragment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kakaofragment.MainFragmentActivity;
import com.example.kakaofragment.R;


public class SplashFragment extends Fragment {
    private View wrapper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        // ImageView imageView = (ImageView) wrapper.findViewById(R.id.talkImage);
        // setImageResource(R.drawable.talk);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        wrapper = inflater.inflate(getResources().getLayout(R.layout.fragment_splash), null);
        return wrapper;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wrapper.setClickable(true);

        final MapFragment mapFragment = new MapFragment();

        //데이터 넘길게 있다면,
        Bundle bundle = new Bundle();
        mapFragment.setArguments(bundle);

        wrapper.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((MainFragmentActivity) getActivity()).goToFragment(mapFragment);
            }
        },3000);
    }
}
