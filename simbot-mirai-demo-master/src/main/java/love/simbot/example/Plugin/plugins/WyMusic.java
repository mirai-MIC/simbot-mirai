package love.simbot.example.Plugin.plugins;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import love.forte.common.ioc.annotation.Beans;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.FilterValue;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.filter.MatchType;
import love.simbot.example.Gson.WYData;
import love.simbot.example.Utils_.SendGet;

/**
 * Copyright (C), 2022-09-24
 * FileName: WyMusic
 * Author:   mirai
 * Date:     2022/9/24 12:11
 * Description:
 */
@Beans
public class WyMusic {
    @OnGroup
    @Filter(value = "点歌/{{music}}", matchType = MatchType.REGEX_MATCHES, trim = true)
    public void music(GroupMsg groupMsg, Sender sender, @FilterValue("music") String music) {
        try {
            Gson gson = new Gson();
            String s = new SendGet().SendGET("https://ovooa.com/API/kwdg/api.php?msg=" + music + "&n=1&type=json&h=%E6%8D%A2%E8%A1%8C&sc=1", "").substring(5);
            WYData wyData = gson.fromJson(s, WYData.class);
            String title = wyData.getMeta().getMusic().getTitle();
            String musicUrl = wyData.getMeta().getMusic().getMusicUrl();
            String preview = wyData.getMeta().getMusic().getPreview();
            String musicXml = "[CAT:music,type=163,title=" + title + "," + "pictureUrl=" + preview + ",audio=" + musicUrl + "]";
            sender.sendGroupMsg(groupMsg, musicXml);
        } catch (JsonSyntaxException e) {
            sender.sendGroupMsg(groupMsg, "唔~刚刚好像出错了,要不再试一下吧");
        }
    }
}
