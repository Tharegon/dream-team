package com.codecool.dreamteam;

import com.codecool.dreamteam.controller.CardController;
import com.codecool.dreamteam.service.CardService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
}
