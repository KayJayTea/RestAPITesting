package restAssuredAutomationForOAuth2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;

public class ReusableMethods {
	
	static Properties prop = new Properties();
	
	public static String pwd() throws IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + File.separator + "writer.properties");
		prop.load(fis);

		String pwd = prop.getProperty("pwd");
		byte[] decode = Base64.decodeBase64(pwd);

		return new String(decode);
	}

}
