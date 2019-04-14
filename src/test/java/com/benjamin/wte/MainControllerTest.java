package com.benjamin.wte;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.anyOf;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Auther Benjamin(Jingyi Li) Li
 * @Email jili@student.unimelb.edu.au
 * @ID 961543
 * @Date 2019-04-14 16:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {
    @Autowired
    private MockMvc mvc;


    @Test
    public void setData() throws Exception{
        String json = "{" +
                "    \"itms\": [" +
                "        {" +
                "            \"Item\":\"bread\"," +
                "            \"Amount\":\"10\"," +
                "            \"Unit\":\"slices\"," +
                "            \"Date\":\"25/12/2014\"" +
                "        }," +
                "        {" +
                "            \"Item\":\"cheese\"," +
                "            \"Amount\":\"10\"," +
                "            \"Unit\":\"slices\"," +
                "            \"Date\":\"25/12/2014\"" +
                "        }," +
                "        {" +
                "            \"Item\":\"butter\"," +
                "            \"Amount\":\"250\"," +
                "            \"Unit\":\"grams\"," +
                "            \"Date\":\"25/12/2014\"" +
                "        }," +
                "        {" +
                "            \"Item\":\"peanut butter\"," +
                "            \"Amount\":\"250\"," +
                "            \"Unit\":\"grams\"," +
                "            \"Date\":\"2/12/2014\"" +
                "        }," +
                "        {" +
                "            \"Item\":\"mixed salad\"," +
                "            \"Amount\":\"150\"," +
                "            \"Unit\":\"grams\"," +
                "            \"Date\":\"26/12/2013\"" +
                "        }" +
                "    ]," +
                "    \"rcps\":[" +
                "        {" +
                "            \"name\":\"grilled cheese on toast\"," +
                "            \"ingredients\":[" +
                "                {" +
                "                    \"item\":\"bread\"," +
                "                    \"amount\":\"2\"," +
                "                    \"unit\":\"slices\"" +
                "                }," +
                "                {" +
                "                    \"item\":\"cheese\"," +
                "                    \"amount\":\"2\"," +
                "                    \"unit\":\"slices\"" +
                "                }" +
                "            ]" +
                "        }," +
                "        {" +
                "            \"name\":\"salad sandwich\"," +
                "            \"ingredients\":[" +
                "                {" +
                "                    \"item\":\"bread\"," +
                "                    \"amount\":\"2\"," +
                "                    \"unit\":\"slices\"" +
                "                }," +
                "                {" +
                "                    \"item\":\"mixed salad\"," +
                "                    \"amount\":\"100\"," +
                "                    \"unit\":\"grams\"" +
                "                }" +
                "            ]" +
                "        }" +
                "    ]" +
                "}";
        String expectedResult1 = "{" +
                "\"name\":\"grilled cheese on toast\"," +
                "\"ingredients\":[" +
                "{" +
                "\"amount\":2," +
                "\"item\":\"bread\"," +
                "\"unit\":\"slices\"" +
                "}," +
                "{" +
                "\"amount\":2," +
                "\"item\":\"cheese\"," +
                "\"unit\":\"slices\"" +
                "}" +
                "]" +
                "}";
        String expectedResult2 ="{" +
                "\"name\":\"salad sandwich\"," +
                "\"ingredients\":[" +
                "{" +
                "\"amount\":2," +
                "\"item\":\"bread\"," +
                "\"unit\":\"slices\"" +
                "}," +
                "{" +
                "\"amount\":100," +
                "\"item\":\"mixed salad\"," +
                "\"unit\":\"grams\"" +
                "}" +
                "]" +
                "}";
        List<String> resultList = new ArrayList<String>();
        resultList.add(expectedResult1);
        resultList.add(expectedResult2);
        RequestBuilder request;
        request = post("/setData")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);
        String result = mvc.perform(request)
                .andReturn().getResponse().getContentAsString();
        //Assert.assertThat(result,anyOf());


        System.out.println(result);

//        Assert.assertTrue("Correct", status == 200);
//        Assert.assertFalse("Error", status != 200);
       // Assert.assertEquals(HttpStatus.ACCEPTED.value(),mvcResult.getResponse().getStatus());




    }
}
