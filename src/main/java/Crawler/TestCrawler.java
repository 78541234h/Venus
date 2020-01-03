package Crawler;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestCrawler {
    public static void main(String[] args) throws Exception {
        HttpGet httpget = new HttpGet("http://www.baidu.com");
        String filePath = "/home/hzh/IntelliJProject/Venus/src/main/resources/test.txt";
        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse response = httpclient.execute(httpget);
             FileOutputStream fos = new FileOutputStream(filePath);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            HttpEntity entity = response.getEntity();
            entity.writeTo(bos);
        }
       // HashMap<String, String> hashMap = new HashMap<>();
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,8,60, TimeUnit.SECONDS,new SynchronousQueue<>());
    }
}