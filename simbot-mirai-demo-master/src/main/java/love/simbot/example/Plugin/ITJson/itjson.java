package love.simbot.example.Plugin.ITJson;

import love.forte.common.ioc.annotation.Beans;
import love.forte.common.ioc.annotation.Depend;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.MessageContent;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.MessageContentBuilderFactory;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.component.mirai.message.MiraiMessageContentBuilder;
import love.forte.simbot.component.mirai.message.MiraiMessageContentBuilderFactory;
import love.forte.simbot.filter.MatchType;

/**
 * Copyright (C), 2022-09-24
 * FileName: itjson
 * Author:   mirai
 * Date:     2022/9/24 19:57
 * Description:
 */
@Beans
public class itjson {
    private final MessageContentBuilderFactory factory;
    @Depend
    private MessageContentBuilderFactory messageBuilderFactory;

    public itjson(MessageContentBuilderFactory factory) {
        this.factory = factory;
    }

    @OnGroup
    @Filter(value = "/zx", matchType = MatchType.EQUALS, trim = true)
    public void it(GroupMsg groupMsg, Sender sender) {
        if (!(factory instanceof MiraiMessageContentBuilderFactory)) {
            throw new RuntimeException("不支持mirai组件");
        }
        MessageContentBuilder msgBuilder = messageBuilderFactory.getMessageContentBuilder();
        MiraiMessageContentBuilder builder = ((MiraiMessageContentBuilderFactory) factory).getMessageContentBuilder();
        MessageContent msg = msgBuilder
                .text(new itMain().ITMain())
                .build();
        builder.forwardMessage(forwardBuilder -> {
            forwardBuilder.add(groupMsg, msg);
        });
        sender.sendGroupMsg(groupMsg, builder.build());
    }
}
