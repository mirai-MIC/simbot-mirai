package love.simbot.example.Plugin.plugins.serchImage;

import catcode.Neko;
import com.google.gson.Gson;
import love.forte.common.ioc.annotation.Beans;
import love.forte.common.ioc.annotation.Depend;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.annotation.OnlySession;
import love.forte.simbot.api.message.MessageContent;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.MessageContentBuilderFactory;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.filter.MatchType;
import love.forte.simbot.listener.ContinuousSessionScopeContext;
import love.forte.simbot.listener.ListenerContext;
import love.forte.simbot.listener.SessionCallback;
import love.simbot.example.Utils_.Properties_;
import love.simbot.example.Utils_.SendGet;
import love.simbot.example.simpac.Get;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

/**
 * Copyright (C), 2022-09-21
 * FileName: Test
 * Author:   mirai
 * Date:     2022/9/21 20:38
 * Description:
 *
 * @author mirai
 */
@Beans
public class TestListener {
    private static final String IMAGE_GROUP = "IMAGE";

    public String getApiKey() {
        return apiKey;
    }

    private final String apiKey = new Properties_().getPropertyName("apiKey","cache/botUser.porperties");
    @Depend
    private MessageContentBuilderFactory messageBuilderFactory;

    public TestListener() throws IOException {
    }

    @OnGroup
    @Filter(value = "以图搜图", matchType = MatchType.EQUALS, trim = true)
    public void start(GroupMsg groupMsg, ListenerContext context, Sender sender) {
        final ContinuousSessionScopeContext sessionContext = (ContinuousSessionScopeContext) context.getContext(ListenerContext.Scope.CONTINUOUS_SESSION);
        assert sessionContext != null;
        MessageContentBuilder builder = messageBuilderFactory.getMessageContentBuilder();
        final String groupCode = groupMsg.getGroupInfo().getGroupCode();
        final String code = groupMsg.getAccountInfo().getAccountCode();
        // 通过群号和账号拼接一个此人在此群中的唯一key
        String key = groupCode + ":" + code;
        sender.sendGroupMsg(groupMsg, "请发送图片");
        Get get = new Get();
        final int[] url = new int[1];
        final SessionCallback<String> callback = SessionCallback.<String>builder().onResume(img -> {
            String getUrl = "https://saucenao.com/search.php?api_key=" + getApiKey() + "&output_type=2&db=5&numres=1&url=" + img;
            StringBuilder sb = new StringBuilder();
            Gson gson = new Gson();
            imageData imageData = gson.fromJson(new SendGet().SendGET(getUrl, ""), imageData.class);
            imageData.getResults().forEach(iM -> {
                DataDTO data = iM.getData();
                sb.append("置信度: ").append(iM.getHeader().getSimilarity()).append("\n");
                sb.append("Pixiv_id: ").append(data.getPixiv_id()).append("\n");
                sb.append("title: ").append(data.getTitle()).append("\n");
                sb.append("User: ").append(data.getMember_name()).append("\n");
                sb.append("Id: ").append(data.getMember_id()).append("\n");
                url[0] = data.getPixiv_id();
            });
            MessageContent msg = builder
                    .at(groupMsg.getAccountInfo().getAccountCode())
                    .text("\n")
                    .text(sb)
                    .text("https://pixivel.moe/illust/" + url[0])
                    .build();
            sender.sendGroupMsg(get.grCode(groupMsg), msg);

        }).onError(e -> {
            if (e instanceof TimeoutException) {
                System.out.println("onError: 超时啦: \n" + e);
                sender.sendGroupMsg(groupMsg, "onError: 超时了: \n" + e.getMessage());
            } else {
                System.out.println("onError: 出错啦: \n" + e);
                sender.sendGroupMsg(groupMsg, "onError: 出错啦: \n" + e.getMessage());
            }
        }).onCancel(e -> System.out.println("onCancel 关闭啦: \n" + Objects.requireNonNull(e).getMessage())).build();
        sessionContext.waiting(IMAGE_GROUP, key, callback);
    }

    @OnGroup
    @OnlySession(group = IMAGE_GROUP)
    public void imag(GroupMsg groupMsg, ListenerContext context) {
        // 得到session上下文。
        final ContinuousSessionScopeContext session = (ContinuousSessionScopeContext) context.getContext(ListenerContext.Scope.CONTINUOUS_SESSION);
        assert session != null;
        final String groupCode = groupMsg.getGroupInfo().getGroupCode();
        final String code = groupMsg.getAccountInfo().getAccountCode();
        MessageContent msgContent = groupMsg.getMsgContent();
        List<Neko> imageCats = msgContent.getCats("image");
        String key = groupCode + ":" + code;
        for (Neko imageCat : imageCats) {
            session.push(IMAGE_GROUP, key, imageCat.get("url"));
        }
    }
}
