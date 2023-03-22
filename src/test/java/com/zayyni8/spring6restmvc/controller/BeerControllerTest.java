package com.zayyni8.spring6restmvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zayyni8.spring6restmvc.model.Beer;
import com.zayyni8.spring6restmvc.services.BeerService;
import com.zayyni8.spring6restmvc.services.BeerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@WebMvcTest(BeerController.class)
class BeerControllerTest {

//    @Autowired
//    BeerController beerController;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    BeerServiceImpl beerServiceImpl;
    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;
    @Captor
    ArgumentCaptor<Beer> beerArgumentCaptor;


//creating json using jackson


    @BeforeEach
    void setUp() {
        beerServiceImpl = new BeerServiceImpl();
    }


    //Patch test using Beer Object

    @Test
    void testPatchBeer() throws Exception {
        Beer beer = beerServiceImpl.listBeers().get(0);

        Map<String, Object> beerMap=new HashMap<>();
        beerMap.put("beerName","New Name");

        mockMvc.perform(patch("/api/v1/beer/" +beer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerMap)))
                .andExpect(status().isNoContent());

        verify(beerService).patchBeerById(uuidArgumentCaptor.capture(),beerArgumentCaptor.capture());
        assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(beerMap.get("beerName")).isEqualTo(beerArgumentCaptor.getValue().getBeerName());
    }


    //Delete using Mock MVC test Beer


    @Test
    void testDeleteBeer() throws Exception {
        Beer beer = beerServiceImpl.listBeers().get(0);

        mockMvc.perform(delete("/api/v1/beer/" +beer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

            verify(beerService).deleteBeerById(uuidArgumentCaptor.capture());

            assertThat(beer.getId()).isEqualTo(uuidArgumentCaptor.getValue());

    }

    //Update Mock MVC test Beer
    @Test
    void testBeerUpdate() throws Exception {
        Beer beer=beerServiceImpl.listBeers().get(0);

        mockMvc.perform(put("/api/v1/beer/" +beer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beer)))
                        .andExpect(status().isNoContent());

        verify(beerService).updateBeerById(any(UUID.class),any(Beer.class));

    }

    @Test
    void testCreateNewBeer() throws Exception {
        Beer beer = beerServiceImpl.listBeers().get(0);

        beer.setVersion(null);
        beer.setId(null);

        given(beerService.saveNewBeer(any(Beer.class))).willReturn(beerServiceImpl.listBeers().get(1));

        mockMvc.perform(post("/api/v1/beer")
                .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

    }

    @Test
    void testListBeers() throws Exception {
        given(beerService.listBeers()).willReturn(beerServiceImpl.listBeers());

        mockMvc.perform(get("/api/v1/beer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //value is equal to elements in the list
                .andExpect(jsonPath("$.length()",is(3)));
    }

    @Test
    void getBeerById() throws Exception {
        Beer testBeer = beerServiceImpl.listBeers().get(0);

        given(beerService.getBeerById(testBeer.getId())).willReturn(testBeer);

        mockMvc.perform(get("/api/v1/beer/"+testBeer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testBeer.getId().toString())))
                .andExpect(jsonPath("$.beerName",is(testBeer.getBeerName())));

//        System.out.println(beerController.getBeerById(UUID.randomUUID()));
    }
}