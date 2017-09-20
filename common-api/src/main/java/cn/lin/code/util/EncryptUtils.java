package cn.lin.code.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EncryptUtils {

	// 密钥文件放到比较安全的路径
	static String publicKey_file_path = "publickey.dat";
	static String privateKey_file_path = "privatekey.dat";
	
	
	static Map<String, String> keyMap = new HashMap<String, String>();

	public static void main(String[] args) throws Exception {

//		long t = System.currentTimeMillis();

		// 此方法只在初始化系统时使用，生成密钥文件后，保存好密钥文件
//		generateKeyAndWriteToFile();
		// //
		String str = "AAAadf阿道夫";

		String enStr = encrypt(str);
		System.out.println("enStr===" + enStr);

		String deStr = decrypt(enStr);
		System.out.println("deStr===" + deStr);
		
		
		
		

//		System.out.println(System.currentTimeMillis() - t);
	}

	/**
	 * 用公钥加密
	 * 
	 * @param str
	 * @return
	 */
	public static String encrypt(String str) {

		byte[] b = null;
		String result = null;
		try {
			long t1 = System.currentTimeMillis();
			b = RSAUtils.encryptByPublicKey(str.getBytes(), getPublicKey());
			System.out.println(System.currentTimeMillis() - t1);
			long t2 = System.currentTimeMillis();
			result = Base64Utils.encode(b);
			System.out.println(System.currentTimeMillis() - t2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 用私钥解密，只在服务端使用
	 * 
	 * @param str
	 * @return
	 */
	public static String decrypt(String str) {

		byte[] b = null;
		String result = null;
		try {
			b = RSAUtils.decryptByPrivateKey(Base64Utils.decode(str), getPrivateKey());
			result = new String(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getPublicKey() {

		long t = System.currentTimeMillis();

		String publicKey = null;
		try {
			publicKey = getPublicKeyFromFile();
			// publicKey = keyMap.get("publicKey");
			System.out.println("getPublicKey=" + publicKey);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("getPublicKey time=" + (System.currentTimeMillis() - t));

		return publicKey;
	}

	public static String getPrivateKey() {

		String privateKey = null;
		try {
			privateKey = getPrivateKeyFromFile();
//			privateKey = keyMap.get("privateKey");
			System.out.println("getPrivateKey=" + privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return privateKey;
	}

	static void generateKeyAndWriteToFile() {

		try {
			Map<String, Object> rsaKeyMap = RSAUtils.genKeyPairMap();

			String publicKey = RSAUtils.getPublicKey(rsaKeyMap);
			String privateKey = RSAUtils.getPrivateKey(rsaKeyMap);
			
//			keyMap.put("publicKey", publicKey);
//			keyMap.put("privateKey", privateKey);

			// 保存公钥到文件
			writeKeyToFile(publicKey, publicKey_file_path);

			// 保存私钥到文件
			writeKeyToFile(privateKey, privateKey_file_path);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static void writeKeyToFile(String key, String filePath) throws IOException {

		ObjectOutputStream oos = null;
		FileOutputStream fos = new FileOutputStream(filePath);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(key);
		fos.close();
	}

	static String getPublicKeyFromFile() throws Exception {

		return getKeyFromFile(publicKey_file_path);
	}

	static String getPrivateKeyFromFile() throws Exception {

		return getKeyFromFile(privateKey_file_path);
	}

	static String getKeyFromFile(String keyFile) throws Exception {

		FileInputStream fos = new FileInputStream(keyFile);
		ObjectInputStream ois = new ObjectInputStream(fos);
		String key = (String) ois.readObject();
		fos.close();
		return key;
	}

}
