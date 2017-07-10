package com.example.lorena.challengifier.activities.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.utils.session.SessionUser;

public class MyPerformancesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_performances);
        TextView user = (TextView) findViewById(R.id.textViewUser);
        user.setText(SessionUser.currentUserId);
    }
}
