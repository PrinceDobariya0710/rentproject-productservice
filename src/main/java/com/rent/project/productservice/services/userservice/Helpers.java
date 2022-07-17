package com.rent.project.productservice.services.userservice;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class Helpers {


    public static String parseObject(JSONObject json,String key){
        return json.get(key).toString();
    }

    //Function to search for a particular "key" in any json object and find it's value
    public static String getKey(JSONObject jsonObject, String key) {
        boolean exists = jsonObject.has(key);
        Iterator<?> keys;
        String nextKeys;
        String valueOfKey = "";

        if (!exists) {
            keys = jsonObject.keys();
            while (keys.hasNext()) {
                nextKeys = (String) keys.next();
                try {
                    if (jsonObject.get(nextKeys) instanceof JSONObject) {
                        if (!exists) {
                            valueOfKey = getKey(jsonObject.getJSONObject(nextKeys), key);
                        }
                    } else if (jsonObject.get(nextKeys) instanceof JSONArray) {
                        JSONArray jsonArray = jsonObject.getJSONArray(nextKeys);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            String jsonArrayString = jsonArray.get(i).toString();
                            JSONObject innerJson = new JSONObject(jsonArrayString);
                            if (!exists) {
                                valueOfKey = getKey(innerJson, key);
                            }
                        }
                    }
                } catch (Exception e) {
                    return e.toString();
                }
            }
        } else {
            return parseObject(jsonObject, key);
        }

        return valueOfKey;
    }


}
