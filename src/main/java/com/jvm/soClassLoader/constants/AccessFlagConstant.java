package com.jvm.soClassLoader.constants;

/**
 * @author luao
 * @date 2018-11-12 14:22
 */
public class AccessFlagConstant {

    /**
     * class field method
     * 0001
     */
    public static final char ACC_PUBLIC = 0x0001 ;

    /**
     * field method
     * 0010
     */
    public static final char ACC_PRIVATE = 0x0002 ;

    /**
     * field method
     * 0100
     */
    public static final char ACC_PROTECTED = 0x0004;

    /**
     * field method
     * 1000
     */
    public static final char ACC_STATIC = 0x0008;

    /**
     * class field method
     * 0001 0000
     */
    public static final char ACC_FINAL = 0x0010;

    /**
     * class
     * 0010 0000
     */
    public static final char ACC_SUPER = 0x0020;

    /**
     * method
     * 0010 0000
     */
    public static final char ACC_SYNCHRONIZED = 0x0020;

    /**
     * field method
     * 0100 0000
     */
    public static final char ACC_VOLATILE = 0x0040;

    /**
     * method
     * 0100 0000
     */
    public static final char ACC_BRIDGE = 0x0040;

    /**
     * field method
     * 1000 0000
     */
    public static final char ACC_TRANSIENT = 0x0080;

    /**
     * method
     * 1000 0000
     */
    public static final char ACC_VARARGS = 0x0080;

    /**
     * method
     *0001 0000 0000
     */
    public static final char ACC_NATIVE = 0x0100;

    /**
     * class
     * 0010 0000 0000
     */
    public static final char ACC_INTERFACE = 0x0200;

    /**
     * class method
     * 0100 0000 0000
     */
    public static final char ACC_ABSTRACT = 0x0400;

    /**
     *  method
     *  1000 0000 0000
     */
    public static final char ACC_STRICT = 0x0800;

    /**
     *  class field method
     *  0001 0000 0000 0000
     */
    public static final char ACC_SYNTHETIC = 0x1000;

    /**
     *  class
     *  0100 0000 0000 0000
     */
    public static final char ACC_ANNOTATION = 0x2000;

    /**
     *  class field
     *  1000 0000 0000 0000
     */
    public static final char ACC_ENUM = 0x4000;
}
