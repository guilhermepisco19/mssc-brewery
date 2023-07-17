package dev.guilhermepisco.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.guilhermepisco.msscbrewery.web.controller.v2.BeerControllerV2;
import dev.guilhermepisco.msscbrewery.web.model.BeerDto;
import dev.guilhermepisco.msscbrewery.web.model.v2.BeerDtoV2;
import dev.guilhermepisco.msscbrewery.web.model.v2.BeerStyle;
import dev.guilhermepisco.msscbrewery.web.service.BeerService;
import dev.guilhermepisco.msscbrewery.web.service.v2.BeerServiceV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerControllerV2.class)
public class BeerControllerV2Test {

    public static final String API_V_1_BEER = "/api/v2/beer";
    @MockBean
    BeerServiceV2 beerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    BeerDtoV2 validBeer;

    @BeforeEach
    public void setUp() {
        validBeer = new BeerDtoV2(UUID.randomUUID(), "Beer1", BeerStyle.ALE, 123456789012L, OffsetDateTime.now(),OffsetDateTime.now());
    }

    @Test
    public void getBeer() throws Exception {
        given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);

        mockMvc.perform(get(API_V_1_BEER+ "/" + validBeer.id().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validBeer.id().toString())))
                .andExpect(jsonPath("$.beerName", is("Beer1")));
    }

    @Test
    //@Disabled
    public void handlePost() throws Exception {
        //given
        BeerDtoV2 beerDto = new BeerDtoV2(null, "Beer1", BeerStyle.ALE, 123456789012L, OffsetDateTime.now(Clock.system(ZoneId.of("UTC"))),OffsetDateTime.now(Clock.system(ZoneId.of("UTC"))));

        BeerDtoV2 savedDto = new BeerDtoV2(UUID.randomUUID(), "New Beer", BeerStyle.ALE, 123456789012L, OffsetDateTime.now(Clock.system(ZoneId.of("UTC"))),OffsetDateTime.now(Clock.system(ZoneId.of("UTC"))));

        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(savedDto);

        mockMvc.perform(post(API_V_1_BEER)
                        .content(beerDtoJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());

    }

    @Test
    public void handleUpdate() throws Exception {
        //given
        BeerDtoV2 beerDto = new BeerDtoV2(null, "Galaxy", BeerStyle.ALE, 123456789012L, OffsetDateTime.now(Clock.system(ZoneId.of("UTC"))),OffsetDateTime.now(Clock.system(ZoneId.of("UTC"))));
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        //when
        mockMvc.perform(put(API_V_1_BEER + "/" + validBeer.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());

        then(beerService).should().updateBeer(any(), any());

    }
}