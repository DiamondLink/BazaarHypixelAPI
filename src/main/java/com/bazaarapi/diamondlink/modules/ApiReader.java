package com.bazaarapi.diamondlink.modules;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.bazaarapi.diamondlink.utils.APIURLsUtils;
import com.bazaarapi.diamondlink.utils.JsonUtils;
import com.bazaarapi.diamondlink.utils.TimeUtils;

public class ApiReader {
	
	public static JSONObject getPlayerStats(String uuid, String apiKey) throws MalformedURLException, IOException, JSONException {
		return JsonReader.readJsonFromUrl(APIURLsUtils.getPlayerStatsURL(uuid, apiKey));
	}
	
	public static List<String> getSkyblockProfile(JSONObject playerStats) throws JSONException {
		JSONObject profiles = playerStats.getJSONObject("player").getJSONObject("stats").getJSONObject("SkyBlock").getJSONObject("profiles");
		Map<String, Object> profilesMap = JsonUtils.jsonToMap(profiles);
		
		List<String> profilesList = new ArrayList<String>(profilesMap.keySet());
		return profilesList;
	}
	
	public static JSONObject getSkyblockProfileStatsDatas(String skyblockProfileId, String apiKey) throws IOException, JSONException {
		return JsonReader.readJsonFromUrl(APIURLsUtils.getSkyblockStats(skyblockProfileId, apiKey));
	}
	
	public static JSONObject getPlayerProfileStatsData(JSONObject profileStatsData, String uuid) throws JSONException {
		return profileStatsData.getJSONObject("profile").getJSONObject("members").getJSONObject(uuid);
	}
	
	public static JSONObject getActiveSkyblockProfilePlayerData (String uuid, String apiKey) throws JSONException, IOException {
		List<String> skyblockProfiles = getSkyblockProfile(getPlayerStats(uuid, apiKey));
		
		HashMap<Long, JSONObject> lastSaveProfilesHashMap = new HashMap<Long, JSONObject>();
		
		Long mostRecentSave = null;
		
		for (String profile : skyblockProfiles) {
			JSONObject skyblockProfileDatas = getSkyblockProfileStatsDatas(profile, apiKey);
			JSONObject playerDatas = getPlayerProfileStatsData(skyblockProfileDatas, uuid);
			Long lastSave = TimeUtils.getTimeFromUnixTimestamp(playerDatas.getLong("last_save"));
			lastSaveProfilesHashMap.put(lastSave, playerDatas);
			if (mostRecentSave == null || mostRecentSave < lastSave) {
				mostRecentSave = lastSave;
			}
		}
		
		return lastSaveProfilesHashMap.get(mostRecentSave);
	}

}
