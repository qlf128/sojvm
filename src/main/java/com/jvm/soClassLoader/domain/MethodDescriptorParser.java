package com.jvm.soClassLoader.domain;

public class MethodDescriptorParser {
    private String raw;
    private int offset;
    private MethodDescriptor parsed;

    public static MethodDescriptor parsedMethodDescriptor(String descriptor){
        MethodDescriptorParser parser = new MethodDescriptorParser();
        return parser.parse(descriptor);
    }

    public MethodDescriptor parse(String descriptor){
        this.raw = descriptor;
        this.parsed = new MethodDescriptor();
        startParams();
        parseParamTypes();
        endParams();
        parseReturnType();
        finish();
        return this.parsed;
    }

    private void throwException(){
        throw new RuntimeException("BAD descriptor:"+this.raw);
    }
    public void startParams(){
        if (readUint8() != '('){
            throwException();
        }
    }

    public void endParams(){
        if (readUint8() != ')'){
            throwException();
        }
    }

    public void finish(){
        if (this.offset != this.raw.length()){
            throwException();
        }
    }
    //原程序返回的是uint8,即char对应的ASCII码
    private int readUint8(){
        //返回char的ASCII编码只要(int)强转或直接赋值给int
        int bascii = this.raw.charAt(this.offset);
        this.offset++;
        return bascii;
    }

    private void unreadUint8(){
        this.offset --;
    }

    public void parseParamTypes(){
        while (true){
            String t = parseFieldType();
            if (t != null && !t.isEmpty()){
                this.parsed.addParameterType(t);
            } else {
                break;
            }
        }
    }

    public void parseReturnType(){
        if (readUint8() == 'V'){
            this.parsed.setReturnType("V");
            return;
        }
        unreadUint8();
        String t = parseFieldType();
        if (t != null && !t.isEmpty()){
            this.parsed.setReturnType(t);
            return;
        }
        throwException();
    }

    public String parseFieldType(){
        switch (readUint8()){
            case 'B':
                return "B";
            case 'C':
                return "C";
            case 'D':
                return "D";
            case 'F':
                return "F";
            case 'I':
                return "I";
            case 'J':
                return "J";
            case 'S':
                return "S";
            case 'Z':
                return "Z";
            case 'L':
                return parseObjectType();
            case '[':
                return parseArrayType();
            default:
                unreadUint8();
                return "";
        }
    }

    public String parseObjectType(){
        String unread = this.raw.substring(this.offset);
        //indexOf默认返回第一次出现的位置，与golang的strings.IndexRune()对应
        int semicolonIndex = unread.indexOf(";");
        if (semicolonIndex == -1){
            throwException();
        } else {
            int objStart = this.offset - 1;
            int objEnd = this.offset + semicolonIndex + 1;
            this.offset = objEnd;
            return this.raw.substring(objStart,objEnd);
        }
        return "";
    }

    //string的[startIndex:endIndex]等同于subString()
    public String parseArrayType(){
        int arrStart = this.offset - 1;
        parseFieldType();
        int arrEnd = this.offset;
        return this.raw.substring(arrStart,arrEnd);
    }



    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public MethodDescriptor getParsed() {
        return parsed;
    }

    public void setParsed(MethodDescriptor parsed) {
        this.parsed = parsed;
    }
}
