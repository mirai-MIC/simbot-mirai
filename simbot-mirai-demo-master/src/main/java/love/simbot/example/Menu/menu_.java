package love.simbot.example.Menu;

import love.forte.common.ioc.annotation.Beans;
import love.forte.common.ioc.annotation.Depend;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.MessageContent;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.MessageContentBuilderFactory;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.filter.MatchType;
import love.simbot.example.Utils_.SendGet;
import org.json.JSONObject;

/**
 * Copyright (C), 2022-09-14
 * FileName: menu_
 * Author:   mirai
 * Date:     2022/9/14 11:37
 * Description:
 */
@Beans
public class menu_ {

    @Depend
    private MessageContentBuilderFactory messageBuilderFactory;

    @OnGroup
    @Filter(value = "/菜单", matchType = MatchType.EQUALS, trim = true)
    public void updateMenu(GroupMsg groupMsg, Sender sender) {

        // 得到一个消息构建器。
        MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
        String menuGet = "<-------菜单------>\n" +
                "b<at>--------禁言一天\n" +
                "j<at>--------解开禁言\n" +
                "t<at>--------踢出艾特的人\n" +
                "a1-----------全体禁言\n" +
                "a0-----------解开全体禁言\n" +
                "/ga<at>------添加群管理\n" +
                "/gf<at>------取消群管理\n" +
                "/清空---------清空抽卡记录\n" +
                "以图搜图-------搜索图片来源<仅支持二次元>\n" +
                "/头衔<text>---获取头衔\n" +
                "bl----------查看Bot管理名单\n" +
                "添加<at>-----添加Bot管理\n" +
                "/删除<qq>-----删除Bot管理\n" +
                "@bot---------和机器人聊天\n" +
                "舔狗日记-------舔狗日记\n" +
                "戳一戳--------随机一言\n" +
                "抽签---------每日抽签\n" +
                "tx/名字/集数----腾讯视频解析\n" +
                "/cos---------20张cos图片\n" +
                "/白毛---------白毛\n" +
                "/兽耳---------兽耳\n" +
                "/星空---------星空\n" +
                "/dog/<字数>/<题目>废话生成\n" +
                "/青年大学习----完成图片\n" +
                "天气<地名>----省/市/部分区\n" +
                "点歌/<歌名-作者>----点歌\n" +
                "tr/{{类型}}/{{文字}}----翻译\n" +
                "gn/标题/正文/true/true/true/true\n" +
                "--->注释:发布群公告\n" +
                "--->true1:是否弹窗,true2:顶置\n" +
                "--->true3:发给新人,true4:是否需要确认}\n" +
                "--->均为布尔值\n" +
                "<------自动开启------>\n" +
                "每日早晚欢迎词\n" +
                "随机时间发言\n" +
                "每日播报新闻\n" +
                "每日发送摸鱼日历\n" +
                "启动给管理者发送群列表\n" +
                "启动给管理者发送好友列表\n" +
                "<------私聊------>\n" +
                "gl-----------输出群列表\n" +
                "fl-----------输出好友列表\n" +
                "dl<GroupId>--退出加入的群\n" +
                "df<FriendId>--删除好友\n" +
                "私聊消息自动转发给Bot管理员\n" +
                "s/<qq>/<msg>-按照格式发送\n" +
                "<----Bot管理系统提示---->\n" +
                "得到Bot权限后仅可以/踢/禁言/解除禁言\n" +
                "禁言前提是双方都是管理员或者群主\n" +
                "\n\n" +
                "[随机一言]\n" +
                new JSONObject(new SendGet().SendGET("https://api.immmms.com/api/yiyan?encode=json&charset=utf-8", "").trim()).optString("text");
        MessageContent msg = builder
                .text(menuGet)
                .build();
        sender.sendGroupMsg(groupMsg.getGroupInfo(), msg);
    }
}
