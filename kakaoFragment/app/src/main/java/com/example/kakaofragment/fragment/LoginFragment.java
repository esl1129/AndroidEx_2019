package com.example.kakaofragment.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kakaofragment.MainFragmentActivity;
import com.example.kakaofragment.OnBackPressedListener;
import com.example.kakaofragment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class LoginFragment extends Fragment{
    private View wrapper;
    private Button nextButton;
    private Button signupButton;
    private long backKeyPressedTime = 0;
    private Toast toast;

    private EditText idEdit;
    private EditText passwdEdit;
    private ImageView imageView;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private String email;
    private String password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        wrapper = inflater.inflate(getResources().getLayout(R.layout.fragment_login), null);
        return wrapper;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        wrapper.setClickable(true);

        imageView = (ImageView)wrapper.findViewById(R.id.talkImage);
        imageView.setImageResource(R.drawable.talk);
        nextButton = (Button) wrapper.findViewById(R.id.btn_signIn);
        signupButton = (Button) wrapper.findViewById(R.id.btn_signUp);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idEdit = (EditText)wrapper.findViewById(R.id.idEdit);
                passwdEdit = (EditText)wrapper.findViewById(R.id.passwdEdit);

                password = passwdEdit.getText().toString();
                email = idEdit.getText().toString();
                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener((Activity) wrapper.getContext(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    user = mAuth.getCurrentUser();
                                }
                            }
                        });

                if (user != null) {
                    MapFragment mapFragment = new MapFragment();

                    //데이터 넘길게 있다면,
                    Bundle bundle = new Bundle();
                    mapFragment.setArguments(bundle);
                    ((MainFragmentActivity) getActivity()).goToFragment(mapFragment);
                } else {
                    if(idEdit.getText().toString().matches("")){
                        toast = Toast.makeText(getActivity(),"아이디를 입력해주세요.", Toast.LENGTH_LONG);
                        toast.show();
                        return;
                    }else if(passwdEdit.getText().toString().matches("")){
                        toast = Toast.makeText(getActivity(),"비밀번호를 입력해주세요.", Toast.LENGTH_LONG);
                        toast.show();
                        return;
                    }else{
                        toast = Toast.makeText(getActivity(),"아이디 또는 비밀번호가 틀렸습니다.", Toast.LENGTH_LONG);
                        toast.show();
                        return;
                    }
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpFragment signUpFragment = new SignUpFragment();

                //데이터 넘길게 있다면,
                Bundle bundle = new Bundle();
                signUpFragment.setArguments(bundle);
                ((MainFragmentActivity) getActivity()).goToFragment(signUpFragment);
            }
        });
    }

}