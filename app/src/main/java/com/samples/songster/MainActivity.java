package com.samples.songster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.samples.songster.details.SongDetailsFragment;
import com.samples.songster.search.SearchFragment;
import com.samples.songster.settings.SettingsActivity;

public class MainActivity extends ActionBarActivity implements SearchFragment.SearchFragmentListener {

    public static final String TAG_MAIN_FRAGMENT = "MainFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check for a saved instance state before adding the fragment. Doing this prevents the fragment's onCreate() method
        //from being called twice when a device rotation occurs. After a rotation, the fragment's onCreate() is called first
        //(during which the savedInstanceState is passed to the Fragment) followed by the Activity's onCreate().
        //If we add or replace the fragment in the Activity's onCreate(), the fragment's
        //onCreate() will be called twice and the second call will not pass a savedInstanceState to the fragment thus making
        //state restoration during rotation not work. So check for the savedInstanceState here before deciding on whether or not
        //to add the fragment.
        if(savedInstanceState == null) {
            Fragment mainFragment = getSupportFragmentManager().findFragmentByTag(TAG_MAIN_FRAGMENT);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (mainFragment != null) {
                ft.replace(R.id.fragmentContainer, MainFragment.getInstance(), TAG_MAIN_FRAGMENT);
            } else {
                ft.add(R.id.fragmentContainer, MainFragment.getInstance(), TAG_MAIN_FRAGMENT);
            }
            ft.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showSongDetails() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, SongDetailsFragment.getInstance());
        ft.addToBackStack(null);
        ft.commit();
    }
}
