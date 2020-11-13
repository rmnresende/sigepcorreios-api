package br.com.dynamicdev.sigeprestapi;

import java.util.Collection;

public class CollectionsUtils {
	
	private CollectionsUtils() {}

	public static boolean isCollectionEmptyOrNull(Collection<?> list) {

		return list == null || list.isEmpty();
	}
	
	public static boolean isCollectionNotNullAndNotEmpty(Collection<?> list) {

		return list != null && !list.isEmpty();
	}

}
