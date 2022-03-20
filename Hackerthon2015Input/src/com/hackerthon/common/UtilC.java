package com.hackerthon.common;

import java.util.Properties;


public class UtilC {

	public static final Properties properties = new Properties();

	static {
		try {
			properties.load(UtilQ.class.getResourceAsStream("../config/config.properties"));
		} catch (Exception e) {
			
		}
	}
}
