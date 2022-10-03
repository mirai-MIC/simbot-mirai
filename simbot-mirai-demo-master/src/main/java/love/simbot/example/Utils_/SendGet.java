package love.simbot.example.Utils_;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2022-07-04
 * FileName: SendGet
 * Author:   mirai
 * Date:     2022/7/4 22:55
 * Description:
 *
 * @author mirai
 */
public class SendGet {
    public String SendGET(String url, String pram) {

        StringBuilder result = new StringBuilder();
        BufferedReader read = null;

        try {
            URL realurl = new URL(url + pram);
            URLConnection connection = realurl.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36)");
            connection.setRequestProperty("cookie", "token=632af2808617a; user=88684; auth=36ae049bc8500f563eefb6b4f212d36b331761ac; cf_clearance=oT1NYZgGU4Bqj3mHh3EMwif_d5TRK1i8q9J62h3NZ1Y-1663762195-0-150");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = read.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return String.valueOf(result);
    }
}
