package ru.myproject.ws_home_work6.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.myproject.ws_home_work6.R;
import ru.myproject.ws_home_work6.model.TokenResponse;
import ru.myproject.ws_home_work6.network.RestApi;
import ru.myproject.ws_home_work6.network.SingleResponseFlatMap;
import ru.myproject.ws_home_work6.network.service.AuthService;
import ru.myproject.ws_home_work6.ui.activities.InfoActivity;
import ru.myproject.ws_home_work6.ui.activities.LoginActivity;
import ru.myproject.ws_home_work6.utils.LoginController;


public class AuthorizationFragment extends Fragment {


    EditText text_login, text_password;
    Button btn_sing_in, btn_reg;
    AuthService authService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_authorization, container,
                false);
        text_login = v.findViewById(R.id.text_login);
        text_password = v.findViewById(R.id.text_password);
        btn_sing_in = v.findViewById(R.id.btn_sing_in);
        btn_reg = v.findViewById(R.id.btn_reg);

        authService = RestApi.createService(AuthService.class);
        btn_sing_in.setEnabled(false);
        Bundle bundle=getArguments();
        if(bundle!=null){
            text_login.setText(bundle.getString("login"));
            text_password.setText(bundle.getString("password"));
            btn_sing_in.setEnabled(true);
        }

        text_login.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    btn_sing_in.setEnabled(false);
                } else {
                    btn_sing_in.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        text_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    btn_sing_in.setEnabled(false);
                } else {
                    btn_sing_in.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_sing_in.setOnClickListener(v1 -> {
            setAuthService(text_login.getText().toString(), text_password.getText().toString());
        });


        btn_reg.setOnClickListener(v2 -> {
            RegistrationFragment registrationFragment = new RegistrationFragment();
            LoginActivity activity = (LoginActivity) getActivity();
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, registrationFragment)
                    .addToBackStack(null)
                    .commit();
        });


        return v;
    }

    public void setAuthService(String login, String password) {
        authService.singInByPassword(AuthService.TOKEN_PREFIX_BASIC +
                        "bXktY2xpZW50Om15LXNlY3JldA==", login, password,
                "password")
                .subscribeOn(Schedulers.io())
                .flatMap(new SingleResponseFlatMap<>())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<TokenResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(TokenResponse tokenResponse) {
                        LoginController.getOwrInstance().setLogin(text_login.getText().toString());

                        LoginController.getOwrInstance().setRefreshToken(tokenResponse.getRefreshToken());
                        LoginController.getOwrInstance().setAccessToken(tokenResponse.getAccessToken());

                        Intent intent = new Intent(getActivity(), InfoActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);


                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "Ошибка авторизации", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
