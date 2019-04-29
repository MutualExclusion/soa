package cn.solarcat.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {
	public static String encode(String text){
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] buffer = digest.digest(text.getBytes());
			// byte -128 ---- 127
			StringBuffer sb = new StringBuffer();
			for (byte b : buffer) {
				int a = b & 0xff;
				// Log.d(TAG, "" + a);
				String hex = Integer.toHexString(a);

				if (hex.length() == 1) {
					hex = 0 + hex;
				}
				sb.append(hex);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
