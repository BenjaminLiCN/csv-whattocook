package com.benjamin.wte;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther Benjamin(Jingyi Li) Li
 * @Email jili@student.unimelb.edu.au
 * @ID 961543
 * @Date 2019-04-08 17:58
 */

@RestController
public class MainController {
    //@CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    @RequestMapping(value = "/setItems", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String handleItems(@RequestBody List<JSONObject> list) {
        System.out.println(list);
        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "/setRecipes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String handleRecipes(@RequestBody List<JSONObject> list) {
        System.out.println(list);
        return "OK2";
    }
}
