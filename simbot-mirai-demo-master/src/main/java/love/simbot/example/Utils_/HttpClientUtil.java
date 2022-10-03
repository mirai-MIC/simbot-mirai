package love.simbot.example.Utils_;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

/**
 * @author mirai
 */
public class HttpClientUtil {
    // 调用POST请求
    public static String sendPost(String url, String requestBody) {
        // 创建post请求
        HttpPost post = new HttpPost(url);
        // 设置头
        post.setHeader("Content-Type", "application/json");
        // 设置请求设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(180000).setConnectionRequestTimeout(180000)
                .setSocketTimeout(180000).setRedirectsEnabled(true)
                .build();
        post.setConfig(requestConfig);

        BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(basicCredentialsProvider)
                .build();
        // 设置发送体
        post.setEntity(new StringEntity(requestBody, ContentType.create("application/json", "utf-8")));
        try {
            CloseableHttpResponse response = httpClient.execute(post);
            if (response != null && response.getStatusLine().getStatusCode() == 200) {
                String str = EntityUtils.toString(response.getEntity());
                System.out.println("请求结果:" + str);
                return str;
            }
            // 请求结果
            assert response != null;
            String result = EntityUtils.toString(response.getEntity());
            System.out.println("调用失败:" + result);
            return "调用失败:" + result;
        } catch (Exception e) {
            e.printStackTrace();
            return "调用异常";
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 调用Get请求
    public static <T> String sendGet(String url) {
        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 返回结果
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            //创建uri
            URIBuilder builder = new URIBuilder(url);

            URI uri = builder.build();
            // 创建http get请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否返回200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            System.out.println("系统错误:" + e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                System.out.println("系统错误:" + e);
            }

        }
        return resultString;
    }

}
