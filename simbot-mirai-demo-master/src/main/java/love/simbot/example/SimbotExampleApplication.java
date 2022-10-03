package love.simbot.example;

import love.forte.common.configuration.Configuration;
import love.forte.simbot.annotation.SimbotApplication;
import love.forte.simbot.annotation.SimbotResource;
import love.forte.simbot.api.message.results.FriendInfo;
import love.forte.simbot.api.message.results.SimpleGroupInfo;
import love.forte.simbot.bot.Bot;
import love.forte.simbot.bot.BotManager;
import love.forte.simbot.core.SimbotApp;
import love.forte.simbot.core.SimbotContext;
import love.forte.simbot.core.SimbotProcess;
import love.simbot.example.Utils_.Properties_;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.LocalDateTime;
@SimbotApplication({
        @SimbotResource(value = "simbot.yml", orIgnore = true),
})
public class SimbotExampleApplication implements SimbotProcess {
    public static void main(String[] args) {
        SimbotApp.run(SimbotExampleApplication.class, args);
    }


    @Override
    public void post(@NotNull SimbotContext context) {
        BotManager botManager = context.getBotManager();
        System.out.println("<------正在启动------>");
        System.out.println("<------正在初始化项目------>");
        String userId;
        try {
            userId = new Properties_().getPropertyName("user","cache/botUser.porperties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("<------发送bot消息给管理------>");
        Bot bot = context.getBotManager().getDefaultBot();
        bot.getSender().SENDER.sendPrivateMsg(userId, "<--bot启动成功-->\n时间: " + LocalDateTime.now());
        StringBuilder grouping = new StringBuilder();
        grouping.append("<----群列表---->\n");
        int i = 0;
        for (SimpleGroupInfo groupInfo : botManager.getDefaultBot().getSender().GETTER.getGroupList()) {
            grouping.append("昵称: ").append(groupInfo.getGroupName()).append("\n").append("群号: ").append(groupInfo.getGroupCode()).append("\n");
            i++;
        }
        grouping.append("群数量: ").append(i);
        botManager.getDefaultBot().getSender().SENDER.sendPrivateMsg(userId, String.valueOf(grouping));
        /*
         * 好友列表
         *
         * */
        StringBuilder friendList = new StringBuilder();
        friendList.append("<----好友列表---->\n");
        int size = 0;
        for (FriendInfo friendInfo : botManager.getDefaultBot().getSender().GETTER.getFriendList()) {
            friendList.append("昵称: ").append(friendInfo.getAccountNickname()).append("\n").append("账号: ").append(friendInfo.getAccountCode()).append("\n");
            size++;
        }
        friendList.append("好友数量: ").append(size);
        botManager.getDefaultBot().getSender().SENDER.sendPrivateMsg(userId, String.valueOf(friendList));
        System.out.println("<------发送成功------>");
        System.out.println("<------项目成功启动------>");
    }

    //启动前
    @Override
    public void pre(@NotNull Configuration config) {
    }
}
