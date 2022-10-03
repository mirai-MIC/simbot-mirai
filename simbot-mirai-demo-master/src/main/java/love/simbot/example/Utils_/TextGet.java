package love.simbot.example.Utils_;

/**
 * Copyright (C), 2022-09-17
 * FileName: TextGet
 * Author:   mirai
 * Date:     2022/9/17 22:32
 * Description:
 */
public class TextGet {
    public String getText(String flag, String text) {
        return text.substring(text.indexOf(flag) + flag.length());
    }
}
