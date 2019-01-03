package com.jvm.soClassLoader.domain;

import com.jvm.classReader.ClassFile;
import com.jvm.runTimeDateArea.model.LocalVars;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.runTimeDateArea.model.StringPool;
import com.jvm.search.ClassPath;
import com.jvm.search.EntryResult;
import com.jvm.search.ReadClass;
import com.jvm.soClassLoader.constants.AccessFlagConstant;
import com.jvm.soClassLoader.util.FileUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luao
 * @date 2018-11-12 16:09
 */
public class SoClassLoader {

    //private ReadClass readClass;
    private ClassPath classFilePath;
    private boolean verboseFlag;

    private Map<String,SoClass> soClassMap = new HashMap<>();;

    public SoClassLoader(){}

    public SoClassLoader(ClassPath classFilePath, boolean verboseFlag) {
        this.classFilePath = classFilePath;
        this.verboseFlag = verboseFlag;
    }

    /**
     * 把类数据加载到方法区
     * 类加载过程的一个阶段：通过一个类的完全限定查找此类字节码文件，并利用字节码文件创建一个SoClass对象
     * @param name
     */
    public SoClass loadClass(String name){
        if(soClassMap!=null&&soClassMap.containsKey(name)){
            return soClassMap.get(name);
        }
        // 添加加载Array
        if(name.startsWith("[")){
            return this.loadArrayClass(name);
        }
        return this.loadNonArrayClass(name);
    }

    /**
     * 加载非数组类
     * 1.️首先找到class文件并把数据读取到内存；
     * 2.然后解析class文件，生成虚拟机可以使用的类数据，并放入方法区；
     * 3.最后进行链接。
     * @param name class文件完整路径名
     * @return
     */
    private SoClass loadNonArrayClass(String name){
        byte[] data = this.readClass(name);
        SoClass soClass = this.defineClass(data);
        link(soClass);
        System.out.println("load SoClass :"+name);
        return soClass;
    }

    /**
     * 根据类路径读取字节码文件返回字节数据
     * @param name class文件完整路径名
     * @return
     */
    private byte[] readClass(String name){
        EntryResult result = classFilePath.readClass(name);
        byte[] bytes = null;
        if(result!=null&&result.isSuccess()){
            return result.getData();
        }
        return null;
    }

    /**
     * 加载SoClass，并加载它的超类以及直接接口
     * @param data
     * @return
     */
    private SoClass defineClass(byte[] data){
        //把class文件数据转换成SoClass结构体。
        SoClass soClass = parseClass(data);
        soClass.setSoClassLoader(this);
        resolveSuperClass(soClass);
        resolveInterfaces(soClass);
        soClassMap.put(soClass.getName(),soClass);
        return soClass;
    }

    /**
     * 将字节数据转成SoClass
     * @param data
     * @return
     */
    private SoClass parseClass(byte[] data){
        //获取ClassFile
        ClassFile classFile = new ClassFile(data);
        if(classFile == null){
            throw new NoClassDefFoundError();
        }
        return new SoClass(classFile);
    }

    /**
     * 加载超类
     * @param soClass
     */
    private void resolveSuperClass(SoClass soClass){
        if (!"java/lang/Object".equals(soClass.getName())){
            soClass.setSuperClass(soClass.getSoClassLoader().loadClass(soClass.getSuperClassName()));
        }
    }

    /**
     * 加载直接接口
     * @param soClass
     */
    private void resolveInterfaces(SoClass soClass){
        int interfaceCount = soClass.getInterfaceNames().length;
        if(soClass.getInterfaceNames()!=null&&interfaceCount>0){
            SoClass[] interfaces = new SoClass[interfaceCount];
            for (int i = 0; i < interfaceCount; i++) {
                interfaces[i] = soClass.getSoClassLoader().loadClass(soClass.getInterfaceNames()[i]);
            }
        }
    }

    private void link(SoClass soClass){
        verify(soClass);
        prepare(soClass);
        //TODO 解析
    }

    /**
     * 验证阶段
     * 确保Class文件的字节流中包含信息符合当前虚拟机要求，不会危害虚拟机自身安全。
     * 主要包括四种验证：文件格式验证，元数据验证，字节码验证，符号引用验证。
     * @param soClass
     */
    private void verify(SoClass soClass){
        //TODO 验证阶段


    }

    /**
     * 为类变量(即static修饰的字段变量)分配内存并且设置该类变量的初始值即0(如static int i=5;这里只将i初始化为0，至于5的值将在初始化时赋值)，
     * 这里不包含用final修饰的static，因为final在编译的时候就会分配了。
     * 注意这里不会为实例变量分配初始化，类变量会分配在方法区中，而实例变量是会随着对象一起分配到Java堆中
     * @param soClass
     */
    public void prepare(SoClass soClass){
        //准备阶段
        calcInstanceFieldSlotIds(soClass);
        calcStaticFieldSlotIds(soClass);
        allocAndInitStaticVars(soClass);
    }

    /**
     * 计算实例非静态字段的个数，同时给他们编号
     * @param soClass
     */
    private void calcInstanceFieldSlotIds(SoClass soClass){
        int soltId = 0;
        if(soClass.getSuperClass()!=null){
            soltId = soClass.getSuperClass().getInstanceSlotCount();
        }
        Field[] fields = soClass.getFields();
        for (int i = 0; i < fields.length; i++) {
            if(!fields[i].isStatic()){
                fields[i].setSoltId(soltId);
                soltId++;
                if(fields[i].isLongOrDouble()){
                    soltId++;
                }
            }
        }
        soClass.setInstanceSlotCount(soltId);
    }

    /**
     *计算实例静态字段的个数，同时给他们编号
     * @param soClass
     */
    private void calcStaticFieldSlotIds(SoClass soClass){
        int soltId = 0;
        if(soClass.getSuperClass()!=null){
            soltId = soClass.getSuperClass().getInstanceSlotCount();
        }
        Field[] fields = soClass.getFields();
        for (int i = 0; i < fields.length; i++) {
            if(fields[i].isStatic()){
                fields[i].setSoltId(soltId);
                soltId++;
                if(fields[i].isLongOrDouble()){
                    soltId++;
                }
            }
        }
        soClass.setStaticSlotCount(soltId);
    }

    /**
     * 给类变量分配空间，然后给它们赋予初始值
     * @param soClass
     */
    private void allocAndInitStaticVars(SoClass soClass){
        soClass.setStaticVars(new LocalVars(soClass.getStaticSlotCount()));
        for (int i = 0; i < soClass.getFields().length; i++) {
            if (soClass.getFields()[i].isStatic() && soClass.getFields()[i].isFinal()) {
                initStaticFinalVar(soClass, soClass.getFields()[i]);
            }
        }
    }

    /**
     * 从常量池中加载常量值，然后给静态变量赋值
     * @param soClass
     * @param field
     */
    private void initStaticFinalVar(SoClass soClass,Field field){
        LocalVars vars = soClass.getStaticVars();
        ConstantPool cp = soClass.getConstantPool();
        int cpIndex = field.getConstValueIndex();
        int slotId = field.getSoltId();
        if (cpIndex>0){
            Object constant = cp.getConstant(cpIndex);
            switch (field.getDescriptor()){
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    vars.setInt(slotId, (int)constant);
                    break;
                case "J":
                    vars.setLong(slotId, (long)constant);
                    break;
                case "F":
                    vars.setFloat(slotId, (float)constant);
                    break;
                case "D":
                    vars.setDouble(slotId, (double)constant);
                    break;
                case "Ljava/lang/String;":
                    String str =  (String) cp.getConstant(cpIndex);
                    SoObject soStr = StringPool.jString(soClass.getSoClassLoader(), str);
                    vars.setObj(slotId,soStr);
                    break;
                default:
                    break;
            }
        }
    }


    public ClassPath getClassFilePath() {
        return classFilePath;
    }

    public void setClassFilePath(ClassPath classFilePath) {
        this.classFilePath = classFilePath;
    }

    public boolean isVerboseFlag() {
        return verboseFlag;
    }

    public void setVerboseFlag(boolean verboseFlag) {
        this.verboseFlag = verboseFlag;
    }

    public Map<String, SoClass> getSoClassMap() {
        return soClassMap;
    }

    public void setSoClassMap(Map<String, SoClass> soClassMap) {
        this.soClassMap = soClassMap;
    }


    /**
     *  新增加载数组对象
     */
    public SoClass loadArrayClass(String name){

        SoClass soClass = new SoClass();
        soClass.setAccessFlags(AccessFlagConstant.ACC_PUBLIC);
        soClass.setName(name);
        soClass.setSoClassLoader(this);
        soClass.setInitStarted(true);
        soClass.setSuperClass(this.loadClass("java/lang/Object"));
        SoClass[] interfaces = new SoClass[2];
        interfaces[0] = this.loadClass("java/lang/Cloneable");
        interfaces[1] = this.loadClass("java/io/Serializable");
        soClass.setInterfaces(interfaces);
        HashMap map = new HashMap<>();
        map.put(name,soClass);
        this.setSoClassMap(map);
        return soClass;
    }

}


