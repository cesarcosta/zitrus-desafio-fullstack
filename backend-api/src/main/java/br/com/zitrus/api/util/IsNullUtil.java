package br.com.zitrus.api.util;

import java.util.Collection;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
public class IsNullUtil {

	private IsNullUtil() {}

	public static boolean isNull(Object value) {
		return value == null;
	}

	public static boolean isNullOrEmpty(String value) {
		return (value == null) || (value.trim().length() == 0);
	}

	public static boolean isNullOrEmpty(Object value) {
		return value == null;
	}

	public static <T> boolean isNullOrEmpty(Collection<T> collection) {
		return (collection == null) || (collection.isEmpty());
	}
}