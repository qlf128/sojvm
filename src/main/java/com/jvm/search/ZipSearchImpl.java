package com.jvm.search;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @Description: Zip解析
 * @Author: WindPursuer
 * @Date 2018/12/25 7:45 PM
 */
public class ZipSearchImpl implements Search {
    /**
     * @Description: 根据根地址查询class文件绝对路径
     * @Author: WindPursuer
     * @Date 2018/11/8 11:02 AM
     * @param path
     */
    @Override
    public List<String> searchClass(String path) {
        Search search = DirSearchImpl.getInstance();
        if (StringUtils.isEmpty(path) || !path.endsWith(".zip") || !path.endsWith(".jar")) {
            return new ArrayList<>();
        }
        if (!zipContraMultiFile(path)) {
            return new ArrayList<>();
        }
        return search.searchClass(path);
    }

    
    
    /**
     * @Description: 解压缩文件
     * @Author: WindPursuer
     * @Date 2018/12/25 7:50 PM
     */
    public static boolean zipContraMultiFile(String zipFilePath) {
        try {
            File srcFile = new File(zipFilePath);
            String dstFilePath = srcFile.getParentFile().getAbsolutePath();
            File outFile;
            ZipFile zipFile = new ZipFile(srcFile);
            ZipInputStream zipIn = new ZipInputStream(new FileInputStream(srcFile));
            ZipEntry entry;
            InputStream in;
            OutputStream out;
            while ((entry = zipIn.getNextEntry()) != null) {
                outFile = new File(dstFilePath + File.separator + entry.getName());
                if (!outFile.getParentFile().exists()) {
                    outFile.getParentFile().mkdirs();
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
