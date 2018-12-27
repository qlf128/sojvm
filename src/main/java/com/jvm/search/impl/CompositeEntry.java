package com.jvm.search.impl;

import com.jvm.constant.Constant;
import com.jvm.search.Entry;
import com.jvm.search.EntryFactory;
import com.jvm.search.EntryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: Entry List
 * @Author: WindPursuer
 * @Date 2018/12/26 3:27 PM
 */
public class CompositeEntry implements Entry {

    private static final Logger logger = LoggerFactory.getLogger(CompositeEntry.class);

    /**
     * entry集合
     */
    private List<Entry> entryList;


    /**
     * 路径集合
     */
    private String pathListString;

    public CompositeEntry() {
    }

    public CompositeEntry(String pathListString) {
        logger.info("初始化CompositeEntry, pathList:【{}】", pathListString);
        List<String> pathList = Arrays.asList(pathListString.split(Constant.pathListSeparator));
        for (String path : pathList) {
            this.entryList.add(EntryFactory.newEntry(path));
        }
    }

    /**
     * @Description: 读取Class方法
     * @Author: WindPursuer
     * @Date 2018/12/27 4:55 PM
     */
    @Override
    public EntryResult readClass(String className) {
        logger.info("CompositeEntry.readClass, className:【{}】", className);
        for (Entry entry : this.entryList) {
            EntryResult entryResult = entry.readClass(className);
            if (null != entryResult.getData()) {
                return entryResult;
            }
        }
        logger.error("CompositeEntry.readClass, not find class, className:【{}】", className);

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
