package com.liyuan3210.dubboDemo.client.util.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * @ClassName NameConvert
 * @Description 参数命名转换
 * @Author <a href="mailto:franksoongyn@gmail.com">yexian</a>
 * @Date 2020/4/7 7:29 下午
 * @Version 1.0
 **/
public class NameConvert {

    /**
     * 驼峰转下划线
     *
     * @param param   参数名
     * @return    返回值
     */
    public static String camelToUnderline(String param) {
        if (StringUtils.isEmpty(param)) {
            return param;
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    sb.append('_');
                }
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    /**
     * 字符串是否为空
     *
     * @param data
     * @return
     */
    public static boolean isEmpty(String data) {
        if (data != null && data.length() > 0) {
            return false;
        }
        return true;
    }
    /**
     * 将字符串分割为list
     *
     * @param data
     * @param split
     * @return
     */
    public static List<String> stringToSList(String data, String split) {
        if (isEmpty(data) || isEmpty(split)) { return null; }
        return new ArrayList(Arrays.asList(data.split(split)));
    }
    /**
     * 下划线转驼峰
     *
     * @param param  参数名
     * @return 返回值
     */
    public static String underlineToCamel(String param) {
        if (StringUtils.isEmpty(param)) {
            return param;
        }
        List<String> oldKeyList = stringToSList(param, "_");
        if (oldKeyList == null || oldKeyList.isEmpty()) {
            return null;
        }
        List<String> operable = new ArrayList<>(oldKeyList);
        StringBuilder sb = new StringBuilder();
        sb.append(operable.get(0));
        operable.remove(0);
        if (!operable.isEmpty()) {
            for (String key : operable) {
                char[] cs = key.toCharArray();
                if (Character.isLowerCase(cs[0])) {
                    cs[0] -= 32;
                }
                sb.append(String.valueOf(cs));
            }
        }
        return sb.toString();
    }

}
