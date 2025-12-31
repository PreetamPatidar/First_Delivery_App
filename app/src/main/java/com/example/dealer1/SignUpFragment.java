package com.example.dealer1;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class SignUpFragment extends Fragment {



    private static  final String myPreference = "preference";
    static UserDao dao;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase db = AppDatabase.getInstance(getContext());
        dao= db.userDao();
    }

    EditText name, email, password;
    Button btnSignup;
    TextView goToLogin;


    @Override
    public View onCreateView(LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);

        name = v.findViewById(R.id.nameSignup);
        email = v.findViewById(R.id.emailSignup);
        password = v.findViewById(R.id.passSignup);
        btnSignup = v.findViewById(R.id.btnSignup);
        goToLogin = v.findViewById(R.id.goToLogin);

        btnSignup.setOnClickListener(view -> {
            String etName = name.getText().toString().trim();
            String etEmail = email.getText().toString().trim();
            String etPass = password.getText().toString().trim();

            if (etName.isEmpty() || etEmail.isEmpty() || etPass.isEmpty()) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }



            User already = dao.getUserByEmail(etEmail);
            if (already != null) {
                Toast.makeText(getContext(), "User already exists", Toast.LENGTH_SHORT).show();
                return;
            }

            dao.insertUser(new User(etName, etEmail, etPass));



            Toast.makeText(getContext(), "Signup Successful", Toast.LENGTH_SHORT).show();
            SharedPreferences prefs = requireActivity().getSharedPreferences(myPreference, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("name", etName);
            editor.apply();

            ((LoginActivity)getActivity()).switchFragment(new LoginFragment());


        });

        goToLogin.setOnClickListener(view -> {
            ((LoginActivity) getActivity()).switchFragment(new LoginFragment());
        });

        return v;
    }
}