package love.simbot.example.Admin_;

import love.forte.common.ioc.annotation.Beans;
import love.forte.common.ioc.annotation.Depend;
import love.forte.simbot.annotation.*;
import love.forte.simbot.api.message.MessageContent;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.MessageContentBuilderFactory;
import love.forte.simbot.api.message.containers.AccountInfo;
import love.forte.simbot.api.message.events.FriendAddRequest;
import love.forte.simbot.api.message.events.GroupAddRequest;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.api.message.results.FriendInfo;
import love.forte.simbot.api.message.results.SimpleGroupInfo;
import love.forte.simbot.api.sender.Getter;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.api.sender.Setter;
import love.forte.simbot.bot.BotManager;
import love.forte.simbot.filter.MatchType;
import love.simbot.example.Utils_.Properties_;

import java.io.IOException;
import java.util.Objects;

/**
 * Copyright (C), 2022-09-14
 * FileName: MyPr
 * Author:   mirai
 * Date:     2022/9/14 20:45
 * Description:
 */
@Beans
public class MyPrivateListen_ {
    private final String Master = new Properties_().getPropertyName("user");
    @Depend
    private MessageContentBuilderFactory messageBuilderFactory;

    public MyPrivateListen_() throws IOException {
    }

    public String getMaster() {
        return Master;
    }

    @OnPrivate
    @Filter(value = "gl", matchType = MatchType.EQUALS, trim = true)
    public void privateMsg(PrivateMsg privateMsg, Sender sender, BotManager botManager) {
        AccountInfo accountInfo = privateMsg.getAccountInfo();
        if (accountInfo.getAccountCode().equals(getMaster())) {
            StringBuilder msg = new StringBuilder();
            msg.append("<----群列表---->\n");
            int i = 0;
            for (SimpleGroupInfo groupInfo : botManager.getDefaultBot().getSender().GETTER.getGroupList()) {
                msg.append("昵称: ").append(groupInfo.getGroupName()).append("\n").append("群号: ").append(groupInfo.getGroupCode()).append("\n");
                i++;
            }
            msg.append("群聊数量: ").append(i);
            sender.sendPrivateMsg(getMaster(), String.valueOf(msg));
        }
    }

    @OnPrivate
    @Filter(value = "fl", matchType = MatchType.EQUALS, trim = true)
    public void friendPr(PrivateMsg privateMsg, Sender sender, Getter getter) {
        AccountInfo accountInfo = privateMsg.getAccountInfo();
        if (accountInfo.getAccountCode().equals(getMaster())) {
            StringBuilder msg = new StringBuilder();
            msg.append("<----好友列表---->\n");
            int sum = 0;
            for (FriendInfo friendInfo : getter.getFriendList()) {
                msg.append("昵称: ").append(friendInfo.getAccountNickname()).append("\n").append("账号: ").append(friendInfo.getAccountCode()).append("\n");
                sum++;
            }
            msg.append("好友数量: ").append(sum);
            sender.sendPrivateMsg(getMaster(), String.valueOf(msg));
        }
    }

    @OnPrivate
    @Filter(value = "df{{QQ}}", matchType = MatchType.REGEX_MATCHES, trim = true)
    public void friendDt(PrivateMsg privateMsg, Sender sender, Setter setter, @FilterValue("QQ") String friend) {
        AccountInfo accountInfo = privateMsg.getAccountInfo();
        if (accountInfo.getAccountCode().equals(getMaster())) {
            try {
                setter.setFriendDelete(friend.trim());
                sender.sendPrivateMsg(getMaster(), friend.trim() + "呜,已经被处理掉了呢");
            } catch (Exception e) {
                sender.sendPrivateMsg(getMaster(), "失败");
            }
        }
    }

    @OnGroupAddRequest
    public void groupAddRequestInvitor(Setter setter, GroupAddRequest request, Sender sender) {
        System.out.println(request.getAccountInfo());
        System.out.println(request.getFlag());
        System.out.println(Objects.requireNonNull(request.getInvitor()).getInvitorCodeNumber());
        try {
            if (Objects.requireNonNull(request.getInvitor()).getInvitorCode().equals(getMaster())) {
                setter.setGroupAddRequest(request.getFlag(), true, false, null);
                sender.sendPrivateMsg(getMaster(), "邀请人: " + Objects.requireNonNull(request.getInvitor()).getInvitorCodeNumber() + "\n" + request.getFlag());
                sender.sendPrivateMsg(getMaster(), "已申请");
            } else {
                sender.sendPrivateMsg(getMaster(), "非法邀请: " + Objects.requireNonNull(request.getInvitor()).getInvitorCodeNumber() + "\n" + request.getFlag());
            }
        } catch (Exception e) {
            sender.sendPrivateMsg(getMaster(), e.getMessage());
        }
    }

    @OnFriendAddRequest
    public void pr(Sender sender, FriendAddRequest friendAddRequest, Setter setter) {
        AccountInfo addFriend = friendAddRequest.getAccountInfo();
        MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
        System.out.println(friendAddRequest.getFlag().getFlag().getId());
        MessageContent msg = builder
                .text("昵称: " + addFriend.getAccountNickname() + "\n" + "账号: " + addFriend.getAccountCodeNumber() + "\n")
                .text("请求添加好友\n")
                .text("标识码: " + friendAddRequest.getFlag().getFlag().getId() + "\n")
                .text("已自动同意")
                .build();
        sender.sendPrivateMsg(getMaster(), msg);
        setter.setFriendAddRequest(friendAddRequest.getFlag(), null, true, false);
    }

    @OnPrivate
    public void friendMessage(PrivateMsg privateMsg, Sender sender) {
        AccountInfo accountInfo = privateMsg.getAccountInfo();
        MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
        if (accountInfo.getAccountCode().equals(getMaster())) {
            System.out.println("排除master消息;");
        } else {
            MessageContent msg = builder
                    .text("昵称: " + Objects.requireNonNull(accountInfo.getAccountNickname()) + "\n")
                    .text("账号: " + accountInfo.getAccountCode() + "\n")
                    .text("发送了: ")
                    .text(privateMsg.getMsgContent())
                    .build();
            sender.sendPrivateMsg(getMaster(), msg);
        }
    }

    @OnPrivate
    @Filter(value = "s/{{friend}}/{{msg}}", matchType = MatchType.REGEX_MATCHES, trim = true)
    public void sendPrivateMsg(PrivateMsg privateMsg, Sender sender, @FilterValue("friend") String friend, @FilterValue("msg") String msg) {

        if (privateMsg.getAccountInfo().getAccountCode().equals(getMaster())) {
            try {
                sender.sendPrivateMsg(friend.trim(), msg.trim());
            } catch (Exception e) {
                sender.sendPrivateMsg(getMaster(), "好像并没有这个人");
            }
        }
    }
}