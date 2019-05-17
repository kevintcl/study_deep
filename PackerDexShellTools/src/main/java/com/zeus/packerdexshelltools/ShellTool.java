package com.zeus.packerdexshelltools;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.zip.Adler32;

public class ShellTool {
	
	// DES密钥
	private static final String DES_KEY = "8A1ED465A5942AD6";
	
	// 需要加壳的程序(源程序APK文件)
//	private static String apkPath = "/Users/lvxiangan/J2ee-workspace/DexShellTool/force/demo.apk"; // 任意指定路径文件
	private static String apkPath = "force/demo.apk"; // 工程根目录下
	// 解壳程序dex
//	private static String shellDexPath = "/Users/lvxiangan/J2ee-workspace/DexShellTool/force/shell.dex";
	private static String shellDexPath = "force/shell.dex";
	// 加壳后的dex
//	private static String newDexFile = "/Users/lvxiangan/J2ee-workspace/DexShellTool/force/classes.dex";
	private static String newDexFile = "force/classes.dex";
	
	

	public static void main(String[] args) {
		System.out.println("Hello World!");
		try {
			File payloadSrcFile = new File(apkPath);// 需要加壳的程序
			File unShellDexFile = new File(shellDexPath);// 解壳dex
			if (!payloadSrcFile.exists()) {
				System.out.println("APK不存在");
				return;
			}
			if (!unShellDexFile.exists()) {
				System.out.println("加壳程序的dex不存在");
				return;
			}
			
			byte[] payloadArray = encrpt(readFileBytes(payloadSrcFile), DES_KEY);
			byte[] unShellDexArray = readFileBytes(unShellDexFile);

			int payloadLen = payloadArray.length;
			int unShellDexLen = unShellDexArray.length;

			int totalLen = payloadLen + unShellDexLen + 4;

			byte[] newDex = new byte[totalLen];

			System.arraycopy(unShellDexArray, 0, newDex, 0, unShellDexLen);
			System.arraycopy(payloadArray, 0, newDex, unShellDexLen, payloadLen);
			System.arraycopy(intToByte(payloadLen), 0, newDex, totalLen - 4, 4);

			fixFileSizeHeader(newDex);
			fixSHA1Header(newDex);
			fixCheckSumHeader(newDex);

			File file = new File(newDexFile);
			if (!file.exists()) {
				file.createNewFile();
			}

			FileOutputStream localFileOutputStream = new FileOutputStream(newDexFile);
			localFileOutputStream.write(newDex);
			localFileOutputStream.flush();
			localFileOutputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改dex头，CheckSum 校验码
	 */
	private static void fixCheckSumHeader(byte[] dexBytes) {
		Adler32 adler = new Adler32();
		adler.update(dexBytes, 12, dexBytes.length - 12);// 从12到文件末尾计算校验码
		long value = adler.getValue();
		int va = (int) value;
		byte[] newcs = intToByte(va);
		// 高位在前，低位在前掉个个
		byte[] recs = new byte[4];
		for (int i = 0; i < 4; i++) {
			recs[i] = newcs[newcs.length - 1 - i];
			System.out.println(Integer.toHexString(newcs[i]));
		}
		System.arraycopy(recs, 0, dexBytes, 8, 4);// 效验码赋值（8-11）
		System.out.println(Long.toHexString(value));
		System.out.println();
	}

	/**
	 * 修改dex头 sha1值
	 */
	private static void fixSHA1Header(byte[] dexBytes) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(dexBytes, 32, dexBytes.length - 32);// 从32为到结束计算sha--1
		byte[] newdt = md.digest();
		System.arraycopy(newdt, 0, dexBytes, 12, 20);// 修改sha-1值（12-31）
		// 输出sha-1值，可有可无
		String hexstr = "";
		for (int i = 0; i < newdt.length; i++) {
			hexstr += Integer.toString((newdt[i] & 0xff) + 0x100, 16).substring(1);
		}
		System.out.println(hexstr);
	}

	/**
	 * 修改dex头 file_size值
	 */
	private static void fixFileSizeHeader(byte[] dexBytes) {
		// 新文件长度
		byte[] newfs = intToByte(dexBytes.length);
		System.out.println(Integer.toHexString(dexBytes.length));
		byte[] refs = new byte[4];
		// 高位在前，低位在前掉个个
		for (int i = 0; i < 4; i++) {
			refs[i] = newfs[newfs.length - 1 - i];
			System.out.println(Integer.toHexString(newfs[i]));
		}
		System.arraycopy(refs, 0, dexBytes, 32, 4);// 修改（32-35）
	}

	
//	private static byte[] encrpt(byte[] srcdata) {
//		for (int i = 0; i < srcdata.length; i++) {
//			srcdata[i] = (byte) (0xFF ^ srcdata[i]);
//		}
//		return srcdata;
//	}

	private static byte[] encrpt(byte[] srcData, String key) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = factory.generateSecret(desKeySpec);

			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
			return cipher.doFinal(srcData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	private static byte[] readFileBytes(File file) throws IOException {
		byte[] arrayOfByte = new byte[1024];
		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
		FileInputStream fis = new FileInputStream(file);
		while (true) {
			int i = fis.read(arrayOfByte);
			if (i != -1) {
				localByteArrayOutputStream.write(arrayOfByte, 0, i);
			} else {
				fis.close(); // 新增
				return localByteArrayOutputStream.toByteArray();
			}
		}
	}

	/**
	 * int 转byte[]
	 */
	public static byte[] intToByte(int number) {
		byte[] b = new byte[4];
		for (int i = 3; i >= 0; i--) {
			b[i] = (byte) (number % 256);
			number >>= 8;
		}
		return b;
	}

}
