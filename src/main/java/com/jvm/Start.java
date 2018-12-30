package com.jvm;

public class Start {

    public void start(){
        Cmd cmd = new Cmd().parseCmd();

        if(cmd.isVersionFlag()){
            System.out.println("version 0.0.1");
        }else if(cmd.isHelpFlag()  || cmd.getClazz() == ""){
            cmd.printUsage();
        }else{
            new JVM(cmd).start();
        }
    }

    public static void main(String[] args){
        new Start().start();
    }
}
