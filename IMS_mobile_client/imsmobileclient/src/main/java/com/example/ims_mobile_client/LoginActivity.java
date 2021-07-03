package com.example.ims_mobile_client;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // CLEAR BUTTON
        findViewById(R.id.buttonClear).setOnClickListener(view -> {
            LinearLayout loginLayout = (LinearLayout) findViewById(R.id.loginLayout);
            for(int i = 0; i < loginLayout.getChildCount(); i++) {
                if(loginLayout.getChildAt(i) instanceof LinearLayout) {
                    LinearLayout childLayout = (LinearLayout) loginLayout.getChildAt(i);
                    if (childLayout.getChildCount() > 1) {
                        EditText et = (EditText) childLayout.getChildAt(1);
                        et.setText("");
                    }

                }
            }
        });

        // LOG IN BUTTON
        findViewById(R.id.buttonLogIn).setOnClickListener(view -> {
            User userCfg = new User();
            try {
                /*
                * TODO:
                *  - Logging method
                *  - save in DB method
                * */

                setContentView(R.layout.activity_main);
            } catch (Exception e) {
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("Cannot log in to server")
                        .setMessage("error: " + e.toString())
                        .setNeutralButton("CLOSE", (dialog, which) -> dialog.dismiss())
                        .show();
            }
        });

    }




}