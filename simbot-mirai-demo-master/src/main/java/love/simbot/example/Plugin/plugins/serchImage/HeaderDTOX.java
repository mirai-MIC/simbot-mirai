package love.simbot.example.Plugin.plugins.serchImage;

/**
 * Copyright (C), 2022-10-02
 * FileName: HeaderDTOX
 * Author:   mirai
 * Date:     2022/10/2 15:34
 * Description:
 */
public class HeaderDTOX {
    private String similarity;
    private String thumbnail;
    private int index_id;
    private String index_name;
    private int dupes;
    private int hidden;

    public String getSimilarity() {
        return similarity;
    }

    public void setSimilarity(String similarity) {
        this.similarity = similarity;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getIndex_id() {
        return index_id;
    }

    public void setIndex_id(int index_id) {
        this.index_id = index_id;
    }

    public String getIndex_name() {
        return index_name;
    }

    public void setIndex_name(String index_name) {
        this.index_name = index_name;
    }

    public int getDupes() {
        return dupes;
    }

    public void setDupes(int dupes) {
        this.dupes = dupes;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }
}
