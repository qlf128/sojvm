package com.jvm.soClassLoader.domain;

import com.jvm.classReader.ClassFile;
import com.jvm.runTimeDateArea.model.LocalVars;
import com.jvm.runTimeDateArea.model.Slot;
import com.jvm.soClassLoader.constants.AccessFlagConstant;

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

    private SoClassLoader soClassLoader;

    private SoClass superClass;

    private SoClass[] interfaces;

    private int instanceSlotCount;

    private int staticSlotCount;

    private LocalVars localVars;

    private LocalVars staticVars;

    public SoClass(){

    }

    public SoClass(ClassFile classFile){
        this.accessFlags = classFile.getAccessFlags();
        this.name = classFile.getClassName();
        this.superClassName = classFile.getSuperClassName();
        this.interfaceNames = classFile.getInterfaceNames();
        this.constantPool = constantPool.newConstantPool(this, classFile.getConstantPool());
        this.fields = Field.newFields(this, classFile.getFields());
        this.methods = Method.newMethods(this,classFile.getMethods());
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

    /**
     * 本类是否可以被其他类访问
     * @param otherSoClass
     * @return
     */
    public boolean isAccessibleTo(SoClass otherSoClass){
        return this.isPublic()||(this.getPackagename()!=null&&this.getPackagename().equals(otherSoClass.getPackagename()));
    }

    public String getPackagename(){
        return this.name.substring(0,this.name.lastIndexOf("/"));
    }

    /**
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

    /**
     * 创建对象 wf
     */
}
