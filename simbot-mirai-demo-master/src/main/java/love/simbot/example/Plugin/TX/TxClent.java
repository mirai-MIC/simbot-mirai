package love.simbot.example.Plugin.TX;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import love.forte.common.ioc.annotation.Beans;
import love.forte.common.ioc.annotation.Depend;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.FilterValue;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.annotation.OnPrivate;
import love.forte.simbot.api.message.MessageContent;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.MessageContentBuilderFactory;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.filter.MatchType;
import love.simbot.example.Utils_.SendGet;

/**
 * Copyright (C), 2022-09-26
 * FileName: TxClent
 * Author:   mirai
 * Date:     2022/9/26 21:07
 * Description:
 */
@Beans
public class TxClent {
    @Depend
    private MessageContentBuilderFactory messageBuilderFactory;

    @OnGroup
    @Filter(value = "tx/{{title}}/{{nums}}", matchType = MatchType.REGEX_MATCHES, trim = true)
    public void TxClent(GroupMsg groupMsg, Sender sender, @FilterValue("title") String title, @FilterValue("nums") String nums) {
        try {
            MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
            Gson gson = new Gson();
            TxData txData = gson.fromJson(new SendGet().SendGET("https://xiaoapi.cn/API/dy_tx.php?msg=" + title.trim() + "&n=1&num=", nums.trim()), TxData.class);
            if (txData.getPic() == null) {
                sender.sendGroupMsg(groupMsg, "好像不存在哦~,要不换个其他的");
            } else {
                MessageContent msg = builder
                        .image(txData.getPic())
                        .text("标题: " + txData.getTitle() + "\n")
                        .text("更新范围: " + txData.getMsg() + "\n")
                        .text("当前集: " + txData.getPlay_title() + "\n")
                        .text("链接: " + txData.getUrl() + "\n")
                        .build();
                sender.sendGroupMsg(groupMsg, msg);
            }

        } catch (JsonSyntaxException e) {
            sender.sendGroupMsg(groupMsg, "没有搜到或者网络波动，请再尝试一次吧");
        }
    }

    @OnPrivate
    @Filter(value = "tx/{{title}}/{{nums}}", matchType = MatchType.REGEX_MATCHES, trim = true)
    public void TxVideoPr(PrivateMsg privateMsg, Sender sender, @FilterValue("title") String title, @FilterValue("nums") String nums) {
        try {
            MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
            Gson gson = new Gson();
            TxData txData = gson.fromJson(new SendGet().SendGET("https://xiaoapi.cn/API/dy_tx.php?msg=" + title.trim() + "&n=1&num=", nums.trim()), TxData.class);
            if (txData.getPic() == null) {
                sender.sendPrivateMsg(privateMsg, "好像不存在哦~,要不换个其他的试试");
            } else {
                MessageContent msg = builder
                        .image(txData.getPic())
                        .text("标题: " + txData.getTitle() + "\n")
                        .text("更新范围: " + txData.getMsg() + "\n")
                        .text("当前集: " + txData.getPlay_title() + "\n")
                        .text("链接: " + txData.getUrl() + "\n")
                        .build();
                sender.sendPrivateMsg(privateMsg, msg);
            }
        } catch (JsonSyntaxException e) {
            sender.sendPrivateMsg(privateMsg, "没有搜到或者网络波动，请再尝试一次吧");
        }
    }
}
