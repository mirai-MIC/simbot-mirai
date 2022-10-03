package love.simbot.example.Utils_;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright (C), 2022-09-12
 * FileName: System_Time
 * Author:   mirai
 * Date:     2022/9/12 13:57
 * Description:
 */
public class System_Time {
    public String getTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        return "Asia/Shanghai:" + sdf.format(d);
    }
}
