package br.com.zitrus.api.util;

import java.util.regex.Pattern;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 18/09/2022
 */
public class UUIDUtil {
	
	private final static Pattern UUID_REGEX_PATTERN = Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");
	
	public static boolean isValidUUID(String str) {
	    if (str == null) {
	        return false;
	    }
	    return UUID_REGEX_PATTERN.matcher(str).matches();
	}
}