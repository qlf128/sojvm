package com.jvm.search;

import com.jvm.constant.Constant;
import com.jvm.search.impl.CompositeEntry;
import com.jvm.search.impl.DirEntry;
import com.jvm.search.impl.WildcardEntry;
import com.jvm.search.impl.ZipEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: Entry工厂类
 * @Author: WindPursuer
 * @Date 2018/12/27 5:05 PM
 */
public class EntryFactory {

    private static final Logger logger = LoggerFactory.getLogger(EntryFactory.class);

    /**
     * @Description: 获取Entry
     * @Author: WindPursuer
     * @Date 2018/12/27 5:06 PM
     */
    public static Entry newEntry(String path) {
        logger.info("Entry 初始化");

        if (path.contains(Constant.pathListSeparator)) {
            return new CompositeEntry(path);
        }
        if (path.endsWith("*")) {
            return new WildcardEntry(path);
        }
        if (path.endsWith(".jar") || path.endsWith(".JAR") || path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }
        return new DirEntry(path);
    }
}
