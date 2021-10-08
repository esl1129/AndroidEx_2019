package com.test.fragmentsample.fragment;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.test.fragmentsample.MainFragmentActivity;
import com.test.fragmentsample.R;

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

        final LoginFragment loginFragment = new LoginFragment();

        //데이터 넘길게 있다면,
        Bundle bundle = new Bundle();
        loginFragment.setArguments(bundle);

        wrapper.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((MainFragmentActivity) getActivity()).goToFragment(loginFragment);
            }
        },3000);
    }
}
