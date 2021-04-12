package com.codecool.dreamteam;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.codecool.dreamteam.controller.CardController;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class MockMvcTest {

    private MockMvc mockMvc;

    @Mock
    private CardService cardService;

    @InjectMocks
    private CardController cardController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(cardController)
                .build();
    }

    @Test
    public void testMyCardsReturnJSON() throws Exception{
        mockMvc.perform(get("/myCards/3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testMyCardsReturnData() throws Exception{
        mockMvc.perform(get("/myCards/3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", greaterThan(1)))
                .andExpect(status().isOk());
    }

    @Test
    public void testCardReturnJSON() throws Exception{
        mockMvc.perform(get("/card/2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMyTeamReturnJSON() throws Exception{
        mockMvc.perform(get("/get-my-team/3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
