package com.example.lorena.challengifier.activities.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lorena.challengifier.R;
import com.example.lorena.challengifier.activities.MainMenuActivity;
import com.example.lorena.challengifier.utils.temp.UserTemp;

public class RegisterActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText = (EditText) findViewById(R.id.editTextName);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    UserTemp.setCurrentUser(editText.getText().toString());
                    Toast.makeText(v.getContext(), "Welcome,"+UserTemp.getCurrentUser()+"!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent( v.getContext(), MainMenuActivity.class);
                    startActivityForResult(intent,0);
                    return true;
                }
                return false;
            }
        });
    }
}
