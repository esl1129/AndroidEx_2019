package com.example.kakaofragment.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kakaofragment.MainFragmentActivity;
import com.example.kakaofragment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {
    private View wrapper;

    private ImageView imageView;
    private TextView nameText;
    private TextView statusText;
    private ImageButton chatBtn;
    private ImageButton callBtn;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        wrapper = inflater.inflate(getResources().getLayout(R.layout.fragment_profile), null);
        return wrapper;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        wrapper.setClickable(true);

        imageView = (ImageView)wrapper.findViewById(R.id.profileImage);
        nameText = (TextView)wrapper.findViewById(R.id.profileId);
        statusText = (TextView)wrapper.findViewById(R.id.profileStatus);

        chatBtn = (ImageButton)wrapper.findViewById(R.id.chatButton);
        callBtn = (ImageButton)wrapper.findViewById(R.id.callButton);

        Bundle bundle = getArguments();
        ListItem item = (ListItem)bundle.getSerializable("item");

        imageView.setImageResource(item.getProfile());
        nameText.setText(item.getId());
        statusText.setText(item.getStatus());
    }

}
