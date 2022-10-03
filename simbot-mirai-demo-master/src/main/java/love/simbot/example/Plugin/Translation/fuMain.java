package love.simbot.example.Plugin.Translation;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import love.forte.common.ioc.annotation.Beans;
import love.forte.common.ioc.annotation.Depend;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.FilterValue;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.MessageContent;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.MessageContentBuilderFactory;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.filter.MatchType;
import love.simbot.example.Utils_.SendGet;

/**
 * Copyright (C), 2022-10-01
 * FileName: fuMain
 * Author:   mirai
 * Date:     2022/10/1 16:34
 * Description:
 *
 * @author mirai
 */
@Beans
public class fuMain {

    @Depend
    private MessageContentBuilderFactory messageBuilderFactory;

    @OnGroup
    @Filter(value = "tr/{{type}}/{{next}}", matchType = MatchType.REGEX_MATCHES, trim = true)
    public void translation(GroupMsg groupMsg, Sender sender, @FilterValue("type") String type, @FilterValue("next") String next) {
        String Type = switch (type) {
            case "中译英" -> "ZH_CN2EN";
            case "中译日" -> "ZH_CN2JA";
            case "中译韩" -> "ZH_CN2KR";
            case "英译中" -> "EN2ZH_CN";
            case "日译中" -> "JA2ZH_CN";
            case "韩译中" -> "KR2ZH_CN";
            default -> "";
        };
        MessageContentBuilder msgBuilder = messageBuilderFactory.getMessageContentBuilder();
        Gson gson = new Gson();
        FyData fyData;
        try {
            fyData = gson.fromJson(new SendGet().SendGET("http://fanyi.youdao.com/translate?&doctype=json&type=" + Type + "&i=" + next, ""), FyData.class);
            fyData.getTranslateResult().forEach(FY -> FY.forEach(fy -> {
                MessageContent msg = msgBuilder
                        .text("原文: " + fy.getSrc() + "\n")
                        .text("翻译: " + fy.getTgt())
                        .build();
                sender.sendGroupMsg(groupMsg, msg);
            }));
        } catch (JsonSyntaxException e) {
            sender.sendGroupMsg(groupMsg, "好像出错了呢");
        }
    }
}
