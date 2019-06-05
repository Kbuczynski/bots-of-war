package ncdc.bow.util;

import org.springframework.web.client.RestTemplate;

import ncdc.bow.model.GameSettings;

public class ApiUtil {
	
	public static final String URL = "http://192.168.99.100:8080";
	
	//http://192.168.99.100:8080 lokalny
	
	//http://192.168.253.121:8080 ncdc
	
	private static <T> T getForObject(String endpoint, Class<T> responseClass) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(endpoint, responseClass);
	}

	public static Integer[][] getMap() {
		return getForObject(URL + "/getMap", Integer[][].class);
	}

	public static GameSettings getGameSettings() {
		return getForObject(URL + "/getGameSettings", GameSettings.class);
	}

}
