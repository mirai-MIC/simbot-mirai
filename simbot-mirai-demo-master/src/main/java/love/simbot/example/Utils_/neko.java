package love.simbot.example.Utils_;

import catcode.Neko;
import love.forte.common.ioc.annotation.Beans;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.MessageContent;
import love.forte.simbot.api.message.events.GroupMsg;

/**
 * Copyright (C), 2022-10-02
 * FileName: neko
 * Author:   mirai
 * Date:     2022/10/2 21:34
 * Description: 此方法为监听at事件
 *
 * @author mirai
 */
@Beans
public class neko {
    @OnGroup
    public Long sendNeko(GroupMsg groupMsg) {
        assert false;
        MessageContent msgContent = groupMsg.getMsgContent();
        Long aT = null;
        for (Neko at : msgContent.getCats("at")) {
            aT = Long.valueOf(at.get("code"));
        }
        return aT;
    }

    /**
     * 监听群聊图片事件
     */
    @OnGroup
    public String image(GroupMsg groupMsg) {
        assert false;
        MessageContent msg = groupMsg.getMsgContent();
        String imageUrl = null;
        for (Neko url : msg.getCats("image")) {
            imageUrl = url.get("url");
        }
        return imageUrl;
    }


}
