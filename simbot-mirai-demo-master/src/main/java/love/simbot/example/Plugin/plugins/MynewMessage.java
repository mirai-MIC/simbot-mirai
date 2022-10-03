package love.simbot.example.Plugin.plugins;

import catcode.Neko;
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
import love.forte.simbot.api.sender.Getter;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.component.mirai.message.MiraiMessageContentBuilder;
import love.forte.simbot.component.mirai.message.MiraiMessageContentBuilderFactory;
import love.forte.simbot.filter.MatchType;
import love.simbot.example.Gson.Ai;
import love.simbot.example.Utils_.SendGet;
import love.simbot.example.Utils_.String_arrList;
import love.simbot.example.Utils_.URL_JX;
import love.simbot.example.Utils_.randSend_;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Copyright (C), 2022-09-12
 * FileName: NewM
 * Author:   mirai
 * Date:     2022/9/12 14:21
 * Description:
 */
@SuppressWarnings({"all"})
@Beans
@OnGroup
public class MynewMessage {
    @Depend
    private MessageContentBuilderFactory messageBuilderFactory;

    private MessageContentBuilderFactory factory;

    public MynewMessage(MessageContentBuilderFactory factory) {
        this.factory = factory;
    }

    @OnGroup
    @Filter(atBot = true, matchType = MatchType.EQUALS, trim = true)
    public void sendMessage_Group(@NotNull Sender sender, @NotNull GroupMsg groupMsg) {
        // 得到一个消息构建器。
        MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
        String groupCode = groupMsg.getGroupInfo().getGroupCode();
        try {
            Gson gson = new Gson();
            Ai ai1 = gson.fromJson(new SendGet().SendGET("https://www.dreamling.xyz/API/xiao_ai_tong_xue/api.php?msg=", groupMsg.getText()), new Ai().getClass());
            MessageContent msg = builder
                    .at(groupMsg.getAccountInfo().getAccountCode())
                    .text(ai1.getDirective().getDisplayText())
                    .build();
            sender.sendGroupMsg(groupMsg, msg);
        } catch (JsonSyntaxException e) {
            sender.sendGroupMsg(groupMsg.getGroupInfo().getGroupCode(), new randSend_().randomArr(new String_arrList().getArray_at()));
        }
    }

    @OnGroup
    @Filter(value = "/青年大学习", matchType = MatchType.EQUALS, trim = true)
    public void youth_Study(GroupMsg groupMsg, Sender sender) {
        MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
        MessageContent msg = builder
                .image("https://h5.cyol.com/special/daxuexi/dr9ja2jkc6/images/end.jpg")
                .build();
        sender.sendGroupMsg(groupMsg.getGroupInfo().getGroupCode(), msg);
    }

    @OnGroup
    @Filter(value = "舔狗日记", matchType = MatchType.EQUALS, trim = true)
    public void t_Dog(@NotNull GroupMsg groupMsg, @NotNull Sender sender) {
        MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
        MessageContent msg = builder
                .text("[舔狗日记]\n")
                .text(new SendGet().SendGET("https://ovooa.com/API/tgrj/api.php", ""))
                .build();
        sender.sendGroupMsg(groupMsg.getGroupInfo().getGroupCode(), msg);
    }

    @OnGroup
    @Filter(value = "天气{{PlaceName}}", matchType = MatchType.REGEX_MATCHES, trim = true)
    public void tq(@NotNull GroupMsg groupMsg, @NotNull Sender sender, @FilterValue("PlaceName") String PlaceName) throws UnsupportedEncodingException {
//        String flag = "天气";
//        String tq = groupMsg.getText().substring(groupMsg.getText().indexOf(flag) + flag.length());
        MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
        MessageContent msg = builder
                .at(groupMsg.getAccountInfo().getAccountCode())
                .text("\n")
                .image("https://urlscan.io/liveshot/?width=880&height=600&url=https://wttr.in/" + new URL_JX().Getencode(PlaceName) + "?lang=zh")
                .build();
        sender.sendGroupMsg(groupMsg.getGroupInfo().getGroupCode(), msg);
    }

    @OnGroup
    public void onGroupNudge(Sender sender, @NotNull GroupMsg groupMsg, Getter getter) {
        MessageContent msgContent = groupMsg.getMsgContent();
        //戳一戳回复
//        System.out.println(msgContent);
        List<Neko> nudge = msgContent.getCats("nudge");
        for (Neko neko : nudge) {
            if (getter.getBotInfo().getAccountCode().equals(neko.get("target"))) {
                MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
                MessageContent msg = builder
                        .at(groupMsg.getAccountInfo().getAccountCode())
                        .text("\n[随机一言]")
                        .text("\n" + new JSONObject(new SendGet().SendGET("https://api.immmms.com/api/yiyan?encode=json&charset=utf-8", "")).optString("text"))
                        .image("http://ap1.iw233.cn/api.php?sort=xing")
                        .build();
                sender.sendGroupMsg(groupMsg.getGroupInfo().getGroupCode(), msg);
            }
        }
    }

    @OnGroup
    @Filter(value = "/dog/{{Quantity}}/{{text}}", matchType = MatchType.REGEX_MATCHES, trim = true)
    public void Dog(GroupMsg groupMsg, Sender sender, @FilterValue("Quantity") int Quantity, @FilterValue("text") String text) {
        if (!(factory instanceof MiraiMessageContentBuilderFactory)) {
            throw new RuntimeException("不支持mirai组件");
        }
        MiraiMessageContentBuilder builder = ((MiraiMessageContentBuilderFactory) factory).getMessageContentBuilder();
        MessageContentBuilder msgBuilder = messageBuilderFactory.getMessageContentBuilder();
        if (Quantity > 4900) {
            sender.sendGroupMsg(groupMsg.getGroupInfo().getGroupCode(), "大于4900字会坏掉的啊喂~~!!!");
        } else {
            MessageContent msg = msgBuilder
                    .at(groupMsg.getAccountInfo().getAccountCode())
                    .text("\n")
                    .text(new SendGet().SendGET("https://ovooa.com/API/dog/api.php?num=" + Quantity, "&msg=" + text + "&type=text"))
                    .build();
            builder.forwardMessage(forwardBuilder -> forwardBuilder.add(groupMsg, msg));
            sender.sendGroupMsg(groupMsg.getGroupInfo().getGroupCode(), builder.build());
        }
    }

    @OnGroup
    @Filter(value = "/cos", matchType = MatchType.EQUALS, trim = true)
    public void RandomCos(GroupMsg groupMsg, Sender sender) {
        if (!(factory instanceof MiraiMessageContentBuilderFactory)) {
            throw new RuntimeException("不支持mirai组件");
        }
        try {
            MessageContentBuilder messageContentBuilder = factory.getMessageContentBuilder();
            MiraiMessageContentBuilder builder = ((MiraiMessageContentBuilderFactory) factory).getMessageContentBuilder();

            MessageContentBuilder image = null;
            for (int i = 0; i < 15; i++) {
                image = messageContentBuilder.image(new SendGet().SendGET("http://api.duozy.cn/api/cos.php", ""));
            }
            MessageContentBuilder finalImage = image;
//            builder.forwardMessage(forwardBuilder -> {
//                forwardBuilder.add(groupMsg, finalImage.build());
//            });
            builder.forwardMessage(forwardBuilder -> forwardBuilder.add(groupMsg, finalImage.build()));
            sender.sendGroupMsg(groupMsg.getGroupInfo().getGroupCode(), builder.build());
        } catch (Exception e) {
            sender.sendGroupMsg(groupMsg.getGroupInfo().getGroupCode(), "呜呜呜~好康的在半路被打劫了");
        }
    }

    @OnGroup
    @Filter(value = "/ys", matchType = MatchType.EQUALS, trim = true)
    public void GenShen(@NotNull GroupMsg groupMsg, @NotNull Sender sender) {
        MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
        MessageContent msg = builder
                .at(groupMsg.getAccountInfo().getAccountCode())
                .text("\n")
                .image(new SendGet().SendGET("https://ovooa.com/API/yuan/api?type=text", ""))
                .build();
        sender.sendGroupMsg(groupMsg.getGroupInfo().getGroupCode(), msg);
    }

    @OnGroup
    @Filter(value = "/白毛", matchType = MatchType.EQUALS, trim = true)
    public void YinFa(@NotNull GroupMsg groupMsg, @NotNull Sender sender) {
        MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
        MessageContent msg = builder
                .at(groupMsg.getAccountInfo().getAccountCode())
                .text("\n")
                .image("http://api.iw233.cn/api.php?sort=yin")
                .build();
        sender.sendGroupMsg(groupMsg.getGroupInfo().getGroupCode(), msg);
    }

    @OnGroup
    @Filter(value = "/兽耳", matchType = MatchType.EQUALS, trim = true)
    public void MaoEr(@NotNull GroupMsg groupMsg, @NotNull Sender sender) {
        MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
        MessageContent msg = builder
                .at(groupMsg.getAccountInfo().getAccountCode())
                .text("\n")
                .image("http://api.iw233.cn/api.php?sort=cat")
                .build();
        sender.sendGroupMsg(groupMsg.getGroupInfo().getGroupCode(), msg);
    }

    @OnGroup
    @Filter(value = "/星空", matchType = MatchType.EQUALS, trim = true)
    public void XinKong(@NotNull GroupMsg groupMsg, @NotNull Sender sender) {
        MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
        MessageContent msg = builder
                .at(groupMsg.getAccountInfo().getAccountCode())
                .text("\n")
                .image("http://api.iw233.cn/api.php?sort=xing")
                .build();
        sender.sendGroupMsg(groupMsg.getGroupInfo().getGroupCode(), msg);
    }

}
