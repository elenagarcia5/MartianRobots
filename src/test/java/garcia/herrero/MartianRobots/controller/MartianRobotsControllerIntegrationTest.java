package garcia.herrero.MartianRobots.controller;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import garcia.herrero.MartianRobots.service.MartinRobotsService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MartianRobotsControllerIntegrationTest {

	@Autowired
    private MockMvc mvc;

    @MockBean
    private MartinRobotsService martinRobotsService;
    
    @Test
    public void when_no_argument_is_passed_a_bad_request_error_is_thrown() throws Exception {

        given(martinRobotsService.playMartinRobot(ArgumentMatchers.any())).willReturn(new ArrayList<>());

        RequestBuilder request = post("/play?lines=");

        final ResultActions result = mvc.perform(request) ;
        	
        result.andExpect(status().isBadRequest())
        	.andExpect(jsonPath("$").isNotEmpty())
        	.andExpect(content().string("{\"lines\":\"size must be between 1 and 2147483647\"}"));

        verify(martinRobotsService,Mockito.times(0)).playMartinRobot(argThat(in -> in.getLines() == null));
    }
    

    @Test
    public void when_providing_a_correct_input_output_is_as_expected() throws Exception {

        given(martinRobotsService.playMartinRobot(ArgumentMatchers.any())).willReturn(Arrays.asList("1 1 E","3 3 N LOST","4 2 N"));

        RequestBuilder request = post("/play?lines=5%203,1%201%20E,RFRFRFRF,3%202%20N,FRRFLLFFRRFLL,0%203%20W,LLFFFRFLFL");

        mvc.perform(request)
        	.andExpect(status().isOk())
        	.andExpect(jsonPath("$").isNotEmpty())
        	.andExpect(content().string("[\"1 1 E\",\"3 3 N LOST\",\"4 2 N\"]"));

        verify(martinRobotsService,Mockito.times(1)).playMartinRobot(argThat(in -> in.getLines() != null));
    }


}
