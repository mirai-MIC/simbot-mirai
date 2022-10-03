package love.simbot.example.Plugin.plugins.serchImage;

import java.util.List;

/**
 * Copyright (C), 2022-10-02
 * FileName: DataDTO
 * Author:   mirai
 * Date:     2022/10/2 15:34
 * Description:
 */
public class DataDTO {
    private List<String> ext_urls;
    private String title;
    private int pixiv_id;
    private String member_name;
    private int member_id;

    public List<String> getExt_urls() {
        return ext_urls;
    }

    public void setExt_urls(List<String> ext_urls) {
        this.ext_urls = ext_urls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPixiv_id() {
        return pixiv_id;
    }

    public void setPixiv_id(int pixiv_id) {
        this.pixiv_id = pixiv_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }
}
