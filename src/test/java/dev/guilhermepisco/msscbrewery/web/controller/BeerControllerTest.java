package dev.guilhermepisco.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.guilhermepisco.msscbrewery.web.model.BeerDto;
import dev.guilhermepisco.msscbrewery.web.service.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    @MockBean
    BeerService beerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    BeerDto validBeer;

    @BeforeEach
    public void setUp() {
        validBeer = new BeerDto(UUID.randomUUID(), "Beer1", "PALE_ALE", 123456789012L);
    }

    @Test
    public void getBeer() throws Exception {
        given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);

        mockMvc.perform(get("/api/v1/beer/" + validBeer.id().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validBeer.id().toString())))
                .andExpect(jsonPath("$.beerName", is("Beer1")));
    }

    @Test
    //@Disabled
    public void handlePost() throws Exception {
        //given
        BeerDto beerDto = new BeerDto(null, "Beer1", "PALE_ALE", 123456789012L);

        BeerDto savedDto = new BeerDto(UUID.randomUUID(), "New Beer", "PALE_ALE", 123456789012L);

        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(savedDto);

        mockMvc.perform(post("/api/v1/beer")
                        .content(beerDtoJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

    }

    @Test
    public void handleUpdate() throws Exception {
        //given
        BeerDto beerDto = validBeer;
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        //when
        mockMvc.perform(put("/api/v1/beer/" + validBeer.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());

        then(beerService).should().updateBeer(any(), any());

    }
}