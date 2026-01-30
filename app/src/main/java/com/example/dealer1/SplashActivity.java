package com.example.dealer1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {

    private static  final String myPreference = "preference";

    FirebaseAuth mAuth;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        if(getSupportActionBar() != null) getSupportActionBar().hide();

        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView textView = findViewById(R.id.textView);
        ImageView imageView = findViewById(R.id.imageView);





        imageView.setImageResource(R.drawable.appicon);

        executor.execute(() -> {

            try{
                runOnUiThread(() ->
                        textView.setText("Initializing app..."));
                TimeUnit.SECONDS.sleep(1);
                progressBar.setProgress(33);
                runOnUiThread(()->
                        textView.setText("Loading Product Data..."));
                TimeUnit.SECONDS.sleep(1);
                progressBar.setProgress(66);

                AppDatabase db = AppDatabase.getInstance(this);
                runOnUiThread(() ->
                        textView.setText("Almost Ready..."));
                TimeUnit.SECONDS.sleep(1);
                progressBar.setProgress(99);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



            runOnUiThread(() -> {

                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }

                SharedPreferences prefs = getSharedPreferences(myPreference, MODE_PRIVATE);
                boolean isLogin = prefs.getBoolean("isLogin", false);
                Intent i;
                if(!isLogin) {
                    i = new Intent(SplashActivity.this, LoginActivity.class);
                }
                else{
                    i = new Intent(SplashActivity.this, MainActivity.class);
                }
                startActivity(i);
                finish();
            });

        });

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        executor.shutdown();

    }
}