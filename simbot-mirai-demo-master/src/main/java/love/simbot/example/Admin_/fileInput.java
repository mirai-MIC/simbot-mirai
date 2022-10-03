package love.simbot.example.Admin_;

import love.forte.common.ioc.annotation.Beans;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/**
 * Copyright (C), 2022-09-14
 * FileName: read_
 * Author:   mirai
 * Date:     2022/9/14 15:27
 * Description:
 *
 * @author mirai
 */
@Beans
public class fileInput {
    //返回Bot管理名单
    public StringBuilder getBotMaster(String FileName) throws IOException {
        FileInputStream fis = new FileInputStream(FileName);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        isr.close();
        fis.close();
        return sb;
    }


    //遍历Bot权限名单  需要额外for循环
    public ArrayList<Object> getBotMasterMember(String FileName) throws IOException {
        FileInputStream fis = new FileInputStream(FileName);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String line;
        ArrayList<Object> objects = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            objects.add(line);
        }
        br.close();
        isr.close();
        fis.close();
        return objects;
    }

    //添加Bot权限名单
    public void getBotAddMaster(String groupCode, String catAt, boolean append) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(groupCode, append));
            bw.write(Objects.requireNonNull(catAt));
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                assert bw != null;
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //删除Bot权限方法
    public void delBotAddMaster(String fileName, String Id) throws IOException {
        //                     文件路径           输入Id
        ArrayList<Object> botMasterMember = new fileInput().getBotMasterMember(fileName);
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        ArrayList<String> array = new ArrayList<>();
        for (Object o : botMasterMember) {
            if (Id.trim().equals(String.valueOf(o))) {
                System.out.println(o);
            } else {
                array.add(String.valueOf(o));
            }
        }
        for (String s : array) {
            bw.write(s);
            bw.newLine();
            bw.flush();
        }
        bw.close();
    }

    //权限判别   抽签
    public HashSet<String> getBotPermission(String FileName) throws IOException {
        FileInputStream fis = new FileInputStream(FileName);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String line;
        HashSet<String> hashSet = new HashSet<>();
        while ((line = br.readLine()) != null) {
            hashSet.add(line);
        }
        br.close();
        isr.close();
        fis.close();
        return hashSet;
    }

}