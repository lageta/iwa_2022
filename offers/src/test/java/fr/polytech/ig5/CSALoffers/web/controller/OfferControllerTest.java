package fr.polytech.ig5.CSALoffers.web.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class OfferControllerTest {


    public MockMvc mockMvc=MockMvcBuilders.standaloneSetup(OfferController.class).build();

    @InjectMocks
    private OfferController offerController;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void offers() throws Exception {
        // test without headers set
        mockMvc.perform(
                        get("/offers"))
                .andExpect(status().isBadRequest());

        //test with invalid role without connection
        mockMvc.perform(
                        get("/offers")
                                .header("role", "INVALID")

                )
                .andDo(print())
                .andExpect(status().isBadRequest());

        // test with admin without connection
        mockMvc.perform(
                        get("/offers")
                                .header("role", "ROLE_ADMIN")
                )
                .andExpect(status().isInternalServerError());

        // test with employer without connection
        mockMvc.perform(
                        get("/offers")
                                .header("role", "ROLE_EMPLOYER")
                )
                .andExpect(status().isInternalServerError());

        // test with seeker without connection
        mockMvc.perform(
                        get("/offers")
                                .header("role", "ROLE_SEEKER")
                )
                .andExpect(status().isInternalServerError());





    }


    @Test
    void offersFromUser() throws Exception {
        // test without headers set
        mockMvc.perform(
                        get("/offers/user/1"))
                .andExpect(status().isBadRequest());

        //test with invalid role without connection
        mockMvc.perform(
                        get("/offers/user/1")
                                .header("role", "INVALID")

                )
                .andDo(print())
                .andExpect(status().isBadRequest());

        // test with admin without connection
        mockMvc.perform(
                        get("/offers/user/1")
                                .header("role", "ROLE_ADMIN")
                )
                .andExpect(status().isNotFound());

        // test with employer without connection
        mockMvc.perform(
                        get("/offers/user/1")
                                .header("role", "ROLE_EMPLOYER")
                )
                .andExpect(status().isNotFound());
        // test with seeker without connection
        mockMvc.perform(
                        get("/offers/user/1")
                                .header("role", "ROLE_SEEKER")
                )
                .andExpect(status().isNotFound());




    }

    @Test
    void offer() throws Exception {
        // test without headers set
        mockMvc.perform(
                        get("/offer/1"))
                .andExpect(status().isBadRequest());

        //test with invalid role without connection
        mockMvc.perform(
                        get("/offer/1")
                                .header("role", "INVALID")


                )
                .andDo(print())
                .andExpect(status().isBadRequest());

        // test with admin without connection
        mockMvc.perform(
                        get("/offer/1")
                                .header("role", "ROLE_ADMIN")
                )
                .andExpect(status().isNotFound());

        // test with employer without connection
        mockMvc.perform(
                        get("/offer/1")
                                .header("role", "ROLE_EMPLOYER")
                )
                .andExpect(status().isNotFound());
        // test with seeker without connection
        mockMvc.perform(
                        get("/offers/1")
                                .header("role", "ROLE_SEEKER")
                )
                .andExpect(status().isNotFound());

    }

    @Test
    void keywords() throws Exception {
        // test without headers set
        mockMvc.perform(
                        get("/offer/1/keywords"))
                .andExpect(status().isBadRequest());

        //test with invalid role without connection
        mockMvc.perform(
                        get("/offer/1/keywords")
                                .header("role", "INVALID")


                )
                .andDo(print())
                .andExpect(status().isBadRequest());

        // test with admin without connection
        mockMvc.perform(
                        get("/offer/1/keywords")
                                .header("role", "ROLE_ADMIN")
                )
                .andExpect(status().isNotFound());

        // test with employer without connection
        mockMvc.perform(
                        get("/offer/1/keywords")
                                .header("role", "ROLE_EMPLOYER")
                )
                .andExpect(status().isNotFound());
        // test with seeker without connection
        mockMvc.perform(
                        get("/offer/1/keywords")
                                .header("role", "ROLE_SEEKER")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void advantages() throws Exception {
        // test without headers set
        mockMvc.perform(
                        get("/offer/1/advantages"))
                .andExpect(status().isBadRequest());

        //test with invalid role without connection
        mockMvc.perform(
                        get("/offer/1/advantages")
                                .header("role", "INVALID")


                )
                .andDo(print())
                .andExpect(status().isBadRequest());

        // test with admin without connection
        mockMvc.perform(
                        get("/offer/1/advantages")
                                .header("role", "ROLE_ADMIN")
                )
                .andExpect(status().isNotFound());

        // test with employer without connection
        mockMvc.perform(
                        get("/offer/1/advantages")
                                .header("role", "ROLE_EMPLOYER")
                )
                .andExpect(status().isNotFound());
        // test with seeker without connection
        mockMvc.perform(
                        get("/offer/1/advantages")
                                .header("role", "ROLE_SEEKER")
                )
                .andExpect(status().isNotFound());

    }

    @Test
    void testKeywords() throws Exception {
        // test without headers set
        mockMvc.perform(
                        get("/keywords"))
                .andExpect(status().isBadRequest());

        //test with invalid role without connection
        mockMvc.perform(
                        get("/keywords")
                                .header("role", "INVALID")


                )
                .andDo(print())
                .andExpect(status().isBadRequest());

        // test with admin without connection
        mockMvc.perform(
                        get("/keywords")
                                .header("role", "ROLE_ADMIN")
                )
                .andExpect(status().isInternalServerError());

        // test with employer without connection
        mockMvc.perform(
                        get("/keywords")
                                .header("role", "ROLE_EMPLOYER")
                )
                .andExpect(status().isInternalServerError());
        // test with seeker without connection
        mockMvc.perform(
                        get("/keywords")
                                .header("role", "ROLE_SEEKER")
                )
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testAdvantages() throws Exception {
        // test without headers set
        mockMvc.perform(
                        get("/advantages"))
                .andExpect(status().isBadRequest());

        //test with invalid role without connection
        mockMvc.perform(
                        get("/advantages")
                                .header("role", "INVALID")


                )
                .andDo(print())
                .andExpect(status().isBadRequest());

        // test with admin without connection
        mockMvc.perform(
                        get("/advantages")
                                .header("role", "ROLE_ADMIN")
                )
                .andExpect(status().isInternalServerError());

        // test with employer without connection
        mockMvc.perform(
                        get("/advantages")
                                .header("role", "ROLE_EMPLOYER")
                )
                .andExpect(status().isInternalServerError());
        // test with seeker without connection
        mockMvc.perform(
                        get("/advantages")
                                .header("role", "ROLE_SEEKER")
                )
                .andExpect(status().isInternalServerError());
    }

    @Test
    void create() throws Exception {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteKeyword() {
    }

    @Test
    void deleteAdvantage() {
    }

    @Test
    void testDeleteKeyword() {
    }

    @Test
    void testDeleteAdvantage() {
    }

    @Test
    void deleteOutdated() {
    }
}