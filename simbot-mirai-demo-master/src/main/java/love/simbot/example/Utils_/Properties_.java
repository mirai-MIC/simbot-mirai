package love.simbot.example.Utils_;

import love.forte.common.ioc.annotation.Beans;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Copyright (C), 2022-09-14
 * FileName: Properties_
 * Author:   mirai
 * Date:     2022/9/14 17:00
 * Description: 此类为读取配置文件数据
 *
 * @author mirai
 */
@SuppressWarnings({"all"})
@Beans
public class Properties_ {
    public String getPropertyName(String propertyName,String path) throws IOException {
//        String path = "cache/botUser.porperties";
        Properties properties = new Properties();
        properties.load(new FileReader(path));
        return properties.getProperty(propertyName);
    }
}
