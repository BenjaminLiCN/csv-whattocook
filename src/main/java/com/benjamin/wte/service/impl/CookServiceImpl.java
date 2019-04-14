package com.benjamin.wte.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.benjamin.wte.objects.Ingredient;
import com.benjamin.wte.objects.Item;
import com.benjamin.wte.objects.Recipe;
import com.benjamin.wte.service.CookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    private String generateRecipe(List<Item> itemList,List<Recipe> recipeList){
        List<Recipe> qualifiedRecipes = new ArrayList<Recipe>();
        for(Recipe recipe : recipeList){
            List<Ingredient> ingredients = recipe.getIngredients();
            int flag = 0;
            for(Ingredient ingredient : ingredients){
                if(qualifiedIngredient(ingredient,itemList)){
                    flag += 1;
                }
            }
            if(flag == ingredients.size()){
                qualifiedRecipes.add(recipe);
            }

        }
        if (qualifiedRecipes.size()==0)
            return null;
        else{
            int max = qualifiedRecipes.size();
            int index = (int)(0+Math.random()*max);

            return JSON.toJSONString(qualifiedRecipes.get(index));
        }



    }

    private boolean qualifiedIngredient(Ingredient ingredient, List<Item> itemList) {
        for(Item item : itemList){
            if (item.getItem().equals(ingredient.getItem()) && item.getAmount()>ingredient.getAmount()){
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject dataProcess(JSONObject data) {

        //String s = JSON.toJSONString(data);
        List<Item> itemList = null;
        List<Recipe> recipeList = null;
        Map mapTypes = data;

        for (Object obj : mapTypes.keySet()){
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
        }
        String result = generateRecipe(itemList, recipeList);
        if(result!=null)
            return JSON.parseObject(result);
        else
            return null;
    }
}
