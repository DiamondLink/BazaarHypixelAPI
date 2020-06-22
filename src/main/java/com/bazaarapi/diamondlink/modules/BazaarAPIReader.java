package com.bazaarapi.diamondlink.modules;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bazaarapi.diamondlink.utils.APIURLsUtils;
import com.bazaarapi.diamondlink.utils.JsonUtils;
public class BazaarAPIReader {
	
	public static JSONObject getBazaarDataProduct(String productID, String apiKey) throws MalformedURLException, IOException, JSONException {
		return JsonReader.readJsonFromUrl(APIURLsUtils.getBazaarDataAPIURl(productID, apiKey));
	}
	
	public List<String> getBazaarProductsList(String apiKey) throws JSONException, IOException {
		JSONObject productsResult = JsonReader.readJsonFromUrl(APIURLsUtils.getBazaarProductsAPIURL(apiKey));
		/*
		if (!productsResult.getBoolean("success")) {
			throw new ApiException(productsResult.getString("cause"));
		}
		*/
		List<String> productsList = new ArrayList<String>();
		
		JSONArray productsListJSON = productsResult.getJSONArray("productIds");

		List<Object> productsListObjects = JsonUtils.toList(productsListJSON);
		for (Object object : productsListObjects) {
			productsList.add(object.toString());
		}
	    
	    return productsList;
	}

	
	private static List<HashMap<String, Double>> getSummary(String summaryName, JSONObject dataProduct) throws JSONException {
		
		JSONArray summary = dataProduct.getJSONObject("product_info").getJSONArray(summaryName);
		
		List<HashMap<String, Double>> summaryList = new ArrayList<HashMap<String,Double>>();
		
		for (int i = 0; i < summary.length(); i++) {
		Iterator<String> nameItr = summary.getJSONObject(i).keys();
		HashMap<String, Double> outMap = new HashMap<String, Double>();
		while(nameItr.hasNext()) {
		    String name = nameItr.next();
		    outMap.put(name, summary.getJSONObject(i).getDouble(name));

		}
		summaryList.add(outMap);
		}
		
		return summaryList;
		
	}

	public List<HashMap<String, Double>> getBuySummary(JSONObject dataProduct) throws JSONException{
		return getSummary("buy_summary", dataProduct);
	}
	
	public List<HashMap<String, Double>> getSellSummary(JSONObject dataProduct) throws JSONException{
		return getSummary("sell_summary", dataProduct);
	}
	
	public Double getBuyPrice(JSONObject dataProduct) throws JSONException {
		List<HashMap<String, Double>> sellSummary = getSummary("sell_summary", dataProduct);
		return sellSummary.get(0).get("pricePerUnit");
	}
	
	public Double getSellPrice(JSONObject dataProduct) throws JSONException {
		List<HashMap<String, Double>> buySummary = getSummary("buy_summary", dataProduct);
		return buySummary.get(0).get("pricePerUnit");
	}
	
	public int getBuyVolume(JSONObject dataProduct) throws JSONException {
		return dataProduct.getJSONObject("product_info").getJSONObject("quick_status").getInt("buyVolume");
	}
	
	public int getSellVolume(JSONObject dataProduct) throws JSONException {
		return dataProduct.getJSONObject("product_info").getJSONObject("quick_status").getInt("sellVolume");
	}
	
	public int getBuyMovingWeek(JSONObject dataProduct) throws JSONException {
		return dataProduct.getJSONObject("product_info").getJSONObject("quick_status").getInt("buyMovingWeek");
	}
	
	public int getSellMovingWeek(JSONObject dataProduct) throws JSONException {
		return dataProduct.getJSONObject("product_info").getJSONObject("quick_status").getInt("sellMovingWeek");
	}
	
	public Double getCurrentCoinsOnThePlayerProfile(String uuid, String apiKey) throws JSONException, IOException {
		return ApiReader.getActiveSkyblockProfilePlayerData(uuid, apiKey).getDouble("coin_purse");
	}

}
