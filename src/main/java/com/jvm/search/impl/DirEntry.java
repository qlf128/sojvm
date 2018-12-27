package com.jvm.search.impl;

import com.jvm.search.Entry;
import com.jvm.search.EntryResult;

import java.io.*;

/**
 * @Description: 文件
 * @Author: WindPursuer
 * @Date 2018/12/25 11:40 PM
 */
public class DirEntry implements Entry {

    /**
     * 绝对路径
     */
    private String absDir;

    public DirEntry(String absDir) {
        this.absDir = absDir;
    }

    @Override
    public EntryResult readClass(String className) {
        String filePath = this.absDir + "/" + className;
        File file = new File(filePath);
        EntryResult entryResult = new EntryResult(true);
        ByteArrayOutputStream out;
        try {
            FileInputStream in = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            while ((in.read(b)) != -1) {
                out.write(b, 0, b.length);
            }
            entryResult.setData(out.toByteArray());
            out.close();
            in.close();
        } catch (Exception e) {
            entryResult.setError(e.getMessage());
            entryResult.setSuccess(false);
            e.printStackTrace();
        }
        entryResult.setEntry(this);
        return entryResult;
    }

    public String getAbsDir() {
        return absDir;
    }

    public void setAbsDir(String absDir) {
        this.absDir = absDir;
    }
}
