package com.jvm.search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 提供目录形式
 * @Author: WindPursuer
 * @Date 2018/12/25 7:17 PM
 */
public class DirSearchImpl implements Search {


    /**
     * 暂时单例（后续改动）
     */
    private static Search dirSearch = new DirSearchImpl();

    private DirSearchImpl(){}

    public static Search getInstance() {
        return dirSearch;
    }

    /**
     * @Description: 根据根地址查询class文件绝对路径
     * @Author: WindPursuer
     * @Date 2018/11/8 11:02 AM
     * @param path
     */
    @Override
    public List<String> searchClass(String path) {
        List<String> result = new ArrayList<>();
        File file = new File(path);
        searchClass(result, file);
        return result;
    }


    /**
     * @Description: 递归获取
     * @Author: WindPursuer
     * @Date 2018/12/25 7:28 PM
     */
    private void searchClass(List<String> result, File file) {
        Search search = FileSearchImpl.getInstance();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (null == files) {
                return;
            }
            for (File fileTemp : files) {
                searchClass(result, fileTemp);
            }
        } else {
            result.addAll(search.searchClass(file.getAbsolutePath()));
        }
    }
}
