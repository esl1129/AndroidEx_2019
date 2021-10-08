package com.test.fragmentsample.fragment;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.test.fragmentsample.MainFragmentActivity;
import com.test.fragmentsample.OnBackPressedListener;
import com.test.fragmentsample.R;
import com.test.fragmentsample.adapter.UserDatabase;

import static android.content.ContentValues.TAG;

public class LoginFragment extends Fragment implements OnBackPressedListener {
    private View wrapper;
    private Button nextButton;
    private long backKeyPressedTime = 0;
    private Toast toast;

    private EditText idEdit;
    private EditText passwdEdit;
    private ImageView imageView;


    private UserDatabase db;
    private String phonenum;
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
        wrapper.setClickable(true);

        db = Room.databaseBuilder(wrapper.getContext(),
                UserDatabase.class, "User.db")
                .build();


        imageView = (ImageView)wrapper.findViewById(R.id.talkImage);
        imageView.setImageResource(R.drawable.talk);
        nextButton = (Button) wrapper.findViewById(R.id.btn_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idEdit = (EditText)wrapper.findViewById(R.id.idEdit);
                passwdEdit = (EditText)wrapper.findViewById(R.id.passwdEdit);

                password = passwdEdit.getText().toString();
                phonenum = idEdit.getText().toString();

                if (db.userDao().getPassword(phonenum) == password) {
                    MainFragment mainFragment = new MainFragment();

                    //데이터 넘길게 있다면,
                    Bundle bundle = new Bundle();
                    mainFragment.setArguments(bundle);
                    ((MainFragmentActivity) getActivity()).goToFragment(mainFragment);
                } else {
                    if(idEdit.getText().toString().matches("")){
                        toast = Toast.makeText(getActivity(),"아이디를 입력해주세요.",Toast.LENGTH_LONG);
                        toast.show();
                        return;
                    }else if(passwdEdit.getText().toString().matches("")){
                        toast = Toast.makeText(getActivity(),"비밀번호를 입력해주세요.",Toast.LENGTH_LONG);
                        toast.show();
                        return;
                    }else{
                        toast = Toast.makeText(getActivity(),"아이디 또는 비밀번호가 틀렸습니다.",Toast.LENGTH_LONG);
                        toast.show();
                        return;
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() > backKeyPressedTime + 2500){
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(getActivity(),"뒤로 가기 버튼을 한 번 더 누르시면 종료됩니다.",Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        if(System.currentTimeMillis() <= backKeyPressedTime + 2500){
            getActivity().finish();
            toast.cancel();
            toast = Toast.makeText(getActivity(),"이용해주셔서 감사합니다.",Toast.LENGTH_LONG);
            toast.show();
        }
    }
}