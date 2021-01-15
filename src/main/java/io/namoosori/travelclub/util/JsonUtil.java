package io.namoosori.travelclub.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.namoosori.travelclub.step1.entity.club.ClubMembership;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonUtil {
    public static final Gson gson = new Gson();
    public static final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(Object object){
        //
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> type){
        //
        return gson.fromJson(json, type);
    }

    public static <T> List<T> fromJsonList(String json, Class<T> type){
        //
        List<T> objects = gson.fromJson(json, new TypeToken<List<T>>(){}.getType());
        List<T> results = objects.stream()
                .map(treeMap -> {
                    String treeMapJson = gson.toJson(treeMap);
                    return fromJson(treeMapJson, type);
                })
                .collect(Collectors.toList());
        return results;
    }

}
