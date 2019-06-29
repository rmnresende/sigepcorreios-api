package br.com.dynamicdev.sigeprestapi;

import java.util.List;

public class ListUtils {

	public static boolean isListEmptyOrNull(List<?> list) {

		return list == null || list.isEmpty();
	}
	
	public static boolean isListNotNullAndNotEmpty(List<?> list) {

		return list != null && !list.isEmpty();
	}

}
