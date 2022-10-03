package love.simbot.example.Plugin.LotRandom_;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
import love.forte.simbot.timer.EnableTimeTask;
import love.simbot.example.Admin_.fileInput;
import love.simbot.example.Gson.Lod;
import love.simbot.example.Utils_.Find;
import love.simbot.example.Utils_.SendGet;

import java.io.IOException;
import java.util.HashSet;

/**
 * Copyright (C), 2022-09-20
 * FileName: LotRandom
 * Author:   mirai
 * Date:     2022/9/20 11:24
 * Description:
 */
@Beans
@EnableTimeTask
public class LotRandom {
    private final MessageContentBuilderFactory factory;
    @Depend
    private MessageContentBuilderFactory messageBuilderFactory;

    public LotRandom(MessageContentBuilderFactory factory) {
        this.factory = factory;
    }

    public String getPath() {
        return "cache/LotAdd.txt";
    }

    @OnGroup
    @Filter(value = "抽签", matchType = MatchType.EQUALS, trim = true)
    public void lot_Random(GroupMsg groupMsg, Sender sender) throws IOException {
        if (!(factory instanceof MiraiMessageContentBuilderFactory)) {
            throw new RuntimeException("不支持mirai组件");
        }
        MessageContentBuilder msgBuilder = messageBuilderFactory.getMessageContentBuilder();
        String accountCode = groupMsg.getAccountInfo().getAccountCode();
        MiraiMessageContentBuilder builder = ((MiraiMessageContentBuilderFactory) factory).getMessageContentBuilder();
        HashSet<String> TimeGet = new fileInput().getBotPermission(getPath());
        if (Find.find(TimeGet, Long.parseLong(accountCode)) == 1) {
            sender.sendGroupMsg(groupMsg.getGroupInfo(), "唔~今天好像不能再抽了");
        } else {
            try {
                Gson gson = new Gson();
                Lod lod = gson.fromJson(new SendGet().SendGET("https://ovooa.com/API/chouq/api.php", ""), Lod.class);
                MessageContent msg = msgBuilder
                        .at(groupMsg.getAccountInfo().getAccountCode())
                        .text("\n" + lod.getData().getFormat() + " 签\n")
                        .text("签词: " + lod.getData().getDraw() + "\n")
                        .text("注释: " + lod.getData().getAnnotate() + "\n")
                        .text("解释: " + lod.getData().getExplain() + "\n")
                        .text("详细: " + lod.getData().getDetails() + "\n")
                        .text("来源: " + lod.getData().getSource() + "\n")
                        .image(lod.getData().getImage())
                        .build();
                builder.forwardMessage(forwardBuilder -> forwardBuilder.add(groupMsg, msg));
                sender.sendGroupMsg(groupMsg, builder.build());
                new fileInput().getBotAddMaster(getPath(), accountCode, true);
            } catch (JsonSyntaxException e) {
                sender.sendGroupMsg(groupMsg, "好像出错了呢，要不再试试吧");
            }
        }
    }
}
