package com.tony.thanhgiang.realtimechat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.net.URISyntaxException;
import java.util.Arrays;

import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by ASUS on 5/8/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private Socket mSocket;
    EditText etUsername, etPassword;
    Button btnSignup, btnLogin;
    String username, password;
    JSONObject user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mapping();

        try {
            mSocket = IO.socket("http://192.168.37.1:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mSocket.connect();
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                if(username.length() > 0 && password.length() > 0) {
                    try {
                        user.put("username", username);
                        user.put("password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    mSocket.emit("register", user);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Invalid input. Try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void mapping() {
        etUsername = findViewById(R.id.edUsername);
        etPassword = findViewById(R.id.edPassword);
        btnSignup = findViewById(R.id.btnSignup);
        btnLogin= findViewById(R.id.btnLogin);
        user = new JSONObject();
    }
}
