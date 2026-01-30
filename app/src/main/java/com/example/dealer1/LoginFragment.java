package com.example.dealer1;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginFragment extends Fragment {


    private  static final String adminEmail = "Admin@gmail.com";
    private  static final String adminPass = "admin@123";

    AppDatabase db;

    private static  final String myPreference = "preference";




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    EditText email, password;
    Button btnLogin;
    TextView goToSignup;
    FirebaseAuth mAuth;


    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);

        email = v.findViewById(R.id.emailLogin);
        password = v.findViewById(R.id.passLogin);
        btnLogin = v.findViewById(R.id.btnLogin);
        goToSignup = v.findViewById(R.id.goToSignup);

        mAuth = FirebaseAuth.getInstance();

        db = AppDatabase.getInstance(getContext());

        btnLogin.setOnClickListener(view -> {
            String etEmail = email.getText().toString().trim();
            String etPass = password.getText().toString().trim();


            User user = db.userDao().login(etEmail, etPass);

            if(etEmail.equals(adminEmail) && etPass.equals(adminPass)){
                startActivity(new Intent(requireContext(), AdminActivity.class));
            }
            if(etEmail.isEmpty() || etPass.isEmpty()){
                Toast.makeText(requireActivity(), "Enter Email and Password", Toast.LENGTH_SHORT).show();


            }else{

            }
            if (user != null) {


                // Save with SharedPreferences
                SharedPreferences prefs = requireActivity().getSharedPreferences(myPreference, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isLogin", true);
                editor.putString("email", etEmail );
                editor.putString("password", etPass);
                editor.apply();



                Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                mAuth.signInWithEmailAndPassword(etEmail, etPass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(requireActivity(), "Login Success",
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(requireActivity(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


                startActivity(new Intent(requireActivity() , MainActivity.class));
                requireActivity().finish();
            } else {
                mAuth.signInWithEmailAndPassword(etEmail, etPass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(requireActivity(), "Login Success",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(requireActivity() , MainActivity.class));
                                    requireActivity().finish();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(requireActivity(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                Toast.makeText(getContext(), "Something wrong", Toast.LENGTH_SHORT).show();
            }

        });

        goToSignup.setOnClickListener(view -> {
            ((LoginActivity) getActivity()).switchFragment(new SignUpFragment());
        });

        return v;
    }
}