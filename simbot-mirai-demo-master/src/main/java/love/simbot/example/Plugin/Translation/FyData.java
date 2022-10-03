package love.simbot.example.Plugin.Translation;

import java.util.List;

/**
 * Copyright (C), 2022-10-01
 * FileName: FyData
 * Author:   mirai
 * Date:     2022/10/1 16:40
 * Description:
 *
 * @author mirai
 */
public class FyData {

    private String type;
    private int errorCode;
    private int elapsedTime;
    private List<List<TranslateResultDTO>> translateResult;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public List<List<TranslateResultDTO>> getTranslateResult() {
        return translateResult;
    }

    public void setTranslateResult(List<List<TranslateResultDTO>> translateResult) {
        this.translateResult = translateResult;
    }
}
