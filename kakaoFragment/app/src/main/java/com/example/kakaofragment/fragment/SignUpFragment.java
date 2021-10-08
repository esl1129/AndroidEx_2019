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
import com.example.kakaofragment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class SignUpFragment extends Fragment {
    private View wrapper;
    private EditText emailEdit;
    private EditText passwdEdit;
    private EditText passwdEdit2;
    private ImageView imageView;
    private Button signupButton;

    private String email;
    private String password;
    private String password2;

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        wrapper = inflater.inflate(getResources().getLayout(R.layout.fragment_signup), null);
        return wrapper;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        wrapper.setClickable(true);

        imageView = (ImageView)wrapper.findViewById(R.id.talkImage);
        imageView.setImageResource(R.drawable.talk);
        signupButton = (Button) wrapper.findViewById(R.id.btn_signUp);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailEdit = (EditText)wrapper.findViewById(R.id.idEdit);
                passwdEdit = (EditText)wrapper.findViewById(R.id.passwdEdit);
                passwdEdit2 = (EditText)wrapper.findViewById(R.id.passwdEdit2);
                email = emailEdit.getText().toString();
                password = passwdEdit.getText().toString();
                password2 = passwdEdit2.getText().toString();

                if(password.matches(password2)){
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener((Activity) wrapper.getContext(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");

                                        MainFragment mainFragment = new MainFragment();

                                        //데이터 넘길게 있다면,
                                        Bundle bundle = new Bundle();
                                        mainFragment.setArguments(bundle);
                                        ((MainFragmentActivity) getActivity()).goToFragment(mainFragment);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(wrapper.getContext(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                }else{
                    Toast.makeText(wrapper.getContext(), "재확인 비밀번호 오류",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
