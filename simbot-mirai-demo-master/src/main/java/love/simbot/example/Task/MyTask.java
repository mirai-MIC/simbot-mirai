//package love.simbot.example.Task;
//
//import catcode.CatCodeUtil;
//import cn.hutool.core.io.IORuntimeException;
//import love.forte.common.ioc.annotation.Beans;
//import love.forte.common.ioc.annotation.Depend;
//import love.forte.simbot.api.message.results.SimpleGroupInfo;
//import love.forte.simbot.bot.BotManager;
//import love.forte.simbot.timer.Cron;
//import love.forte.simbot.timer.EnableTimeTask;
//import love.simbot.example.Admin_.fileInput;
//import love.simbot.example.Utils_.Properties_;
//import love.simbot.example.Utils_.String_arrList;
//import love.simbot.example.Utils_.randSend_;
//
//import java.io.IOException;
//
///**
// * Copyright (C), 2022-09-12
// * FileName: MyTask
// * Author:   mirai
// * Date:     2022/9/12 15:04
// * Description:
// */
//@SuppressWarnings({"all"})
//@EnableTimeTask
//@Beans
//public class MyTask {
//    private final String Master = new Properties_().getPropertyName("user","cache/botUser.porperties");
//    @Depend
//    private BotManager botManager;
//    private String LadAddress = "cache/LotAdd.txt";
//
//    public MyTask() throws IOException {
//    }
//
//    public String getLadAddress() {
//        return LadAddress;
//    }
//
//    public BotManager getBotManager() {
//        return botManager;
//    }
//
//
//    public String getMaster() {
//        return Master;
//    }
//
//
//    //    @Cron(value = "0/20 * * * * ? ")
//    @Cron(value = "0 0 6 * * ?")
//    public void task_mon() {
//        CatCodeUtil util = CatCodeUtil.INSTANCE;
//        String image = util.toCat("image", true, "url=https://ovooa.com/API/image_Tnem/?QR_parent=0");
//        for (SimpleGroupInfo groupInfo : getBotManager().getDefaultBot().getSender().GETTER.getGroupList()) {
//            int[] arr = {Integer.parseInt(groupInfo.getGroupCode())};
//            for (int i : arr) {
//                getBotManager().getDefaultBot().getSender().SENDER.sendGroupMsg(i, "早上好啊~~" + "\n" + image);
//            }
//        }
//    }
//
//    //    0 26 7 * * ?
//    @Cron(value = "0 0 7 * * ?")
//    public void task_moyu() {
//        try {
//            CatCodeUtil util = CatCodeUtil.INSTANCE;
//            String image = util.toCat("image", true, "url=https://api.emoao.com/api/moyu");
//            for (SimpleGroupInfo groupInfo : botManager.getDefaultBot().getSender().GETTER.getGroupList()) {
//                int[] arr = {Integer.parseInt(groupInfo.getGroupCode())};
//                for (int i : arr) {
//                    botManager.getDefaultBot().getSender().SENDER.sendGroupMsg(i, "今天该怎么摸鱼呢" + "\n" + image);
//                }
//            }
//        } catch (NumberFormatException e) {
//            botManager.getDefaultBot().getSender().SENDER.sendPrivateMsg(getMaster(),"请检查早晚安接口是否正常运行");
//        }
//    }
//
//    @Cron(value = "0 30 7 * * ?")
//    public void task_xw() {
//        CatCodeUtil util = CatCodeUtil.INSTANCE;
//        String image = util.toCat("image", true, "url=https://api.tsuixin.com/api/60s");
//        for (SimpleGroupInfo groupInfo : botManager.getDefaultBot().getSender().GETTER.getGroupList()) {
//            int[] arr = {Integer.parseInt(groupInfo.getGroupCode())};
//            for (int i : arr) {
//                botManager.getDefaultBot().getSender().SENDER.sendGroupMsg(i, "这是今天的新闻哦" + "\n" + image);
//            }
//        }
//    }
//
//    @Cron(value = "0 10 12 * * ?")
//    public void task_nun() {
//        for (SimpleGroupInfo groupInfo : botManager.getDefaultBot().getSender().GETTER.getGroupList()) {
//            botManager.getDefaultBot().getSender().SENDER.sendGroupMsg(groupInfo.getGroupCode(), "好快啊,这就到中午了吗");
//        }
//    }
//
//    //    @Cron(value = "0/20 * * * * ? ")
//    @Cron(value = "0 20 18 * * ?")
//    public void task_eve() {
//
//        try {
//            CatCodeUtil util = CatCodeUtil.INSTANCE;
//            String image = util.toCat("image", true, "url=https://ovooa.com/API/image_Tnem/?QR_parent=0");
//            for (SimpleGroupInfo groupInfo : botManager.getDefaultBot().getSender().GETTER.getGroupList()) {
//                int[] arr = {Integer.parseInt(groupInfo.getGroupCode())};
//                for (int i : arr) {
//                    botManager.getDefaultBot().getSender().SENDER.sendGroupMsg(i, "天黑了呐~~" + "\n" + image);
//                }
//            }
//        } catch (NumberFormatException e) {
//            botManager.getDefaultBot().getSender().SENDER.sendPrivateMsg(getMaster(),"早晚安接口可能网络异常或站点超时");
//        }
//    }
//
//    @Cron(value = "0 0 3,5 * * ?")
//    public void task_Random() {
//        for (SimpleGroupInfo groupInfo : botManager.getDefaultBot().getSender().GETTER.getGroupList()) {
//            botManager.getDefaultBot().getSender().SENDER.sendGroupMsg(groupInfo.getGroupCode(), new randSend_().randomArr(new String_arrList().getArray_eve()));
//        }
//    }
//
//    @Cron(value = "0 30 23 * * ?")
//    public void task_sleep() {
//        for (SimpleGroupInfo groupInfo : botManager.getDefaultBot().getSender().GETTER.getGroupList()) {
//            botManager.getDefaultBot().getSender().SENDER.sendGroupMsg(groupInfo.getGroupCode(), "好困啊~~,到睡觉时间了，晚安呐");
//        }
//    }
//
//    @Cron(value = "0 20 8,9,14,15,16,17,18,20 * * ?")
////    @Cron(value = "0/20 * * * * ? ")
//    public void task_xian() {
//        for (SimpleGroupInfo groupInfo : botManager.getDefaultBot().getSender().GETTER.getGroupList()) {
//            botManager.getDefaultBot().getSender().SENDER.sendGroupMsg(groupInfo.getGroupCode(), new randSend_().randomArr(new String_arrList().getArrayRandom()));
//        }
//    }
//
//    //    @Cron(value = "0/20 * * * * ? ")
//    @Cron(value = "0 2 0 * * ?")
//    public void getLotRandom() {
//        new fileInput().getBotAddMaster(getLadAddress(), "", false);
//        botManager.getDefaultBot().getSender().SENDER.sendPrivateMsg(getMaster(), "抽签记录已清空...");
//    }
//}