package cn.kgc.tangcco.lihaozhe.commons.faceUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年11月12日 下午4:41:44 
*    类说明 
*/
public class Base64Util {
	 private static final char last2byte = (char) Integer.parseInt("00000011", 2);
	    private static final char last4byte = (char) Integer.parseInt("00001111", 2);
	    private static final char last6byte = (char) Integer.parseInt("00111111", 2);
	    private static final char lead6byte = (char) Integer.parseInt("11111100", 2);
	    private static final char lead4byte = (char) Integer.parseInt("11110000", 2);
	    private static final char lead2byte = (char) Integer.parseInt("11000000", 2);
	    private static final char[] encodeTable = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

	    public Base64Util() {
	    }

	    public static String encode(byte[] from) {
	        StringBuilder to = new StringBuilder((int) ((double) from.length * 1.34D) + 3);
	        int num = 0;
	        char currentByte = 0;

	        int i;
	        for (i = 0; i < from.length; ++i) {
	            for (num %= 8; num < 8; num += 6) {
	                switch (num) {
	                    case 0:
	                        currentByte = (char) (from[i] & lead6byte);
	                        currentByte = (char) (currentByte >>> 2);
	                    case 1:
	                    case 3:
	                    case 5:
	                    default:
	                        break;
	                    case 2:
	                        currentByte = (char) (from[i] & last6byte);
	                        break;
	                    case 4:
	                        currentByte = (char) (from[i] & last4byte);
	                        currentByte = (char) (currentByte << 2);
	                        if (i + 1 < from.length) {
	                            currentByte = (char) (currentByte | (from[i + 1] & lead2byte) >>> 6);
	                        }
	                        break;
	                    case 6:
	                        currentByte = (char) (from[i] & last2byte);
	                        currentByte = (char) (currentByte << 4);
	                        if (i + 1 < from.length) {
	                            currentByte = (char) (currentByte | (from[i + 1] & lead4byte) >>> 4);
	                        }
	                }

	                to.append(encodeTable[currentByte]);
	            }
	        }

	        if (to.length() % 4 != 0) {
	            for (i = 4 - to.length() % 4; i > 0; --i) {
	                to.append("=");
	            }
	        }

	        return to.toString();
	    }
	    
	    public static String byteConverterBASE64(File file){
	        long size = file.length();
	        byte[] imageByte = new byte[(int)size];
	        FileInputStream fs = null;
	        BufferedInputStream bis = null;
	        try {
	            fs = new FileInputStream(file);
	            bis = new BufferedInputStream(fs);
	            bis.read(imageByte);
	        } catch (FileNotFoundException e) {
//	            log.error("文件"+file.getName()+"不能被找到："+e.getMessage());
	        } catch (IOException e) {
//	            log.error("byte转换BASE64出错："+e.getMessage());
	        } finally{
	            if(bis != null){
	                try {
	                    bis.close();
	                } catch (IOException e) {
//	                    log.error("关闭输入流出错："+e.getMessage());
	                }
	            }
	            if(fs != null){
	                try {
	                    fs.close();
	                } catch (IOException e) {
//	                    log.error("关闭输入流出错："+e.getMessage());
	                }
	            }
	        }
	        return (new sun.misc.BASE64Encoder()).encode(imageByte);  
	}
}
	 

