package com.bazaarapi.diamondlink.utils;

import java.util.Date;

public class TimeUtils {
	
	public static Long getTimeFromUnixTimestamp(Long timestampMs) {
		return new Date(timestampMs).getTime();
	}
	
	public static Long getCurrentTime() {
		return new Date().getTime();
	}
	

}
