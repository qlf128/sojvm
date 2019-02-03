package com.jvm.search;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 具体文件获取
 * @Author: WindPursuer
 * @Date 2018/11/8 11:03 AM
 */
public class FileSearchImpl implements Search {

    private static final Logger logger = LoggerFactory.getLogger(FileSearchImpl.class);

    /**
     * 暂时单例（后续改动）
     */
    private static Search fileSearch = new FileSearchImpl();

    private FileSearchImpl(){}

    public static Search getInstance() {
        return fileSearch;
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
        if (StringUtils.isEmpty(path) || !path.endsWith(".class")) {
            logger.info("param is error, 返回空");
            return result;
        }
        result.add(path);
        return result;
    }
}
