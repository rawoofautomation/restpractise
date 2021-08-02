import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConversion {

	public static void main(String[] args) throws JsonProcessingException {

		HashMap<String,Object> s = new HashMap<String,Object>();
		s.put("name","rawoof");
		s.put("address","hyderabad");
		s.put("language", "urdu");
		HashMap<String,Object> loc = new HashMap<String,Object>();
		loc.put("lat",34.656);
		loc.put("lan",-23.545);
		s.put("location", loc);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(s);
		System.out.println(json);
		
		
		Map<String,String> s2 = objectMapper.readValue(json,Map.class);
		System.out.println(s2);
		
		
	}

}
