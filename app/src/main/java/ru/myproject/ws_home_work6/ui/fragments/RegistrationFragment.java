package ru.myproject.ws_home_work6.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Body;
import ru.myproject.ws_home_work6.R;
import ru.myproject.ws_home_work6.model.Registration;
import ru.myproject.ws_home_work6.model.StatusRegistration;
import ru.myproject.ws_home_work6.network.RestApi;
import ru.myproject.ws_home_work6.network.SingleResponseFlatMap;
import ru.myproject.ws_home_work6.network.service.AuthService;
import ru.myproject.ws_home_work6.utils.LoginController;

public class RegistrationFragment extends Fragment {

    EditText text_login, text_password, text_password2;
    Button btn_sing_in;
    AuthService authService;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_registration, container, false);
        text_login = v.findViewById(R.id.text_login);
        text_password = v.findViewById(R.id.text_password);
        text_password2 = v.findViewById(R.id.text_password2);
        btn_sing_in = v.findViewById(R.id.btn_sing_in);
        authService= RestApi.createService(AuthService.class);
        btn_sing_in.setEnabled(false);
        text_login.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                check_input(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        text_password2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                check_input(s);
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
                check_input(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn_sing_in.setOnClickListener(v1 -> {
            if (check_password()) {
                Registration registration =new Registration();
                registration.setEmail(text_login.getText().toString());
                registration.setPassword(text_password.getText().toString());
                authService.registerIn(registration)
                        .subscribeOn(Schedulers.io())
                        .flatMap(new SingleResponseFlatMap<>())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<StatusRegistration>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(StatusRegistration statusRegistration) {
                                if(statusRegistration.getStatus().equals("Successful")) {
                                    AuthorizationFragment fragment = new AuthorizationFragment();
                                    Bundle bundle =new Bundle();
                                    bundle.putString("login",text_login.getText().toString());
                                    bundle.putString("password",text_password.getText().toString());
                                    fragment.setArguments(bundle);
                                    getActivity().getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.fragment_container,fragment)
                                            .commit();
                                }
                            }

                            @Override
                            public void onError(Throwable error) {
                                Log.i("MyError",error.getMessage());
                                System.out.println(error.getMessage());
                                error.printStackTrace();
                            }
                        });

            }
        });


        return v;
    }

    boolean check_password() {
        if (text_password.getText().toString().equals(text_password2.getText().toString())) {
            return true;
        } else {
            Toast.makeText(getContext(), "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    void check_input(CharSequence s) {
        if (s.toString().equals("")
                || text_password.getText().toString().equals("")
                || text_password2.getText().toString().equals("")
                || text_login.getText().toString().equals("")) {
            btn_sing_in.setEnabled(false);
        } else {
            btn_sing_in.setEnabled(true);
        }
    }

}
