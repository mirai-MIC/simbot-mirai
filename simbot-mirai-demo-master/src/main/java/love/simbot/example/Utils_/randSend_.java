package love.simbot.example.Utils_;

import love.forte.common.ioc.annotation.Beans;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Copyright (C), 2022-09-13
 * FileName: randSend_
 * Author:   mirai
 * Date:     2022/9/13 7:51
 * Description:
 */
@Beans
public class randSend_ {
    public String randomArr(@NotNull String[] arr) {
        return arr[new Random().nextInt(arr.length)];
    }
}
