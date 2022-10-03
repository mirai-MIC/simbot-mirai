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
        // 此事件的“申请者”
        AccountInfo accountInfo = groupAddRequest.getRequestAccountInfo();
        // 收到此事件的bot
        BotInfo botInfo = groupAddRequest.getBotInfo();

        // 如果上述两者的账号不相同，则说明此事件不是bot被邀请，而是别人申请入群。
        // 这步判断操作似乎很繁琐，未来版本可能会提供更简洁的方案
        // 如果你有好的点子，可以通过 github issue 或 github pr向simbot提出。 https://github.com/ForteScarlet/simpler-robot
        if (!accountInfo.getAccountCode().equals(botInfo.getBotCode())) {
            // 获取入群的时候的申请消息（如果有的话
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


            // 通过申请
            // 通过setter来通过加群申请有多个方法：
            // 方法1：acceptGroupAddRequest(flag)
            // flag 是请求事件的一个”标识“
            setter.acceptGroupAddRequest(groupAddRequest.getFlag());

            // 方法2：setGroupAddRequest(flag, agree, blockList, why)
            // 4个参数分别代表：标识、是否同意、是否加入黑名单(一般是只有在拒绝时生效, 但是mirai目前不支持此参数)、以及这么操作的原因(一般是在拒绝时生效, 可以为null)
            // setter.setGroupAddRequest(groupAddRequest.getFlag(), true, false, null);

            // 方法3：return Reply.accept()
            // 将方法返回值设置为 ReplyAble 或者 Reply, 然后直接返回 Reply.accept() 实例来快速同意申请。
            // 这种方法依靠的是响应值处理器。文档参考：https://www.yuque.com/simpler-robot/simpler-robot-doc/aioxhh

            // 方法4：鉴于目前 Flag 的设计仍然有一些风险和隐患，未来可能会提供其他更便捷的方式对相关事件进行处理。

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
        // 得到一个消息构建器。
        MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
        AccountInfo accountInfo = groupMemberReduce.getAccountInfo();
        //自己离开了
        ActionMotivations leave = GroupMemberReduce.Type.LEAVE.getActionMotivations();
        try {
            String leaver_ = String.valueOf(leave);
            if ("PROACTIVE".equals(leaver_)) {
                leaver_ = "永远的消失了";
            }
            MessageContent leave_bu_msg = builder
                    .image(Objects.requireNonNull(accountInfo.getAccountAvatar()))
                    .text("昵称: " + accountInfo.getAccountNickname() + "\n")
                    .text("账号: " + accountInfo.getAccountCode() + "\n")
                    .text(leaver_ + "\t你的群地位+1~~")
                    .build();
            sender.sendGroupMsg(groupMemberReduce.getGroupInfo(), leave_bu_msg);
        } catch (Exception e) {
            System.out.println("Bot 离开了");
        }
    }
}
