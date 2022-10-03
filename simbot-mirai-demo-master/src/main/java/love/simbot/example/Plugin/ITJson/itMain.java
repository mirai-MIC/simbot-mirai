package love.simbot.example.Plugin.ITJson;

import com.google.gson.Gson;
import love.simbot.example.Gson.itData;
import love.simbot.example.Utils_.SendGet;

/**
 * Copyright (C), 2022-09-24
 * FileName: itMain
 * Author:   mirai
 * Date:     2022/9/24 17:50
 * Description:
 * @author mirai
 */
public class itMain {

    public StringBuilder ITMain() {
        String s = new SendGet().SendGET("https://api.vvhan.com/api/hotlist?type=itInfo", "");
        Gson gson = new Gson();
        StringBuilder it = new StringBuilder();
        itData itData = gson.fromJson(s, itData.class);
        itData.getData().forEach(itDataIO -> {
            it.append("index: ").append(itDataIO.getIndex()).append("\n");
            it.append(itDataIO.getTitle()).append("\n");
            it.append(itDataIO.getUrl()).append("\n");
        });
        return it;
    }
}
