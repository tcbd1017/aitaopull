package cn.kgc.tangcco.lihaozhe.commons.token;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import cn.kgc.tangcco.lihaozhe.commons.uuid.BaseUUID;

/**
 * @author 李昊哲
 * @version 1.0
 * 创建时间：	2019年7月21日 上午9:36:38 <br>
 * 类说明:	
 */
public class TokenProccessor {
	/*
     *单例设计模式（保证类的对象在内存中只有一个）
     *1、把类的构造函数私有
     *2、自己创建一个类的对象
     *3、对外提供一个公共的方法，返回类的对象
     */
    private TokenProccessor(){}
    
    private static final TokenProccessor instance = new TokenProccessor();
    
    /**
     * 返回类的对象
     * @return
     */
    public static TokenProccessor getInstance(){
        return instance;
    }
    
    /**
     * 生成Token
     * Token：Nv6RRuGEVvmGjB+jimI/gw==
     * @return
     */
    public String makeToken(){  //checkException
        String token = BaseUUID.generate();
        //数据指纹   128位长   16个字节  md5
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes("UTF-8"));
            //base64编码--任意二进制编码明文字符   adfsdfsdfsf
            // Encoder encoder = Base64.getEncoder();
            return Base64.getEncoder().encodeToString(md5);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
