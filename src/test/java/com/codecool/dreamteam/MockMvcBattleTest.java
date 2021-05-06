package com.codecool.dreamteam;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.codecool.dreamteam.controller.BattleController;
import com.codecool.dreamteam.service.BattleService;
import com.codecool.dreamteam.service.CardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



@RunWith(SpringJUnit4ClassRunner.class)
public class MockMvcBattleTest {

    private MockMvc mockMvc;

    @Mock
    private BattleService battleService;

    @InjectMocks
    private BattleController battleController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(battleController)
                .build();
    }

    @Test
    public void testBattleReturnJSON() throws Exception{
        mockMvc.perform(get("/battle/3/6")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());    }
}
