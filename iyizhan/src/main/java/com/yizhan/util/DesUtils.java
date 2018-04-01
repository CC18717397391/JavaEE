package com.yizhan.util;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.sun.crypto.provider.SunJCE;

public class DesUtils {

	/**默认密钥*/
	private static String strDefaultKey = "Clear";
	
	/**加密工具*/
	private Cipher encryptCipher;
	
	/**加密工具*/
	private Cipher decryptCipher;
	
	/**
	 * 默认构造，使用默认密钥
	 */
	public DesUtils() throws Exception{
		Security.addProvider(new SunJCE());
		Key key = getKey(strDefaultKey.getBytes());
		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}
	
	/**
	 * 带参构造，使用指定密钥
	 */
	public DesUtils(String str) throws Exception{
		Security.addProvider(new SunJCE());
		Key key = getKey(str.getBytes());
		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);
		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}
	
	
	/**
	 * 生成密钥
	 * 从指定字符串的字节数组生成密钥，取前8位
	 */
	private Key getKey(byte[] arrBTmp){
		byte[] arrB = new byte[8];
		for (int i = 0; i < arrBTmp.length&&i<arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}
		Key key = new SecretKeySpec(arrB, "DES");
		return key;
	}
	
	/**
	 * 将byte数组转换为16进制的字符串
	 */
	public static String byteArr2HexStr(byte[] arrB){
		int iLen = arrB.length;
		StringBuffer sb = new StringBuffer(iLen*2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			while(intTmp<0){
				intTmp = intTmp+256;
			}
			if(intTmp<16){
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp,16));
		}
		return sb.toString();
	}
	
	/**
	 * 将16进制的字符串转换为数组
	 */
	public static byte[] hexStr2ByteArr(String str){
		byte[] arrB = str.getBytes();
		int iLen = arrB.length;
		byte[] arrOut = new byte[iLen/2];
		for (int i = 0; i < iLen; i=i+2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i/2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}
	
	
	/**
	 * 加密字节数组
	 */
	public byte[] encrypt(byte[] arrB) throws Exception{
		return encryptCipher.doFinal(arrB);
	}
	
	/**
	 * 字符串加密
	 * @throws Exception 
	 */
	public String encrypt(String str) throws Exception{
		return byteArr2HexStr(encrypt(str.getBytes()));
	}
	
	/**
	 * 解密字节数组
	 */
	public byte[] decrypt(byte[] arrB) throws Exception{
		return decryptCipher.doFinal(arrB);
	}
	
	/**
	 * 解密字符串
	 * @throws Exception 
	 */
	public String decrypt(String str) throws Exception {
		return new String(decrypt(hexStr2ByteArr(str)));
	}
	
	public static void main(String[] args) throws Exception {
		String str = "admin123";
		DesUtils des = new DesUtils();
		System.out.println(des.encrypt(str));
	}
	
	
	
}
