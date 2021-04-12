package com.codecool.dreamteam;

import com.codecool.dreamteam.controller.BattleController;
import com.codecool.dreamteam.controller.UserController;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class MockMvcUserTest {

    private MockMvc mockMvc;

    @Mock
    private CardService Service;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    public void testLedgerReturnJSON() throws Exception{
        mockMvc.perform(get("/ledger")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
