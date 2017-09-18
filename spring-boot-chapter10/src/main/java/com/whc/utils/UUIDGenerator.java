package com.whc.utils;

import java.util.UUID;

public class UUIDGenerator {
	
	/**
	 * 生成UUID公用业务方法
	 */
	public static String createKey() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 创建32位的UUID
	 */
	public static String create32Key() {
		return UUID.randomUUID().toString().replaceAll("\\-", "");
	}

}