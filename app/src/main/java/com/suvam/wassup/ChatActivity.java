package com.suvam.wassup;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.suvam.wassup.databinding.ActivityChatBinding;
import com.suvam.wassup.model.Chat;

public class ChatActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView userName;
    private ImageView userImg;
    private ImageButton backBtn;
    private Chat chatItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChatBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        toolbar = (Toolbar) findViewById(R.id.chat_toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
            chatItem = (Chat) extras.getSerializable("chat");
        else
            chatItem = new Chat();

        userName = findViewById(R.id.user_name);
        userImg = findViewById(R.id.user_img);
        backBtn = findViewById(R.id.goBack);

        binding.setMsg(chatItem.getMsg());
        binding.setTime(chatItem.getMsgTime());
        binding.setType(chatItem.getLastMsgType().name());

        userName.setText(chatItem.getName());

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
