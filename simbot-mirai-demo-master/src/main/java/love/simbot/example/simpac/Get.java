package love.simbot.example.simpac;

import love.forte.simbot.api.message.assists.Permissions;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.api.sender.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * Copyright (C), 2022-10-02
 * FileName: Sim
 * Author:   mirai
 * Date:     2022/10/2 12:00
 * Description:
 *
 * @author mirai
 */

@Component
public class Get {
    /**
     * 获取当前QQ账号
     */
    public String prCode(@NotNull PrivateMsg privateMsg) {
        return privateMsg.getAccountInfo().getAccountCode();
    }

    /**
     * 获取当前QQ账号
     */
    public String prCode(@NotNull GroupMsg groupMsg) {
        return groupMsg.getAccountInfo().getAccountCode();
    }

    /**
     * 获取当前群号
     */
    public String grCode(@NotNull GroupMsg groupMsg) {
        return groupMsg.getGroupInfo().getGroupCode();
    }

    /**
     * 获取消息体
     */
    public String text(@NotNull PrivateMsg privateMsg) {
        return privateMsg.getText();
    }
    /**
     * 获取群Id
     */
    public String groupId(@NotNull GroupMsg groupMsg) {
        return groupMsg.getGroupInfo().getGroupCode();
    }

    /**
     * 获取群成员id
     */
    public String accountId(@NotNull GroupMsg groupMsg) {
        return groupMsg.getAccountInfo().getAccountCode();
    }

    /**
     * 获取Bot权限
     */
    public Permissions memberInfo(@NotNull Getter getter, @NotNull GroupMsg groupMsg) { return getter.getMemberInfo(groupMsg.getGroupInfo(), groupMsg.getBotInfo()).getPermission();}

    /**
     * 获取发消息人权限
     */
    public Permissions groupMemberInfo(@NotNull GroupMsg groupMsg) {
        return groupMsg.getPermission();
    }

    /**
     * 获取禁言数据
     */
    public String getGroupCode(@NotNull GroupMsg groupMsg) { return groupMsg.getGroupInfo().getGroupCode(); }

    /**
     * 获取私聊Id
     */
    public String getPrivateAccount(@NotNull PrivateMsg privateMsg) { return privateMsg.getAccountInfo().getAccountCode(); }
}
