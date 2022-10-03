package love.simbot.example.listener;

import love.forte.common.ioc.annotation.Beans;
import love.forte.common.ioc.annotation.Depend;
import love.forte.simbot.annotation.OnGroupAddRequest;
import love.forte.simbot.annotation.OnGroupMemberIncrease;
import love.forte.simbot.annotation.OnGroupMemberReduce;
import love.forte.simbot.api.message.MessageContent;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.MessageContentBuilderFactory;
import love.forte.simbot.api.message.assists.ActionMotivations;
import love.forte.simbot.api.message.containers.AccountInfo;
import love.forte.simbot.api.message.containers.BotInfo;
import love.forte.simbot.api.message.containers.GroupInfo;
import love.forte.simbot.api.message.events.GroupAddRequest;
import love.forte.simbot.api.message.events.GroupMemberIncrease;
import love.forte.simbot.api.message.events.GroupMemberReduce;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.api.sender.Setter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 这是一个 自动通过加群申请并自动迎新 的实例监听器。
 *
 * @author ForteScarlet
 */

@Beans
public class MyNewGroupMemberListen {

    /**
     * 用来缓存入群申请的时候所填的信息。
     */
    private static final Map<String, String> REQUEST_TEXT_MAP = new ConcurrentHashMap<>();
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MyNewGroupMemberListen.class);
    /**
     * 注入得到一个消息构建器工厂。
     */
    @Depend
    private MessageContentBuilderFactory messageBuilderFactory;


    @OnGroupAddRequest
    public void onRequest(@NotNull GroupAddRequest groupAddRequest, Setter setter) {
        AccountInfo accountInfo = groupAddRequest.getRequestAccountInfo();
        BotInfo botInfo = groupAddRequest.getBotInfo();
        if (!accountInfo.getAccountCode().equals(botInfo.getBotCode())) {
            String text = groupAddRequest.getText();
            if (text != null) {
                // 如果有，记录这一条信息。
                REQUEST_TEXT_MAP.put(accountInfo.getAccountCode(), text);
            }
            GroupInfo groupInfo = groupAddRequest.getGroupInfo();
            LOGGER.info("{}({}) 申请加入群 {}({}), 申请备注：{}",
                    accountInfo.getAccountNickname(), accountInfo.getAccountCode(),
                    groupInfo.getGroupName(), groupInfo.getGroupCode(),
                    text
            );
            setter.acceptGroupAddRequest(groupAddRequest.getFlag());
        }

    }

    @OnGroupMemberIncrease
    public void newGroupMember(GroupMemberIncrease groupMemberIncrease, Sender sender) {
        // 得到一个消息构建器。
        MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
        // 入群者信息
        AccountInfo accountInfo = groupMemberIncrease.getAccountInfo();
        MessageContent msg = builder
                // at当事人
                .at(accountInfo)
                .text(" 欢迎入群！\n")
                .image("https://api.xingzhige.com/API/bite/?qq=" + accountInfo.getAccountCode())
                .build();
        // 增加了人的群信息
        GroupInfo groupInfo = groupMemberIncrease.getGroupInfo();
        // 发送消息
        sender.sendGroupMsg(groupInfo, msg);
    }

    @OnGroupMemberReduce
    public void groupReduce(GroupMemberReduce groupMemberReduce, Sender sender) {
        MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
        AccountInfo accountInfo = groupMemberReduce.getAccountInfo();
        //自己离开了
        ActionMotivations leave = GroupMemberReduce.Type.LEAVE.getActionMotivations();
        try {
            String leaver = String.valueOf(leave);
            if ("PROACTIVE".equals(leaver)) {
                leaver = "永远的消失了";
            }
            MessageContent leaveBuMsg = builder
                    .image(Objects.requireNonNull(accountInfo.getAccountAvatar()))
                    .text("昵称: " + accountInfo.getAccountNickname() + "\n")
                    .text("账号: " + accountInfo.getAccountCode() + "\n")
                    .text(leaver + "\t你的群地位+1~~")
                    .build();
            sender.sendGroupMsg(groupMemberReduce.getGroupInfo(), leaveBuMsg);
        } catch (Exception e) {
            System.out.println("Bot 离开了");
        }
    }
}
