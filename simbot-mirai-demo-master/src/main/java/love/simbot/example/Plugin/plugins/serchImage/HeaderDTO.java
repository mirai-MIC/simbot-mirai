package love.simbot.example.Plugin.plugins.serchImage;

/**
 * Copyright (C), 2022-10-02
 * FileName: HeaderDTO
 * Author:   mirai
 * Date:     2022/10/2 15:34
 * Description:
 */
public class HeaderDTO {
    private String user_id;
    private String account_type;
    private String short_limit;
    private String long_limit;
    private int long_remaining;
    private int short_remaining;
    private int status;
    private int results_requested;
    private IndexDTO index;
    private String search_depth;
    private double minimum_similarity;
    private String query_image_display;
    private String query_image;
    private int results_returned;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getShort_limit() {
        return short_limit;
    }

    public void setShort_limit(String short_limit) {
        this.short_limit = short_limit;
    }

    public String getLong_limit() {
        return long_limit;
    }

    public void setLong_limit(String long_limit) {
        this.long_limit = long_limit;
    }

    public int getLong_remaining() {
        return long_remaining;
    }

    public void setLong_remaining(int long_remaining) {
        this.long_remaining = long_remaining;
    }

    public int getShort_remaining() {
        return short_remaining;
    }

    public void setShort_remaining(int short_remaining) {
        this.short_remaining = short_remaining;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getResults_requested() {
        return results_requested;
    }

    public void setResults_requested(int results_requested) {
        this.results_requested = results_requested;
    }

    public IndexDTO getIndex() {
        return index;
    }

    public void setIndex(IndexDTO index) {
        this.index = index;
    }

    public String getSearch_depth() {
        return search_depth;
    }

    public void setSearch_depth(String search_depth) {
        this.search_depth = search_depth;
    }

    public double getMinimum_similarity() {
        return minimum_similarity;
    }

    public void setMinimum_similarity(double minimum_similarity) {
        this.minimum_similarity = minimum_similarity;
    }

    public String getQuery_image_display() {
        return query_image_display;
    }

    public void setQuery_image_display(String query_image_display) {
        this.query_image_display = query_image_display;
    }

    public String getQuery_image() {
        return query_image;
    }

    public void setQuery_image(String query_image) {
        this.query_image = query_image;
    }

    public int getResults_returned() {
        return results_returned;
    }

    public void setResults_returned(int results_returned) {
        this.results_returned = results_returned;
    }
}
