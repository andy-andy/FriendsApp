package com.tarapus.android.friends;

import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by andreytarasenko on 4/1/15.
 */
public class AddActivity extends FragmentActivity {
    private final String LOG_TAG = AddActivity.class.getSimpleName();
    private TextView mNameTextView, mPhoneTextView, mEmailTextView;
    private Button mButton;
    private ContentResolver mContentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        mContentResolver = AddActivity.this.getContentResolver();

        mNameTextView = (TextView) findViewById(R.id.friend_name);
        mPhoneTextView = (TextView) findViewById(R.id.friend_phone);
            mEmailTextView = (TextView) findViewById(R.id.friend_email);

        mButton = (Button) findViewById(R.id.saveButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    ContentValues values = new ContentValues();
                    values.put(FriendsContract.FriendsColumns.FRIENDS_NAME, mNameTextView.getText().toString());
                    values.put(FriendsContract.FriendsColumns.FRIENDS_PHONE, mPhoneTextView.getText().toString());
                    values.put(FriendsContract.FriendsColumns.FRIENDS_EMAIL, mEmailTextView.getText().toString());
                    Uri returned = mContentResolver.insert(FriendsContract.URI_TABLE, values);
                    Log.d(LOG_TAG, "record id returned is " + returned.toString());
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Make sure you entered valid data!", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private boolean isValid() {
        if (mNameTextView.getText().toString().length() == 0 ||
                mPhoneTextView.getText().toString().length() == 0 ||
                mEmailTextView.getText().toString().length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean someDataEntered(){
        if(mNameTextView.getText().toString().length() > 0 ||
                mPhoneTextView.getText().toString().length() > 0 ||
                mEmailTextView.getText().toString().length() > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        if(someDataEntered()){
            FriendsDialog dialog = new FriendsDialog();
            Bundle args = new Bundle();
            args.putString(FriendsDialog.DIALOG_TYPE, FriendsDialog.CONFIRM_EXIT);
            dialog.setArguments(args);
            dialog.show(getSupportFragmentManager(), "confirm-exit");
        }else{
            super.onBackPressed();
        }
    }
}