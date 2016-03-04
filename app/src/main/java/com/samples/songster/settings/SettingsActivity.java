package com.samples.songster.settings;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.samples.songster.R;
import com.samples.songster.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    private SettingsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActivitySettingsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);
        viewModel = new SettingsViewModel(new PreferencesSettingsRepository());
        UserModel user = new UserModel();
        user.setFirstName("Charles");
        user.setLastName("McBacon");
        viewModel.setUser(user);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onResume(){
        super.onResume();
        viewModel.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        viewModel.onPause();
    }
}
