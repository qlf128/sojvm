package com.jvm.search;

import java.util.List;

/**
 * @Description: 找寻class
 * @Author: WindPursuer
 * @Date 2018/11/8 10:59 AM
 */
public interface Search {

    /**
     * @Description: 根据根地址查询class文件绝对路径
     * @Author: WindPursuer
     * @Date 2018/11/8 11:02 AM
     */
    List<String> searchClass(String path);
}
