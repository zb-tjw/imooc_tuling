package com.example.zb_tjw.imooc_tuling.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.zb_tjw.imooc_tuling.utils.HttpUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by:zb_tjw on 2019/1/12
 * Author:zb_tjw
 * Email:1549790231@qq.com
 * QQ:1549790231
 * be Used:
 */
public class TestHttpUtils{

    @Test
    public void testSendInfo(){
        String result = null;
        try {
            result = HttpUtils.post("{\"reqType\":0,\"perception\":{\"inputText\":{\"text\":\"给我讲个笑话\"}},\"userInfo\":{\"apiKey\":\"b45dde26f2e843848d8f2c1aba7cf74d\",\"userId\":\"378152\"}}");
//            JSONObject jsonObject = JSONObject.parseObject(result);//结果转化为JSONObject对象
//            JSONArray jsonArray = jsonObject.getJSONArray("results");//直接从这个JSONObject获取到results数组
//            JSONObject jsonObject1 = jsonArray.getJSONObject(0);//再获取results数组中所有对象
//            JSONObject jsonObject2 = jsonObject1.getJSONObject("values");//获取到values对应的值
//            String resultText = jsonObject2.getString("text");//获取最后有用的文本数据
//            System.out.println("resultText==="+resultText);
            System.out.println("resultText==="+result);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String result = HttpUtils.doPost("{\\\"reqType\\\":0,\\\"perception\\\":{\\\"inputText\\\":{\\\"text\\\":\\\"你好\\\"}},\\\"userInfo\\\":{\\\"apiKey\\\":\\\"b45dde26f2e843848d8f2c1aba7cf74d\\\",\\\"userId\\\":\\\"378152\\\"}}");
    }
}
