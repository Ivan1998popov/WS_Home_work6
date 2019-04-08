package ru.myproject.ws_home_work6.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.myproject.ws_home_work6.R;
import ru.myproject.ws_home_work6.ui.fragments.AuthorizationFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AuthorizationFragment authorizationFragment =new AuthorizationFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,authorizationFragment)
                .commit();

    }




}
