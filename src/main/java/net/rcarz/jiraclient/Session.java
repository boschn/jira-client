package net.rcarz.jiraclient;

import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class Session extends Resource {

	private String name;
	
	protected Session(RestClient restclient, JSONObject json) {
		super(restclient);
		if (json != null)
            deserialise(json);
	}
	
	private void deserialise(JSONObject json) {
        Map map = json;

        self = Field.getString(map.get("Session"));
        
        id = Field.getString(map.get("value"));
        name = Field.getString(map.get("name"));
     }

   }
