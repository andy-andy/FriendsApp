package com.tarapus.android.friends;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(android.R.id.content) == null) {
            FriendsListFragment friendsListFragment = new FriendsListFragment();
            fragmentManager.beginTransaction().add(android.R.id.content, friendsListFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.addRecord:
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                break;
            case R.id.deleteDatabase:
                FriendsDialog dialog = new FriendsDialog();
                Bundle args = new Bundle();
                args.putString(FriendsDialog.DIALOG_TYPE, FriendsDialog.DELETE_DATABASE);
                dialog.setArguments(args);
                dialog.show(getSupportFragmentManager(), "delete database");
                break;
            case R.id.searchRecord:
                Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchIntent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
