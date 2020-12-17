package com.example.stup.ui.notifications;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.stup.R;
import com.example.stup.ui.firebaseClass.User;
import com.example.stup.ui.firebaseClass.UserMod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.MODE_PRIVATE;

public class NotificationsFragment extends Fragment {
        Button choose_login,choose_register,logout,btn_login,btn_register;
        LinearLayout log_window;
        LinearLayout reg_window;
        LinearLayout log_ok;
        private User user;
        EditText username,password1_reg,password2_reg,phone,email;
        EditText username_log,password_log;
        CheckBox saveDataStatus;
    public NotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.fragment_notifications, container, false);

                //fragment_notifications config
                 choose_login=root.findViewById(R.id.login_btn_open);
                choose_register=root.findViewById(R.id.register_btn_open);
                logout=root.findViewById(R.id.logout_btn);
                log_window=root.findViewById(R.id.login_window);
                reg_window=root.findViewById(R.id.register_window);
                log_ok=root.findViewById(R.id.Cont_log);
                //register config

                btn_register=root.findViewById(R.id.register_btn);
                username=root.findViewById(R.id.editTextUsername);
                email=root.findViewById(R.id.editTextEmail);
                password1_reg=root.findViewById(R.id.editTextPassword);
                password2_reg=root.findViewById(R.id.editTextCnfPassword);
                phone=root.findViewById(R.id.editTextPhone);


             //    SharedPreferences sharedPreferences = getSharedPreferences("Stup",MODE_PRIVATE);
          //      boolean isLogin = sharedPreferences.getBoolean("isLogin", false);

                //Login config
                btn_login=root.findViewById(R.id.log_btn);
                username_log=root.findViewById(R.id.username_log);
                password_log=root.findViewById(R.id.parola);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
               DatabaseReference myRef = database.getReference("Register");


                choose_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                                reg_window.setVisibility(View.GONE);
                                log_window.setVisibility(View.VISIBLE);
                                choose_login.setVisibility(View.GONE);
                                choose_register.setVisibility(View.VISIBLE);
                    }
                });
                choose_register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        log_window.setVisibility(View.GONE);
                        reg_window.setVisibility(View.VISIBLE);
                        choose_login.setVisibility(View.VISIBLE);
                        choose_register.setVisibility(View.GONE);

                    }
                });
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        log_window.setVisibility(View.GONE);
                        reg_window.setVisibility(View.INVISIBLE);
                        choose_login.setVisibility(View.VISIBLE);
                        choose_register.setVisibility(View.VISIBLE);
                        log_ok.setVisibility(View.GONE);
                    }
                });
                btn_login.setOnClickListener(new View.OnClickListener() {
                    String pass_txt;

                    @Override
                    public void onClick(View v) {
                          pass_txt=password_log.getText().toString();
                        String user_txt=username_log.getText().toString();
                         //UserMod us=db.getUser(user_txt,pass_txt);
                            myRef.child(user_txt).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    User u=snapshot.getValue(User.class);
                                    if(pass_txt.equals(u.getPassword())){

                                       // Toast.makeText(login_act.this,"Login Succesfull",Toast.LENGTH_LONG).show();
                                        choose_register.setVisibility(View.GONE);
                                        log_window.setVisibility(View.GONE);
                                        reg_window.setVisibility(View.INVISIBLE);
                                        choose_login.setVisibility(View.INVISIBLE);
                                        log_ok.setVisibility(View.VISIBLE);

                                    }
                                    else
                                    {
                                     //   Toast.makeText(login_act.this,"parola gresita",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                    }
                });
                btn_register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String u=username.getText().toString();
                        String e=email.getText().toString();
                        String p1=password1_reg.getText().toString();
                        String p2=password2_reg.getText().toString();
                        String ph=phone.getText().toString();
                        user=new User(u,e,ph,p1);
                        myRef.child(user.getUsername()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                  //  Toast.makeText(NotificationsFragment.this, "User creat cu succes", Toast.LENGTH_SHORT).show();

                                }
                                else
                                {
                                    //Toast.makeText(NotificationsFragment.this, "Eroare de creare user", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }
                });

        return root;
    }

}