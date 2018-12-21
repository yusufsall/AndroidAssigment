package com.sal.yusuf.assigment.Pages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.sal.yusuf.assigment.R;
import com.sal.yusuf.assigment.Utils.Constants;
import com.sal.yusuf.assigment.Utils.Database;

public class LoginActivity extends AppCompatActivity {
    String username = "";
    String password = "";

    //Giriş yapmak için gerekli kullanıcı adı ve şifre değerleri
    String correctUser = Constants.Username;
    String correctPass = Constants.Password;

    //Viewlerin tnaımlanması
    EditText user, pass;
    Button login;
    Switch rememberme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckForRemember();
        HideTitleBar();
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);
        rememberme = (Switch) findViewById(R.id.rememberme);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = user.getText().toString();
                password = pass.getText().toString();

                if(username.equals(correctUser) && password.equals(correctPass)) {

                    if(rememberme.isChecked())
                        Database.RememberMe(LoginActivity.this, username, password);

                    PassToTheMainActivity();
                }
                else
                    ShowErrorMessage();


            }
        });

    }

    private void HideTitleBar(){
        getSupportActionBar().hide();
    }


    private void PassToTheMainActivity(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void ShowErrorMessage(){
        Toast.makeText(LoginActivity.this,"Kullanıcı adı veya şifre hatalı!",Toast.LENGTH_SHORT).show();

    }


    private void CheckForRemember(){
        boolean remember = Database.isRemembered(this);

        if(remember)
            PassToTheMainActivity();
    }


}
