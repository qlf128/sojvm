package com.jvm.search.impl;

import com.jvm.search.Entry;
import com.jvm.search.EntryResult;

import java.io.*;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @Description: zip或jar形式文件路径
 * @Author: WindPursuer
 * @Date 2018/12/26 1:52 PM
 */
public class ZipEntry implements Entry {

    /**
     * 绝对路径
     */
    private String absPath;


    public ZipEntry() {
    }

    public ZipEntry(String absPath) {
        this.absPath = absPath;
    }

    public String getAbsPath() {
        return absPath;
    }

    public void setAbsPath(String absPath) {
        this.absPath = absPath;
    }

    @Override
    public EntryResult readClass(String className) {
        File srcFile = new File(this.absPath);
        int a = srcFile.getName().lastIndexOf(".");
        String dstFilePath = srcFile.getParentFile().getAbsolutePath() + File.separator + srcFile.getName().substring(0, a);
        zipContraMultiFile(srcFile, dstFilePath);
        File file = new File(dstFilePath);
        EntryResult entryResult = new EntryResult(true);
        for (File fileTemp : file.listFiles()) {
            if (fileTemp.getName().equals(className)) {
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
        }
        entryResult.setSuccess(false);
        entryResult.setError("not find");
        return entryResult;
    }



    /**
     * @Description: 解压缩文件
     * @Author: WindPursuer
     * @Date 2018/12/25 7:50 PM
     */
    public static boolean zipContraMultiFile(File srcFile, String dstFilePath) {
        try {

            File outFile;
            ZipFile zipFile = new ZipFile(srcFile);
            ZipInputStream zipIn = new ZipInputStream(new FileInputStream(srcFile));
            java.util.zip.ZipEntry entry;
            InputStream in;
            OutputStream out;
            while ((entry = zipIn.getNextEntry()) != null) {
                outFile = new File(dstFilePath + File.separator + entry.getName());
                if (entry.isDirectory()) {
                    if (!outFile.exists()) {
                        outFile.mkdirs();
                    }
                    continue;
                }
                in = zipFile.getInputStream(entry);
                out = new FileOutputStream(outFile);
                byte[] cache = new byte[1024];
                int nRead;
                while ((nRead = in.read(cache)) != -1) {
                    out.write(cache, 0, nRead);
                }
                out.close();
                in.close();
            }
            zipIn.close();
            zipFile.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
        return false;
    }

}
