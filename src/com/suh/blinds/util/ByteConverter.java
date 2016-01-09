package com.suh.blinds.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Suhail Abood @link{mailto:suhail.abood@gmail.com}
 * @since 1.0.0
 */
public class ByteConverter {
    /**
     * Constant that defines little-endian data conversion mode. (0x00)
     */
    public static final int MODE_LITTLE_ENDIAN = 0;
    /**
     * Constant that defines big-endian data conversion mode. (0x01)
     */
    public static final int MODE_BIG_ENDIAN = 1;

    /**
     * Converts a long primitive value to a byte array
     * <p><bold>Note:</bold> This method converts the value using big-endian mode.
     * @param l the long value to convert
     * @return a byte array that represent the long value
     */
    public static byte[] toByte(long l){
        return toByte(l,MODE_BIG_ENDIAN);
    }

    /**
     * Converts an int primitive value to a byte array
     * <p><bold>Note:</bold> This method converts the value using big-endian mode.
     * @param l
     * @return
     */
    public static byte[] toByte(int l){
        return toByte(l,4,MODE_BIG_ENDIAN);
    }
    /**
     * Converts a long primitive value to a byte array given the long value and a mode of conversion
     * <p><bold>Note:</bold> This method converts the value using little-endian mode.
     * @param l
     * @param mode the mode to use for conversion
     * @return
     */
    public static byte[] toByte(long l, int mode){
        return toByte(l,8,mode);
    }

    /**
     * Converts a int primitive value to a byte array given the vlaue and a mode of conversion
     * <p><bold>Note:</bold> This method converts the value using little-endian mode.
     * @param l the value
     * @param mode the mode of conversion
     * @return
     */
    public static byte[] toByte(int l, int mode){
        return toByte(l,4,mode);
    }

    private static byte[] toByte(long l, int length, int mode){
        byte [] b = new byte[length];
        for(int i=0;i<length;i++){
            b[i] = mode == MODE_BIG_ENDIAN? (byte)((l >> ((length - 1) -i)*8)&0xFF):(byte)((l >> i)&0xFF);
        }
        return b;
    }
    /**
     * Converts a String value to a byte array. Uses getBytes.
     * @param str the string value
     * @return
     */
    public static byte[] toByte(String str){
        return str.getBytes();
    }
    /**
     * Converts a String value to a byte array given the value and a charset.
     * Note that this method will attempt to convert the String value using the provided charset.
     * However, in case of an exception being thrown, the method will revert back to getBytes() method.
     * @param str the string value
     * @param charset the character set to use
     * @return
     */
    public static byte[] toByte(String str, String charset){
        try {
            return str.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }

    /**
     * Converts a short value to a byte array, uses big-endian.
     * @param s the short value to convert
     * @return
     */
    public static byte[] toByte(short s){
        return toByte(s,2,MODE_BIG_ENDIAN);
    }

    /**
     * Converts a short value to a byte array given a value and a mode of conversion.
     * @param s the short value to convert
     * @return
     */
    public static byte[] toByte(short s, int mode){
        return toByte(s,2,mode);
    }
    /**
     * Converts a character value to a byte array, using big-endian.
     * @param c the short value to convert
     * @return
     */
    public static byte[] toByte(char c){
        return toByte(c,2,MODE_BIG_ENDIAN);
    }
    /**
     * Converts a character value to a byte array given a value and a mode of conversion.
     * @param c the character value to convert
     * @return
     */
    public static byte[] toByte(char c, int mode){
        return toByte(c,2,mode);
    }
    /**
     * Converts a boolean value to a byte array.
     * @param b the character value to convert
     * @return
     */
    public static byte[] toByte(boolean b){
        return new byte[]{b?(byte)1:(byte)0};
    }
    /**
     * Converts a ByteArraySerializable object to a byte array.
     * @param b the serializable instance.
     * @return
     */
    public static byte[] toByte(ByteArraySerializable b){
        return b.toBytes();
    }

    /**
     * Converts an object to a byte array
     * The method internally uses reflection to check if the object implements a method named "toBytes"
     * <p><bold>Note:</bold> This method converts the value using little-endian mode.
     * @param o the object to convert
     * @return
     */
    public static byte[] toByte(Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (o instanceof ByteArraySerializable){
            return ((ByteArraySerializable)o).toBytes();
        }else {
            Class clz = o.getClass();
            Method m = clz.getMethod("toBytes",null);
            if (m.getReturnType().getSimpleName().equals("byte[]")){
                return (byte[])m.invoke(o);
            }

        }
        return null;
    }

}
