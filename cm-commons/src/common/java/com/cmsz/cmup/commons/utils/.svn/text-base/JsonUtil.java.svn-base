package com.cmsz.cmup.commons.utils;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * json 简单操作的工具类
 * @author wzq
 *
 */
public class JsonUtil{
	/**
     * 将OBJ用JSONObject的String来描述
     * @param obj
     * @return
     */
    public static String parseObjectToJSON(Object obj){
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject.toString().replaceAll("\t|\r|\n", "");
    }

	/**
     * 将OBJ用JSONObject来描述
     * @param obj
     * @return
     */
    public static JSONObject parseObjectToJSONObject(Object obj){
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject;
    }
    /**
     * 将数组转为JSONArray格式
     * @param objs
     * @return
     */
    public static JSONArray parseArrayToJsonText(Object[] objs){
        JSONArray jsonArray = JSONArray.fromObject(objs);   
        return jsonArray;
    }
    /**
     * 将JSON格式的字符串转为JSON对象  格式:("['JSON','is','easy']")
     * @param text
     * @return
     */
    public static JSONArray  parseJsonTextToArray(String text){
        JSONArray jsonArray = JSONArray.fromObject(text);   
        return jsonArray;
    }
    /**
     * 将List集合转为JSONArray格式
     * @param list
     * @return
     */
    public static JSONArray parseListToJsonText(List list){
        JSONArray jsonArray = JSONArray.fromObject(list);   
        return jsonArray;
    }
    
    /**
     * 将Map集合转为JSON格式数据
     * @param map
     * @return
     */
    public static JSONObject parseMapToJsonText(Map map){
        JSONObject json = JSONObject.fromObject(map);   
        return json;        
    }
    
       //将JSON格式的字符串转为JAVABEAN
    public static Object format(String json,Class c){
        
        JSONObject jb = JSONObject.fromObject(json);   
        return JSONObject.toBean(jb, c); 
    }
}