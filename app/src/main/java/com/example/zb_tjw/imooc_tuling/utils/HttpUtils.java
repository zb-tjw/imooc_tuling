package com.example.zb_tjw.imooc_tuling.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.zb_tjw.imooc_tuling.bean.ChatMessage;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Date;

/**
 * Created by:zb_tjw on 2019/1/11
 * Author:zb_tjw
 * Email:1549790231@qq.com
 * QQ:1549790231
 * be Used:Get方式请求已废弃，自己写了doPost()方法
 */
public class HttpUtils {
    public static final String URL = "http://openapi.tuling123.com/openapi/api/v2";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    public static String post(String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String receivedMessage = getUsefulMessage(response.body().string());
            return receivedMessage;
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    private static String getUsefulMessage(String responseStr) throws IOException {
        JSONObject jsonObject = JSONObject.parseObject(responseStr);//结果转化为JSONObject对象
        JSONArray jsonArray = jsonObject.getJSONArray("results");//直接从这个JSONObject获取到results数组
        JSONObject jsonObject1 = jsonArray.getJSONObject(0);//再获取results数组中所有对象
        JSONObject jsonObject2 = jsonObject1.getJSONObject("values");//获取到values对应的值
        String resultText = jsonObject2.getString("text");//获取最后有用的文本数据
        return resultText;
    }

    public static ChatMessage sendMessage(String msg)
    {
        ChatMessage chatMessage = new ChatMessage();
        try {
            String request = "{\"reqType\":0,\"perception\":{\"inputText\":{\"text\":\"" + msg +
                    "\"}},\"userInfo\":{\"apiKey\":\"b45dde26f2e843848d8f2c1aba7cf74d\",\"userId\":\"378152\"}}";
            String resultStr = post(request);
            chatMessage.setMsg(resultStr);
            chatMessage.setType(ChatMessage.Type.INCOMING);
            chatMessage.setDate(new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chatMessage;
    }

    /**
     * get方式已废弃
     */
    /*public static final String API_KEY = "b45dde26f2e843848d8f2c1aba7cf74d";

    public static String doGet(String msg){

        String result = "";
        String url = setParamters(msg);
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            java.net.URL urlNet = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlNet.openConnection();

            conn.setReadTimeout(5 * 1000);
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            is = conn.getInputStream();

            int len = -1;
            byte[] buff = new byte[128];
            baos = new ByteArrayOutputStream();
            while ((len = is.read(buff)) != -1){
                baos.write(buff, 0, len);
            }

            baos.flush();
            result = new String(baos.toByteArray());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private static String setParamters(String msg) {

        String url = "";
        try {
            url = URL+"?key="+API_KEY+"&info="+ URLEncoder.encode(msg,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }*/

    /**
     * 导入HttpClient包可以正常使用，但是之前存在包冲突，果断扔了转OKHttp框架来写
     */
    /*public static String doPost(String json){
        String response = "接口调用失败";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            //第一步：创建HttpClient对象
            httpClient = HttpClients.createDefault();

            //第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(URL);

            //第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(json,"utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);

            //第四步：发送HttpPost请求，获取返回值
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if(httpResponse.getStatusLine().getStatusCode()==200){//请求和响应都成功了

                HttpEntity entity=httpResponse.getEntity();//调用getEntity()方法获取到一个HttpEntity实例

                response=EntityUtils.toString(entity,"UTF-8");//用EntityUtils.toString()这个静态方法将HttpEntity转换成字符串,防止服务器返回的数据带有中文,所以在转换的时候将字符集指定成utf-8就可以了

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //第五步：处理返回值
        return response;
    }*/
}
