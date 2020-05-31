package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etEmailLogin,
            etPassLogin;

    TextView tvRegister;

    Button btnLogin;

    DBHelper dbHelper;

    public static final String USERNAME = "com.example.proyectofinal.example.USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this);

        etEmailLogin = findViewById(R.id.etEmailLogin);
        etPassLogin = findViewById(R.id.etPassLogin);

        tvRegister = findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startRegisterActivity();
            }
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etEmailLogin.getText().toString();
                String pass = etPassLogin.getText().toString();

                startMainActivity();
                if(name.equals("") && pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Mail and Password required!", Toast.LENGTH_SHORT).show();
                }else if(name.equals("")){
                    Toast.makeText(LoginActivity.this, "Mail is required!", Toast.LENGTH_SHORT).show();
                }else if(pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Password is required!", Toast.LENGTH_SHORT).show();
                }else{
                    //Auth
                    //DBHelper GET Email and pass
                    String userCredential = dbHelper.getUserName(name, pass);
                    if (userCredential.equals("")) {
                        Toast.makeText(LoginActivity.this, "Error, the user or password is wrong", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(LoginActivity.this, "Entras a la app como: " + userCredential, Toast.LENGTH_SHORT).show();
                        //StartActiviy App
                        startMainActivity();
                    }
                }
            }
        });
    }

    public void startRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    public void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra(USERNAME,userCredential);
        startActivity(intent);
    }


}
