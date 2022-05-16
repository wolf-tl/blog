package com.tl.blog.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author tl
 */
public class QqHttpClient {
    /**
     * 获取Access Token
     */
    public static String getAccessToken(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        String token = null;

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            if (result.indexOf("access_token") >= 0) {
                String[] array = result.split("&");
                for (String str : array) {
                    if (str.indexOf("access_token") >= 0) {
                        token = str.substring(str.indexOf("=") + 1);
                        break;
                    }
                }
            }
        }

        httpGet.releaseConnection();
        return token;
    }

    /**
     * 获取openID
     */
    public static String getOpenID(String url) throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        // 一些信息都在 请求 中的 entity 里
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            String result = EntityUtils.toString(entity, "UTF-8");
            jsonObject = parseJSONP(result);
        }

        httpGet.releaseConnection();

        if (jsonObject != null) {
            return jsonObject.getString("openid");
        } else {
            return null;
        }
    }

    /**
     * 获取QQ用户信息
     */
    public static JSONObject getUserInfo(String url) throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            // 将entity的内容按照指定编码 译成 字符串
            String result = EntityUtils.toString(entity, "UTF-8");
            // 将字符串 转为json对象
            jsonObject = JSONObject.parseObject(result);
        }

        httpGet.releaseConnection();

        return jsonObject;
    }

    /**
     * 转换json对象
     */
    private static JSONObject parseJSONP(String jsonp) {
        int startIndex = jsonp.indexOf("(");
        int endIndex = jsonp.lastIndexOf(")");
        String json = jsonp.substring(startIndex + 1, endIndex);
        return JSONObject.parseObject(json);
    }
}
