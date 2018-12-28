package com.jvm.search.impl;

import com.jvm.search.Entry;
import com.jvm.search.EntryFactory;
import com.jvm.search.EntryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * @Description: *结尾的路径解析
 * @Author: WindPursuer
 * @Date 2018/12/27 5:34 PM
 */
public class WildcardEntry implements Entry {

    private static final Logger logger = LoggerFactory.getLogger(CompositeEntry.class);

    /**
     * entry集合
     */
    private List<Entry> entryList = new ArrayList<>();


    public WildcardEntry() {

    }

    public WildcardEntry(String path) {
        logger.info("初始化CompositeEntry, pathList:【{}】", path);
        path = path.substring(0, path.length() - 1);
        File file = new File(path);
        for (File fileTemp : file.listFiles()) {
            if (fileTemp.isDirectory()) {
                continue;
            }
            String filePath = fileTemp.getAbsolutePath();
            if (filePath.endsWith(".jar") || filePath.endsWith(".JAR")) {
                this.entryList.add(EntryFactory.newEntry(filePath));
            }
        }
    }

    /**
     * @Description: readClass
     * @Author: WindPursuer
     * @Date 2018/12/27 7:51 PM
     */
    @Override
    public EntryResult readClass(String className) {
        logger.info("WildcardEntry.readClass, className:【{}】", className);
        for (Entry entry : this.entryList) {
            EntryResult entryResult = entry.readClass(className);
            if (null != entryResult.getData()) {
                return entryResult;
            }
        }
        logger.error("WildcardEntry.readClass, not find class, className:【{}】", className);
        EntryResult entryResult = new EntryResult(false);
        entryResult.setError("can not find");
        return entryResult;
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }
}
