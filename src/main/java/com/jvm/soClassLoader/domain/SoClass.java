package com.jvm.soClassLoader.domain;

import com.jvm.classReader.ClassFile;
import com.jvm.classReader.model.attribute.SourceFileAttribute;
import com.jvm.runTimeDateArea.model.LocalVars;
import com.jvm.runTimeDateArea.model.Slot;
import com.jvm.runTimeDateArea.model.SoArrayObject;
import com.jvm.runTimeDateArea.model.SoObject;
import com.jvm.soClassLoader.constants.AccessFlagConstant;
import org.apache.commons.lang3.StringUtils;
import sun.reflect.CallerSensitive;

/**
 * @author luao
 * @date 2018-11-12 11:43
 */
public class SoClass {
    private int accessFlags;

    private String name;

    private String superClassName;

    private String[] interfaceNames;

    private ConstantPool constantPool;

    private Field[] fields;

    private Method[] methods;

    private String sourceFile;

    private SoClassLoader soClassLoader;

    private SoClass superClass;

    private SoClass[] interfaces;

    private int instanceSlotCount;

    private int staticSlotCount;

    private LocalVars localVars;

    private LocalVars staticVars;
    //使用一个布尔字段标识类的<clinit>方法是否已经开始执行
    private boolean initStarted;

    public SoClass(){

    }

    public SoClass(ClassFile classFile){
        this.accessFlags = classFile.getAccessFlags();
        this.name = classFile.getClassName();
        this.superClassName = classFile.getSuperClassName();
        this.interfaceNames = classFile.getInterfaceNames();
        com.jvm.classReader.ConstantPool cp = classFile.getConstantPool();
        this.constantPool = com.jvm.soClassLoader.domain.ConstantPool.newConstantPool(this, cp);
        this.fields = Field.newFields(this, classFile.getFields());
        this.methods = Method.newMethods(this,classFile.getMethods());
        this.sourceFile = getSourceFile(classFile);
    }

    private String getSourceFile(ClassFile classFile){
        SourceFileAttribute sfAttr = classFile.sourceFileAttribute();

        if(sfAttr != null){
            return sfAttr.getFileName();
        }

        return "Unknown";
    }

    public boolean isPublic(){
        return 0!=(this.accessFlags&AccessFlagConstant.ACC_PUBLIC);
    }

    public boolean isFinal(){
        return 0!=(this.accessFlags&AccessFlagConstant.ACC_FINAL);
    }

    public boolean isSuper(){
        return 0!=(this.accessFlags&AccessFlagConstant.ACC_SUPER);
    }

    public boolean isSuperClassOf(SoClass other){
        return other.isSubClassOf(this);
    }

    public boolean isInterface(){
        return 0!=(this.accessFlags&AccessFlagConstant.ACC_INTERFACE);
    }

    public boolean isAbstract(){
        return 0!=(this.accessFlags&AccessFlagConstant.ACC_ABSTRACT);
    }

    public boolean isAnnotation(){
        return 0!=(this.accessFlags&AccessFlagConstant.ACC_ANNOTATION);
    }

    public boolean isSynthetic(){
        return 0!=(this.accessFlags&AccessFlagConstant.ACC_SYNTHETIC);
    }

    public boolean isEnum(){
        return 0!=(this.accessFlags&AccessFlagConstant.ACC_ENUM);
    }

    public void startInit(){
        this.initStarted = true;
    }

    /**
     * 本类是否可以被其他类访问
     * @param otherSoClass
     * @return
     */
    public boolean isAccessibleTo(SoClass otherSoClass){
        return this.isPublic()||(this.getPackageName()!=null&&this.getPackageName().equals(otherSoClass.getPackageName()));
    }

    public String getPackageName(){
        int index = this.name.lastIndexOf("/");
        if (index >= 0){
            return this.name.substring(0,index);
        }
        return "";
    }

    public Method getMainMethod(){
        return getStaticMethod("main","([Ljava/lang/String;)V");
    }
    public Method getClinitMethod(){
        return getStaticMethod("<clinit>","()V");
    }
    public Method getStaticMethod(String name, String descriptor){
        for (Method method : this.methods){
            if (method.isStatic() && method.getName().equals(name) && method.getDescriptor().equals(descriptor)){
                return method;
            }
        }
        return null;
    }
    public SoObject createObject(){
        return SoObject.createObject(this);
    }
    /**TODO ClassHierarchy中存在同名方法
     * soClass是否是本类的直接超类或间接超类
     * @param soClass
     * @return
     */
    public boolean isSubClassOf(SoClass soClass){
        if(soClass.superClass == this){
            return true;
        }
        if(soClass.superClass.isSubClassOf(this)){
            return true;
        }
        return false;
    }

    /**
     *  self implements iface
     */
    public  boolean isImplements(SoClass soClass){
        SoClass[] interfaces = soClass.interfaces;
        for (int i = 0; i <interfaces.length ; i++) {
            if(interfaces[i]==soClass || interfaces[i].isSubInterfaceOf(soClass)){
                return true;
            }
        }
        return false;
    }

    // self extends iface
    public boolean isSubInterfaceOf(SoClass soClass){
        SoClass[] interfaces = this.interfaces;

        for(SoClass soClassInter:interfaces){
            if(soClassInter==soClass || soClassInter.isSubInterfaceOf(soClass)){
                return true;
            }
        }
        return false;
    }

    public Field getField(String name, String descriptor, boolean isStatic){
        for(SoClass c = this; c != null; c = c.superClass){
            for(int i=0;i<c.fields.length;i++){
                Field field = fields[i];
                if(field.isStatic() == isStatic &&
                        field.getName().equals(name) &&
                        field.getDescriptor().equals(descriptor)){
                    return field;
                }
            }
        }
        return null;
    }


    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public void setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
    }

    public String[] getInterfaceNames() {
        return interfaceNames;
    }

    public void setInterfaceNames(String[] interfaceNames) {
        this.interfaceNames = interfaceNames;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public Field[] getFields() {
        return fields;
    }

    public void setFields(Field[] fields) {
        this.fields = fields;
    }

    public Method[] getMethods() {
        return methods;
    }

    public void setMethods(Method[] methods) {
        this.methods = methods;
    }

    public SoClassLoader getSoClassLoader() {
        return soClassLoader;
    }

    public void setSoClassLoader(SoClassLoader soClassLoader) {
        this.soClassLoader = soClassLoader;
    }

    public SoClass getSuperClass() {
        return superClass;
    }

    public void setSuperClass(SoClass superClass) {
        this.superClass = superClass;
    }

    public SoClass[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(SoClass[] interfaces) {
        this.interfaces = interfaces;
    }

    public int getInstanceSlotCount() {
        return instanceSlotCount;
    }

    public void setInstanceSlotCount(int instanceSlotCount) {
        this.instanceSlotCount = instanceSlotCount;
    }

    public int getStaticSlotCount() {
        return staticSlotCount;
    }

    public void setStaticSlotCount(int staticSlotCount) {
        this.staticSlotCount = staticSlotCount;
    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public void setLocalVars(LocalVars localVars) {
        this.localVars = localVars;
    }

    public LocalVars getStaticVars() {
        return staticVars;
    }

    public void setStaticVars(LocalVars staticVars) {
        this.staticVars = staticVars;
    }

    public boolean isInitStarted() {
        return initStarted;
    }

    public void setInitStarted(boolean initStarted) {
        this.initStarted = initStarted;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public boolean isJlObject(){
        return "java/lang/Object".equals(this.getName());
    }
    public boolean isJlCloneable(){
        return "java/lang/Cloneable".equals(this.getName());
    }
    public boolean isJioSerializable(){
        return "java/lang/Serializable".equals(this.getName());
    }

    public void setRefVar(String fieldName, String fieldDescriptor, SoObject ref){
        Field field = getField(fieldName, fieldDescriptor, true);
        staticVars.setObj(field.getSoltId(), ref);
    }

    public SoObject getRefVar(String fieldName, String fieldDescriptor){
        Field field = getField(fieldName, fieldDescriptor, true);
        return staticVars.getObj(field.getSoltId());
    }



    /**
     * 创建对象 wf new指令
     */
    public SoObject newObject(){
        return new SoObject(this);
    }

    @CallerSensitive
    public static native SoClass getCallerClass();


    /**
     * 数组得到class
     */
    public SoClass arrayClass(){
        String arrayClassName = ClassNameHelper.getArrayClassName(this.getName());
        return this.getSoClassLoader().loadClass(arrayClassName);
    }

    public String javaName(){
        return StringUtils.replace(name,"/",".",-1);
    }


    /*从SoArrayClass迁移过来 --begin*/

    public boolean isArray() {
        return getName().startsWith("[");
    }


    /**
     * 创建数组对象
     */
    public SoArrayObject newArray(int count) {

        if (!isArray()) {
            System.err.println(this.getName());
        }

        String str = getName().substring(0, 2);
        // z boolean；B byte；C char；D double；F float；I int；J long；S short；
        switch (str) {
            case "[Z":
                boolean[] z = new boolean[count];
                return new SoArrayObject(this,z);
            case "[B":
                byte[] b = new byte[count];
                return new SoArrayObject(this,b);
            case "[C":
                char[] c = new char[count];
                return new SoArrayObject(this,c);
            case "[S":
                short[] s = new short[count];
                return new SoArrayObject(this,s);
            case "[I":
                int[] i = new int[count];
                return new SoArrayObject(this,i);
            case "[J":
                long[] l = new long[count];
                return new SoArrayObject(this,l);
            case "[F":
                float[] f = new float[count];
                return new SoArrayObject(this,f);
            case "[D":
                double[] d = new double[count];
                return new SoArrayObject(this,d);
            default:
                SoObject[] o = new SoObject[count];
                return new SoArrayObject(this,o);
        }

    }

    public SoArrayClass componentClass(){

        String className = ClassNameHelper.getComponentClassName(this.getName());
        return (SoArrayClass) this.getSoClassLoader().loadClass(className);
    }

    /*从SoArrayClass迁移过来 --end*/
}
