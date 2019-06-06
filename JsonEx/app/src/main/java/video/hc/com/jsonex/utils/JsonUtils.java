package video.hc.com.jsonex.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import video.hc.com.jsonex.enity.Book;
import video.hc.com.jsonex.enity.Weather;

/**
 * Created by ly on 2019/6/4.
 * <p>
 * <p>
 * 用法 在gradle 添加 implementation 'com.google.code.gson:gson:2.8.5'
 * over
 */

public class JsonUtils {
    static JsonUtils jsonUtils;
    JsonParser parser;
    JsonObject jsons;

    public static JsonUtils getIncetence() {
        if (jsonUtils == null) {
            jsonUtils = new JsonUtils();
            return  jsonUtils;
        }
        return  jsonUtils;
    }

    public JsonObject beginJson(String json) {
        parser = new JsonParser();
        jsons = (JsonObject) parser.parse(json);
        return jsons;
    }

    public Book beginBookGson(String json, Class<Book> s){
        Gson gson = new Gson();
        return gson.fromJson(json,s);
    }

    public Weather beginWeatherGson(JsonObject json, Class<Weather> s){
        Gson gson = new Gson();
        return gson.fromJson(json,s);
    }
}
