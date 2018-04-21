package com.example.helper;


/**
 * Created by 王彦鹏 on 2016/9/14.
 */
public class Convert {

    public static String ByteArrayToHexString(byte[] inarray) { // converts byte
        // arrays to string
        int i, j, in;
        String[] hex = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
                "B", "C", "D", "E", "F" };
        String out = "";

        for (j = 0; j < inarray.length; ++j) {
            in = inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }
    private  static byte toByte(char c)
    {
        byte b=(byte)"0123456789ABCDEF".indexOf(c);
        return b;
    }
    public static byte[] HexStrToBytes(String HexStr)
    {
        HexStr=HexStr.toUpperCase();
        int len=(HexStr.length()/2);
        byte[] result=new byte[len];
        char[] achar=HexStr.toCharArray();
        for(int i=0;i<len;i++)
        {
            int pos=i*2;
            result[i]=(byte)(toByte(achar[pos])<<4 | toByte(achar[pos+1]));
        }
        return result;
    }

}