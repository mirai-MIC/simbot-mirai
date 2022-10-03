package love.simbot.example.Plugin.plugins.serchImage;

import java.util.List;

/**
 * Copyright (C), 2022-10-02
 * FileName: imageData
 * Author:   mirai
 * Date:     2022/10/2 15:33
 * Description:
 *
 * @author mirai
 */
public class imageData {

    private HeaderDTO header;
    private List<ResultsDTO> results;

    public HeaderDTO getHeader() {
        return header;
    }

    public void setHeader(HeaderDTO header) {
        this.header = header;
    }

    public List<ResultsDTO> getResults() {
        return results;
    }

    public void setResults(List<ResultsDTO> results) {
        this.results = results;
    }
}
