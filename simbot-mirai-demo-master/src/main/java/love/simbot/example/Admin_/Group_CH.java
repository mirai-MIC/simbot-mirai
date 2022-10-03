package love.simbot.example.Admin_;

import love.forte.common.ioc.annotation.Beans;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.FilterValue;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.annotation.OnPrivate;
import love.forte.simbot.api.message.assists.Permissions;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.api.sender.Getter;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.api.sender.Setter;
import love.forte.simbot.filter.MatchType;
import love.simbot.example.Utils_.Find;
import love.simbot.example.Utils_.Properties_;
import love.simbot.example.Utils_.neko;
import love.simbot.example.simpac.Get;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({"all"})
/**
 * Copyright (C), 2022-09-12
 * FileName: Group_CH
 * Author:   mirai
 * Date:     2022/9/12 20:39
 * Description:
 */


@Beans
public class Group_CH {
    private final String Master = new Properties_().getPropertyName("user","cache/botUser.porperties");
    private String filePath = "cache/userId.txt";

    private String LadAddress = "cache/LotAdd.txt";

    private HashSet<String> bot = new fileInput().getBotPermission(getFilePath());
    private Get get = new Get();

    public Group_CH() throws IOException {
    }

    public String getLadAddress() {
        return LadAddress;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getMaster() {
        return Master;
    }


    @OnGroup
    @Filter(value = "b/{{time}}", matchType = MatchType.REGEX_MATCHES, trim = true)
    public void groupAcf(GroupMsg groupMsg, Setter setter, Getter getter, Sender sender, @FilterValue("time") long time) throws IOException {
        Long GroupBanId = new neko().sendNeko(groupMsg);
        String accountId = get.accountId(groupMsg);
        String groupCode = get.getGroupCode(groupMsg);
        Permissions permissions = get.memberInfo(getter, groupMsg);
        if (groupMsg.getPermission().isMember() && permissions.isMember()) {
            sender.sendGroupMsg(groupMsg, "这个...呜，我也没有办法");
        } else if (accountId.equals(getMaster()) || permissions.isOwnerOrAdmin()) {
            if (new Find().find(bot, GroupBanId) == 1) {
                sender.sendGroupMsg(groupMsg, "太坏了，不能这样做的!!");
            } else {
                try {
                    setter.setGroupBan(groupCode, String.valueOf(GroupBanId), time, TimeUnit.SECONDS);
                } catch (Exception e) {
                    sender.sendGroupMsg(groupMsg, "没有权限的说");
                }
            }
        }
    }

    @OnGroup
    @Filter(value = "j", matchType = MatchType.EQUALS, trim = true)
    public void group_jie(GroupMsg groupMsg, Setter setter, Getter getter, Sender sender) throws IOException {
        String groupCode = get.getGroupCode(groupMsg);
        String groupId = get.groupId(groupMsg);
        String accountId = get.accountId(groupMsg);
        Permissions permissions = get.memberInfo(getter, groupMsg);
        Long GroupBanId = new neko().sendNeko(groupMsg);
        try {
            if (groupMsg.getPermission().isMember() && permissions.isMember()) {
                sender.sendGroupMsg(groupId, "这个...呜，我也没有办法");
            } else if (accountId.equals(getMaster()) || permissions.isOwnerOrAdmin()) {
                if (new Find().find(bot, GroupBanId) == 1) {
                    sender.sendGroupMsg(groupId, "太坏了，不能这样做的!!");
                } else {
                    setter.setGroupBan(groupCode, String.valueOf(GroupBanId), 0, TimeUnit.MINUTES);
                }
            }
        } catch (Exception e) {
            sender.sendGroupMsg(groupId, "想干嘛?");
        }
    }

    @OnGroup
    @Filter(value = "a1", matchType = MatchType.EQUALS, trim = true)
    public void Group_CH_(GroupMsg groupMsg, Setter setter, Getter getter, Sender sender) {
        Permissions permissions = get.groupMemberInfo(groupMsg);
        Permissions botPermissions = get.memberInfo(getter, groupMsg);
        String groupCode = get.getGroupCode(groupMsg);
        try {
            if (permissions.isMember()) {
                sender.sendGroupMsg(groupMsg, "想造反吗???");
            } else if (permissions.isOwnerOrAdmin() && botPermissions.isOwnerOrAdmin()) {
                setter.setGroupWholeBan(groupCode, true);
            }
        } catch (Exception e) {
            sender.sendGroupMsg(groupCode, "太荒唐了，你在干什么");
        }
    }

    @OnGroup
    @Filter(value = "a0", matchType = MatchType.EQUALS, trim = true)
    public void Group_all0(GroupMsg groupMsg, Setter setter, Sender sender) {
        Permissions permissions = get.groupMemberInfo(groupMsg);
        String groupCode = get.getGroupCode(groupMsg);
        try {
            if (permissions.isMember()) {
                sender.sendGroupMsg(groupCode, "您没有权限呢~");
            } else if (permissions.isOwnerOrAdmin()) {
                setter.setGroupWholeBan(groupCode, false);
            }
        } catch (Exception e) {
            sender.sendGroupMsg(groupMsg, "你在干什么!!!!");
        }
    }


    @OnGroup
    @Filter(value = "t", matchType = MatchType.EQUALS, trim = true)
    public void group_DB(GroupMsg groupMsg, Setter setter, Getter getter, Sender sender) throws IOException {
        Long GroupBanId = new neko().sendNeko(groupMsg);
        Permissions GetGroupPerissions = get.groupMemberInfo(groupMsg);
        Permissions permissions = get.memberInfo(getter, groupMsg);
        String groupId = get.groupId(groupMsg);
        String accountId = get.accountId(groupMsg);
        if (GetGroupPerissions.isMember() && permissions.isMember()) {
            sender.sendGroupMsg(groupId, "这个...呜，我也没有办法");
        } else if (accountId.equals(getMaster()) || permissions.isOwnerOrAdmin()) {
            if (new Find().find(bot, GroupBanId) == 1) {
                sender.sendGroupMsg(groupMsg, "你们不能这样的说");
            } else {
                try {
                    setter.setGroupMemberKick(groupId, String.valueOf(GroupBanId), "automatic", true);
                } catch (Exception e) {
                    sender.sendGroupMsg(groupMsg, "唔~你确定没有搞错吗?");
                }
            }
        }
    }

    @OnPrivate
    @Filter(value = "dl{{Id}}", matchType = MatchType.REGEX_MATCHES, trim = true)
    public void Group_dl(PrivateMsg privateMsg, Sender sender, Setter setter, @FilterValue("Id") String Id) throws IOException {
        Get get = new Get();
        String privateAccount = get.getPrivateAccount(privateMsg);
        if (privateAccount.equals(getMaster())) {
            try {
                setter.setGroupQuit(Id.trim(), true);
            } catch (Exception e) {
                sender.sendPrivateMsg(getMaster(), "呜~貌似出错了呢");
            }
            sender.sendPrivateMsg(getMaster(), "退出成功了呢");
        }
    }

    @OnGroup
    @Filter(value = "bl", matchType = MatchType.EQUALS, trim = true)
    public void readFile02(Sender sender, GroupMsg groupMsg) throws IOException {
        String accountId = get.accountId(groupMsg);
        if (accountId.equals(getMaster())) {
            StringBuilder botMaster = new fileInput().getBotMaster(getFilePath());
            sender.sendGroupMsg(groupMsg, "Bot管理名单\n" + botMaster);
        } else {
            sender.sendGroupMsg(groupMsg, "没有权限呢");
        }
    }

    @OnGroup
    @Filter(value = "添加", matchType = MatchType.EQUALS, trim = true)
    public void readFile01(GroupMsg groupMsg, Sender sender) throws IOException {
        Long GroupBanId = new neko().sendNeko(groupMsg);
        String accountId = get.accountId(groupMsg);
        if (accountId.equals(getMaster())) {
            new fileInput().getBotAddMaster(getFilePath(), String.valueOf(GroupBanId), true);
        } else {
            sender.sendGroupMsg(groupMsg, "您没有权限");
        }
    }

    @OnGroup
    @Filter(value = "/删除{{Id}}", matchType = MatchType.REGEX_MATCHES, trim = true)
    public void delBotMasterMember(GroupMsg groupMsg, Sender sender, @FilterValue("Id") String Id) throws IOException {
        String accountId = get.accountId(groupMsg);
        try {
            if (accountId.equals(getMaster())) {
                sender.sendGroupMsg(groupMsg, "好滴~");
                new fileInput().delBotAddMaster(getFilePath(), Id);
            } else {
                sender.sendGroupMsg(groupMsg, "不能这样子的!!!");
            }
        } catch (IOException e) {
            sender.sendGroupMsg(groupMsg, "呜~好像哪里怪怪的呢..");
        }
    }

    @OnGroup
    @Filter(value = "/清空", matchType = MatchType.EQUALS, trim = true)
    public void clear(Sender sender, GroupMsg groupMsg) throws IOException {
        String accountId = get.accountId(groupMsg);
        if (accountId.equals(getMaster())) {
            new fileInput().getBotAddMaster(getLadAddress(), "", false);
            sender.sendGroupMsg(groupMsg, "抽签记录已手动清空....");
        } else {
            sender.sendGroupMsg(groupMsg, "没有权限呢");
        }
    }

    @OnGroup
    @Filter(value = "gn/{{title}}/{{text}}/{{popUp}}/{{top}}/{{toNewMember}}/{{confirm}}", matchType = MatchType.REGEX_MATCHES, trim = true)
    public void S_G_N(GroupMsg groupMsg, Getter getter, Sender sender, @FilterValue("title") String title, @FilterValue("text") String text, @FilterValue("popUp") boolean popUp, @FilterValue("toNewMember") boolean toNewMember, @FilterValue("confirm") boolean confirm, @FilterValue("top") boolean top) {
        Permissions permissions = get.memberInfo(getter, groupMsg);
        Permissions GroupAdmin = get.groupMemberInfo(groupMsg);
        String accountId = get.accountId(groupMsg);
        try {
            if (GroupAdmin.isMember() && permissions.isMember()) {
                sender.sendGroupMsg(groupMsg, "想造反吗???");
            } else if (accountId.equals(getMaster()) || permissions.isOwnerOrAdmin()) {
                if (new Find().find(bot, Long.parseLong(accountId)) == 1) {
                    sender.sendGroupNotice(groupMsg, title, text, popUp, top, toNewMember, confirm);
                    sender.sendGroupMsg(groupMsg, "发送成功哦");
                }
            }
        } catch (Exception e) {
            sender.sendGroupMsg(groupMsg, "太荒唐了，你在干什么");
        }
    }
}

