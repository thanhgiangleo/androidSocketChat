package com.tony.thanhgiang.realtimechat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {

    private Socket mSocket;
    ListView listUser, listChat;
    EditText etChat;
    ImageButton btnSendText, btnSendFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

        try {
            mSocket = IO.socket("http://192.168.37.1:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mSocket.connect();
    }

    private void mapping() {
        listUser = findViewById(R.id.listUser);
        listChat = findViewById(R.id.listChatContent);
        etChat = findViewById(R.id.edChat);
        btnSendFile = findViewById(R.id.btnSendFile);
        btnSendText = findViewById(R.id.btnSend);
    }
}
