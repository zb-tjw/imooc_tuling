package com.example.zb_tjw.imooc_tuling;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zb_tjw.imooc_tuling.adapter.ChatMessageAdapter;
import com.example.zb_tjw.imooc_tuling.bean.ChatMessage;
import com.example.zb_tjw.imooc_tuling.utils.HttpUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv_msgs;
    private ChatMessageAdapter mAdater;
    private List<ChatMessage> mDatas;

    private EditText ed_input;
    private Button btn_send;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //等待子线程返回的数据然后更新UI
            ChatMessage fromChatMessage = (ChatMessage) msg.obj;
            mDatas.add(fromChatMessage);
            mAdater.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        initDatas();
        initListener();
    }

    private void initListener() {
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String toMsg = ed_input.getText().toString();
                if (TextUtils.isEmpty(toMsg)){
                    Toast.makeText(MainActivity.this,"发送消息不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                ChatMessage toChatMessage = new ChatMessage();
                toChatMessage.setDate(new Date());
                toChatMessage.setMsg(toMsg);
                toChatMessage.setType(ChatMessage.Type.OUTCOMING);
                mDatas.add(toChatMessage);
                mAdater.notifyDataSetChanged();

                ed_input.setText("");//清空输入框文本

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ChatMessage fromChatMessage = HttpUtils.sendMessage(toMsg);
                        Message message = Message.obtain();
                        message.obj = fromChatMessage;
                        mHandler.sendMessage(message);
                    }
                }).start();
            }
        });
    }

    private void initDatas() {
        mDatas = new ArrayList<ChatMessage>();
        mDatas.add(new ChatMessage("你好，晓萍为您服务", ChatMessage.Type.INCOMING, new Date()));
//        mDatas.add(new ChatMessage("你好", ChatMessage.Type.OUTCOMING, new Date()));
        mAdater = new ChatMessageAdapter(this,mDatas);

        lv_msgs.setAdapter(mAdater);
    }

    private void initView() {
        lv_msgs = findViewById(R.id.lv_msgs);
        ed_input = findViewById(R.id.et_input_msg);
        btn_send = findViewById(R.id.btn_send_msg);
    }
}
