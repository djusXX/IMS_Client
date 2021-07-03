package com.example.ims_mobile_client;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        mainToolbar.setOnMenuItemClickListener(menuItem -> {
            int id = menuItem.getItemId();
            if (R.id.app_bar_search == id) {
                // TODO: implement
                return true;
            }
            if (R.id.app_bar_add_contact == id) {
                // TODO: implement
                return true;
            }
            if (R.id.app_bar_settings == id) {
                // TODO: implement
                return true;
            }
            if (R.id.app_bar_search == id) {
                // TODO: implement
                return true;
            }

            return false;
        });
    }



}