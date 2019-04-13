package com.benjamin.wte;

import com.alibaba.fastjson.JSONObject;
import com.benjamin.wte.service.CookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther Benjamin(Jingyi Li) Li
 * @Email jili@student.unimelb.edu.au
 * @ID 961543
 * @Date 2019-04-08 17:58
 */

@RestController
public class MainController {
    @Autowired
    private CookService cookService;

    @ResponseBody
    @RequestMapping(value = "/setData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public JSONObject handleData(@RequestBody JSONObject data) {
        JSONObject recipe = cookService.dataProcess(data);
        if (recipe!=null) {
            System.out.println(recipe);
            return recipe;
        }
        else
            System.out.println("No luck");
        return null;
    }

}
