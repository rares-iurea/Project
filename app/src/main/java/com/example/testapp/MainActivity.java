package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password,repassword;
    Button signupButton,registerButton;
    DataBase DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signupButton = (Button) findViewById(R.id.signupButton);
        registerButton = (Button) findViewById(R.id.registerButton);
        DB = new DataBase(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String user =  username.getText().toString();
              String pass =  password.getText().toString();
              String repass =  repassword.getText().toString();

              if(user.equals("") || pass.equals("") || repass.equals(""))
                Toast.makeText(MainActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
              else{
                 if(pass.equals(repass)){
                     Boolean checkuser = DB.checkUsername(user);
                     if(checkuser == false){
                         Boolean insert = DB.insertData(user, pass);
                         if(insert == true){
                             Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(getApplicationContext(),Home.class);
                             startActivity(intent);
                         }else{
                             Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                         }
                     }else{
                         Toast.makeText(MainActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                     }
                  }else {
                     Toast.makeText(MainActivity.this, "Passwords does not match", Toast.LENGTH_SHORT).show();
                 }
              }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);
            }
        });

    }
}