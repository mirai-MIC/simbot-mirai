package love.simbot.example.Utils_;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Copyright (C), 2022-07-15
 * FileName: URL_JX
 * Author:   mirai
 * Date:     2022/7/15 21:39
 * Description:
 */

@SuppressWarnings({"all"})
public class URL_JX {
    public String Getencode(String dq) throws UnsupportedEncodingException {
        String encodeStr = URLEncoder.encode(dq, "UTF-8");
        return encodeStr;
    }
}
