package com.codecool.dreamteam;

import com.codecool.dreamteam.controller.BattleController;
import com.codecool.dreamteam.controller.UserController;
import com.codecool.dreamteam.service.BattleService;
import com.codecool.dreamteam.service.CardService;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

}
