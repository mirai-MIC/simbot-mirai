package love.simbot.example.Plugin.plugins.serchImage;

/**
 * Copyright (C), 2022-10-02
 * FileName: ResultsDTO
 * Author:   mirai
 * Date:     2022/10/2 15:34
 * Description:
 */
public class ResultsDTO {
    private HeaderDTOX header;
    private DataDTO data;

    public HeaderDTOX getHeader() {
        return header;
    }

    public void setHeader(HeaderDTOX header) {
        this.header = header;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }
}
