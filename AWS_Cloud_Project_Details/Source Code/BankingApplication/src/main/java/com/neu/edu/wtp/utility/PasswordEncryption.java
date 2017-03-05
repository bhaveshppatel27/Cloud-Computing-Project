package com.neu.edu.wtp.utility;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
//import javax.util.Base64;

import org.apache.commons.codec.binary.Base64;
public class PasswordEncryption {

	private static final String ALGO = "AES";
	private static final byte[] keyValue = new byte[] { 'M', 'y', 'E', 'n', 'c', 'r', 'y', 'p', 't', 'i', 'o', 'n', 's','K','e','y'};

	// password encryption

	public static String encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		
		String encryptedValue = Base64.encodeBase64String(encVal);

		return encryptedValue;
	}

	public static String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = Base64.decodeBase64(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}
}
