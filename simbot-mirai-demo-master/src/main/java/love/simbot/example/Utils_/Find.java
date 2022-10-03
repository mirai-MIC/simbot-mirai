package love.simbot.example.Utils_;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

/**
 * Copyright (C), 2022-09-19
 * FileName: Find
 * Author:   mirai
 * Date:     2022/9/19 13:26
 * Description: 此方法用来检查 输入数字是否在数组中存在
 *
 * @author mirai
 */
public class Find {
    public static int find(@NotNull HashSet<String> arr, long num) {
        int found = 0;
        for (String find : arr) {
            if (find.equals(String.valueOf(num))) {
                found = 1;
                break;
            }
        }
        return found;
    }
}
