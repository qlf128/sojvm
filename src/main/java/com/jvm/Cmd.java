package com.jvm;

import com.jvm.option.OptionClass;
import com.jvm.option.OptionProperty;
import com.jvm.search.ClassPath;
import com.jvm.search.EntryResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cmd {
    private boolean helpFlag;
    private boolean versionFlag;
    private boolean verboseClassFlag;
    private boolean verboseInstFlag;
    private String cpOption;
    private String xjreOption;
    private String clazz;
    private String[] args;

    private static final Logger logger = LoggerFactory.getLogger(Cmd.class);

    public Cmd parseCmd(String[] args){
        Cmd cmd = new Cmd();

        OptionProperty optionProperty = OptionClass.getInstance().parse(args);
        if (!StringUtils.isEmpty(optionProperty.getVersion())) {
            logger.info("Version:【{}】", optionProperty.getVersion());
            return cmd;
        }

        if (StringUtils.isEmpty(optionProperty.getClassName())) {
            logger.info("参数错误");
            return cmd;
        }
        String jreOption = optionProperty.getJrePath();
        String cpOption = optionProperty.getUserPath();
        String className = optionProperty.getClassName();
        ClassPath classPath = OptionClass.getInstance().parse(jreOption, cpOption);
        //EntryResult result = classPath.readClass(className);
        //logger.info("Class:【{}】", result.getData());

        cmd.setXjreOption(jreOption);
        cmd.setCpOption(cpOption);
        cmd.setClazz(className);
        cmd.setArgs(args);
        return cmd;
    }

    public void printUsage(){
        System.out.println("Usage: [-options] class [args...]\n");
    }


    public boolean isHelpFlag() {
        return helpFlag;
    }

    public void setHelpFlag(boolean helpFlag) {
        this.helpFlag = helpFlag;
    }

    public boolean isVersionFlag() {
        return versionFlag;
    }

    public void setVersionFlag(boolean versionFlag) {
        this.versionFlag = versionFlag;
    }

    public boolean isVerboseClassFlag() {
        return verboseClassFlag;
    }

    public void setVerboseClassFlag(boolean verboseClassFlag) {
        this.verboseClassFlag = verboseClassFlag;
    }

    public boolean isVerboseInstFlag() {
        return verboseInstFlag;
    }

    public void setVerboseInstFlag(boolean verboseInstFlag) {
        this.verboseInstFlag = verboseInstFlag;
    }

    public String getCpOption() {
        return cpOption;
    }

    public void setCpOption(String cpOption) {
        this.cpOption = cpOption;
    }

    public String getXjreOption() {
        return xjreOption;
    }

    public void setXjreOption(String xjreOption) {
        this.xjreOption = xjreOption;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
