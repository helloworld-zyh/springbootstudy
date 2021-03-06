package com.keer.mybatisdemo.controller;

import com.google.gson.Gson;
import com.keer.mybatisdemo.pojo.People;
import com.keer.mybatisdemo.pojo.WebResult;
import com.keer.mybatisdemo.service.PeopleService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


/**
 * @BelongsProject: spring-boot-study
 * @BelongsPackage: com.keer.mybatisdemo.controller
 * @Author: keer
 * @CreateTime: 2020-03-10 16:30
 * @Description:
 */
@RunWith(SpringRunner.class)
@DisplayName("人员API接口测试")
@WebMvcTest(PeopleController.class)
public class PeopleControllerTest {
    @SpringBootApplication(scanBasePackages = {"com.keer.mybatisdemo.controller"})
    static class InnerConfig {
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PeopleService peopleService;

    @Before
    public void setup() {
        WebResult webResult = new WebResult();
        webResult.setStatus(WebResult.SUCCESS);

        Mockito.when(peopleService.getAllPeopleInfo()).thenReturn(webResult);
        Mockito.when(peopleService.addPeopleInfo(Mockito.any())).thenReturn(webResult);
        Mockito.when(peopleService.getPeopleInfoByID(Mockito.anyInt())).thenReturn(webResult);
        Mockito.when(peopleService.updatePeopleNameByID(Mockito.anyString(), Mockito.anyInt())).thenReturn(webResult);
        Mockito.when(peopleService.deletePeopleInfoByID(Mockito.anyInt())).thenReturn(webResult);
    }

    @Test
    @DisplayName(value = "测试获取全部信息接口")
    public void testGetAllPeopleInfo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/getAllPeopleInfo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("0")));
    }

    @Test
    @DisplayName(value = "增加人员接口")
    public void testAddPeopleInfo() throws Exception {
        Gson gson = new Gson();
        People people = new People("java", 1, 12, "spring");
        String json = gson.toJson(people);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/addPeopleInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("0")));
    }

    @Test
    @DisplayName(value = "通过id查找人员信息")
    public void testGetPeopleInfoByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/getPeopleInfoByID/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("0")));
    }

    @Test
    @DisplayName(value = "根据id更新人员姓名")
    public void testUpdatePeopleNameByID() throws Exception {
        Gson gson = new Gson();
        People people = new People("java", 1, 12, "spring");
        String json = gson.toJson(people);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/updatePeopleNameByID")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("0")));

    }

    @Test
    @DisplayName(value = "根据ID删除用户信息")
    public void testDeletePeopleInfoByID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/deletePeopleInfoByID/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("0")));
    }


}
