package com.example.usersad.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usersad.myapplication.serviceapi.AppRMNTK;
import com.example.usersad.myapplication.R;
import com.example.usersad.myapplication.model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener {



    EditText loginEditText, pasEditText;

    Button butOk;

    SharedPreferences tokenShPr,loginShPr,passShPr;

    String saved_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        loginEditText = findViewById(R.id.loginEditText);
        pasEditText = findViewById(R.id.pasEditText);

        butOk = findViewById(R.id.butOk);

        getSupportActionBar().hide();

        SharedPreferences sharedPreferencesL = getSharedPreferences("mainlogin",MODE_PRIVATE);
        String login = sharedPreferencesL.getString("my_string","".toString());

        SharedPreferences sharedPreferencesP = getSharedPreferences("mainpas",MODE_PRIVATE);
        String passw = sharedPreferencesP.getString("my_string","".toString());

        loginEditText.setText(login);
        pasEditText.setText(passw);
    }

    @Override
    public void onClick(View view) {


        AppRMNTK.getAuthClient().getData(loginEditText.getText().toString(), pasEditText.getText().toString()).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Intent intent;

                if(response.body() != null) {

                    String token = response.body().getData().getToken();

                    String loginSh,passSh;
                    loginSh = loginEditText.getText().toString();
                    passSh = pasEditText.getText().toString();

                    Log.d("mylog", token);

                    tokenShPr = getSharedPreferences("main",MODE_PRIVATE);
                    SharedPreferences.Editor ed = tokenShPr.edit();
                    ed.putString("my_string", token);
                    ed.commit();

                    Toast.makeText(AuthActivity.this, "Добро пожаловать!",Toast.LENGTH_SHORT).show();

                    intent = new Intent("android.intent.action.Rmntk");
                    startActivity(intent);

                    saved_text = tokenShPr.getString("my_string","");

                    loginShPr = getSharedPreferences("mainlogin",MODE_PRIVATE);
                    SharedPreferences.Editor edL = loginShPr.edit();
                    edL.putString("my_string",loginSh);
                    edL.commit();

                    passShPr = getSharedPreferences("mainpas",MODE_PRIVATE);
                    SharedPreferences.Editor edP = passShPr.edit();
                    edP.putString("my_string",passSh);
                    edP.commit();




                }else{
                    Toast.makeText(AuthActivity.this, "Неверный логин или пароль!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                Toast.makeText(AuthActivity.this, "Null Ex", Toast.LENGTH_SHORT).show();

            }
        });

    }
}