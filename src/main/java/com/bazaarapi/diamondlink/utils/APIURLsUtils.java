package com.bazaarapi.diamondlink.utils;

public class APIURLsUtils {
	
	public static String getBazaarProductsAPIURL(String apiKey) {
		return "https://api.hypixel.net/skyblock/bazaar/products?key=" + apiKey;
	}
	
	public static String getBazaarDataAPIURl(String productID, String apiKey) {
		return "https://api.hypixel.net/skyblock/bazaar/product?key=" + apiKey + "&productId=" + productID;
	}
	
	public static String getPlayerStatsURL(String uuid, String apiKey) {
		return "https://api.hypixel.net/player?key=" + apiKey + "&uuid=" + uuid;
	}
	
	public static String getSkyblockStats(String profileID, String apiKey) {
		return "https://api.hypixel.net/skyblock/profile?key=" + apiKey + "&profile=" + profileID;
	}
}
