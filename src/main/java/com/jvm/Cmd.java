package com.jvm;

public class Cmd {
    private boolean helpFlag;
    private boolean versionFlag;
    private boolean verboseClassFlag;
    private boolean verboseInstFlag;
    private String cpOption;
    private String xjreOption;
    private String clazz;
    private String[] args;

    public Cmd parseCmd(){
        Cmd cmd = new Cmd();
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
