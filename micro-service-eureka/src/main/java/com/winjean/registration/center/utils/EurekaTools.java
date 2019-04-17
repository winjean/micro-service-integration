package com.winjean.registration.center.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/16 11:34
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class EurekaTools {

    public List<String> getAllServiceAddr(String eurekaIp, String eurekaPort, String servicename) {
        List<String> result = new ArrayList<>();
		String url = "http://" + eurekaIp + ":" + eurekaPort + "/eureka/apps/" + servicename;
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)//请求接口。如果需要传参拼接到接口后面。
                .addHeader("Content-Type","application/json")
                .addHeader("Accept","application/xml")
                .get()
                .build();//创建Request 对象

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseContent = response.body().string();
                Matcher matcher = Pattern.compile("<homePageUrl>(.+?)</homePageUrl>").matcher(responseContent);
                while (matcher.find()) {
                    String homepage = matcher.group(1).trim();
                    result.add(homepage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
