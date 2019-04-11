package com.benjamin.wte.Service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.benjamin.wte.Item;
import com.benjamin.wte.Recipe;
import com.benjamin.wte.Service.CookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther Benjamin(Jingyi Li) Li
 * @Email jili@student.unimelb.edu.au
 * @ID 961543
 * @Date 2019-04-10 11:40
 */
@Service
public class CookServiceImpl implements CookService {

    @Override
    public JSONObject dataProcess(JSONObject data) {

        //String s = JSON.toJSONString(data);
        List<Item> itemList = null;
        List<Recipe> recipeList;
        Map mapTypes = data;
        System.out.println("这个是用JSON类的parseObject来解析JSON字符串!!!");
        for (Object obj : mapTypes.keySet()){
            //System.out.println("key为："+obj+"值为："+mapTypes.get(obj));
            Object o = mapTypes.get(obj);

            if(obj.toString()=="itms"){
                String itemString = JSON.toJSONString(o);
                itemList = JSON.parseArray(itemString, Item.class);
                //System.out.println(itemList);
            }
            if(obj.toString()=="rcps"){
                String recipeString = JSON.toJSONString(o);
                recipeList = JSON.parseArray(recipeString, Recipe.class);
                //System.out.println(recipeList);
            }
            //itemList = new List<Item>();

        }
        //for qualified recipes
        //(int)(1+Math.random()*10)
        System.out.println(itemList.get(0).getItem());

        //JSONArray itemList = JSON.parseArray(items);

        //List<Recipe> recipeList = JSON.parseArray(recipes, Recipe.class);
        //System.out.println(itemList);
        //System.out.println(recipeList);
        return null;
    }
}
