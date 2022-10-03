package love.simbot.example.listener;

import catcode.Neko;
import love.forte.common.ioc.annotation.Beans;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.FilterValue;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.MessageContent;
import love.forte.simbot.api.message.containers.GroupAccountInfo;
import love.forte.simbot.api.message.containers.GroupInfo;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.message.results.GroupMemberInfo;
import love.forte.simbot.api.sender.Getter;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.api.sender.Setter;
import love.forte.simbot.filter.MatchType;
import love.simbot.example.Utils_.Properties_;
import love.simbot.example.Utils_.System_Time;
import love.simbot.example.Utils_.neko;
import love.simbot.example.simpac.Get;

import java.io.IOException;
import java.util.List;

/**
 * 群消息监听的示例类。
 * 所有需要被管理的类都需要标注 {@link Beans} 注解。
 *
 * @author ForteScarlet
 */
@Beans
public class MyGroupListen {

    /**
     * log
     */
    private final String userId = new Properties_().getPropertyName("user","cache/botUser.porperties");
    private final Get get = new Get();

    public MyGroupListen() throws IOException {
    }

    public String getMaster() {
        return userId;
    }

    /**
     * 此监听函数代表，收到消息的时候，将消息的各种信息打印出来。
     * <p>
     * 此处使用的是模板注解 {@link OnGroup}, 其代表监听一个群消息。
     * <p>
     * 由于你监听的是一个群消息，因此你可以通过 {@link GroupMsg} 作为参数来接收群消息内容。
     *
     * <p>
     * 注意！ 假如你发现你群消息发不出去（或者只有一些很短的消息能发出去）且没有任何报错，
     * 但是尝试后，发现 <b>私聊</b> 一切正常，能够发送，那么这是 <b>正常现象</b>！
     * <p>
     * 参考：
     */
    @OnGroup
    public void onGroupMsg(GroupMsg groupMsg) {
        MessageContent msgContent = groupMsg.getMsgContent();
        List<Neko> nudge = msgContent.getCats("nudge");
        GroupAccountInfo accountInfo = groupMsg.getAccountInfo();
        // 获取群信息
        GroupInfo groupInfo = groupMsg.getGroupInfo();

        String grMsg = "群 :" + groupInfo.getGroupName() + "(" + groupInfo.getGroupCode() + ")" + "\n" + "用户: " + accountInfo.getAccountNickname() + "(" + accountInfo.getAccountCode() + ")";
        System.out.println(new System_Time().getTime() + "\n" + grMsg);
        //输出群聊图片
        String image = new neko().image(groupMsg);
        if (image != null) {
            System.out.println(image);
        }
        for (Neko neko : nudge) {
            System.out.println("用户" + neko.get("from") + "\t\t戳了戳" + neko.get("target"));
        }
        System.out.println("权限等级: " + groupMsg.getPermission());
        System.out.println(groupMsg.getMsg() + "\n");
    }

    @OnGroup
    @Filter(value = "/头衔{{title}}", matchType = MatchType.REGEX_MATCHES, trim = true)
    public void onGroupTitled(GroupMsg groupMsg, Sender sender, Setter setter, Getter getter, @FilterValue("title") String title) {
        GroupMemberInfo memberInfo = getter.getMemberInfo(groupMsg.getGroupInfo(), groupMsg.getBotInfo());
        String groupId = get.groupId(groupMsg);
        String accountId = get.accountId(groupMsg);
        if (memberInfo.getPermission().isOwner()) {
            setter.setGroupMemberSpecialTitle(groupId, accountId, title.trim());
            sender.sendGroupMsg(groupId, groupMsg.getAccountInfo().getAccountNickname() + "头衔『" + title + "』拿好哦~");
        } else {
            sender.sendGroupMsg(groupId, "我还不是群主呢~");
        }
    }

    @OnGroup
    @Filter(value = "/ga", matchType = MatchType.CONTAINS, trim = true)
    public void onGroupAdmin(GroupMsg groupMsg, Sender sender, Setter setter, Getter getter) {
        Long groupBanId = new neko().sendNeko(groupMsg);
        boolean owner = new Get().memberInfo(getter, groupMsg).isOwner();
        String groupCode = new Get().groupId(groupMsg);
        String accountId = new Get().accountId(groupMsg);
        if (accountId.equals(getMaster()) && owner) {
            setter.setGroupAdmin(groupCode, String.valueOf(groupBanId), true);
        } else {
            sender.sendGroupMsg(groupCode, "您没有权限");
        }
    }

    @OnGroup
    @Filter(value = "/gf", matchType = MatchType.CONTAINS, trim = true)
    public void onGroupAdminFalse(GroupMsg groupMsg, Sender sender, Setter setter, Getter getter) {
        Long groupBanId = new neko().sendNeko(groupMsg);
        String accountId = get.accountId(groupMsg);
        String groupId = get.groupId(groupMsg);
        boolean owner = new Get().memberInfo(getter, groupMsg).isOwner();
        if (accountId.equals(getMaster()) && owner) {
            setter.setGroupAdmin(groupId, String.valueOf(groupBanId), false);
        } else {
            sender.sendGroupMsg(groupId, "您没有权限");
        }
    }
}
