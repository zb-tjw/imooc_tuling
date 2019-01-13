package com.example.zb_tjw.imooc_tuling.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zb_tjw.imooc_tuling.R;
import com.example.zb_tjw.imooc_tuling.bean.ChatMessage;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by:zb_tjw on 2019/1/12
 * Author:zb_tjw
 * Email:1549790231@qq.com
 * QQ:1549790231
 * be Used:
 */
public class ChatMessageAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ChatMessage> mDatas;

    public ChatMessageAdapter(Context context, List<ChatMessage> mDatas) {
        mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessage = mDatas.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            //通过ItemType设置不同的布局
            if (getItemViewType(position) == 0){
                convertView = mInflater.inflate(R.layout.item_from_msg,parent,false);
                viewHolder = new ViewHolder();
                viewHolder.mDate = convertView.findViewById(R.id.tv_from_msg_date);
                viewHolder.mMsg = convertView.findViewById(R.id.tv_from_msg_info);
            }else{
                convertView = mInflater.inflate(R.layout.item_send_msg,parent,false);
                viewHolder = new ViewHolder();
                viewHolder.mDate = convertView.findViewById(R.id.tv_send_msg_date);
                viewHolder.mMsg = convertView.findViewById(R.id.tv_send_msg_info);
            }
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //设置数据
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        viewHolder.mDate.setText(df.format(chatMessage.getDate()));
        viewHolder.mMsg.setText(chatMessage.getMsg());
        return convertView;
    }

    /**
     * 如果一个布局只要重写默认的四个方法就行
     * 这里多个布局要多重写getItemViewType()和getViewTypeCount()方法
     * 如果是接收消息return 0; 如果是发送消息return 1
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage = mDatas.get(position);
        if (chatMessage.getType() == ChatMessage.Type.INCOMING)
            return 0;
        else
            return 1;
    }

    /**
     * 因为有两种布局，所以return 2
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }


    private final class ViewHolder{
        TextView mDate;//时间
        TextView mMsg;//消息
    }
}
