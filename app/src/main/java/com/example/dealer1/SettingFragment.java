package com.example.dealer1;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Objects;



public class SettingFragment extends Fragment {



    TextView userName, userEmail;

    Button btnLogout, btnLogoutToMainActivity;
    SharedPreferences preferences;

    final private static String myPreference = "preference";




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        userName = view.findViewById(R.id.userNameSetting);
        userEmail = view.findViewById(R.id.emailIdSetting);
        btnLogout = view.findViewById(R.id.btnLogout);

        btnLogoutToMainActivity = view.findViewById(R.id.btnlogoutToMainActivity);

        SharedPreferences prefs = requireContext().getSharedPreferences(myPreference, MODE_PRIVATE);

        String name = prefs.getString("name",null);
        String email = prefs.getString("email", null);
        userName.setText(name);
        userEmail.setText(email);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isLogin", false);
                editor.apply();
                Intent i = new Intent(requireActivity(), SplashActivity.class);
                startActivity(i);
                requireActivity().finish();


            }
        });
        btnLogoutToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);
                requireActivity().finish();
            }
        });





        return view;
    }
}