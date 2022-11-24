package fr.polytech.ig5.CSALoffers.model.controller;

import fr.polytech.ig5.CSALoffers.model.Keyword;
import fr.polytech.ig5.CSALoffers.model.Offer;
import fr.polytech.ig5.CSALoffers.web.controller.OfferController;
import fr.polytech.ig5.CSALoffers.web.dao.OfferDao;
import fr.polytech.ig5.CSALoffers.web.dao.OfferDaoImpl;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(OfferController.class)
@AutoConfigureMockMvc(addFilters = false)
@ComponentScan(basePackages = {"fr.polytech.ig5"})
public class OfferControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OfferDaoImpl offerDao;

    @Test
    public void OfferController_offers() throws Exception{
        // Arrange
        List<Offer> offers = new ArrayList<>();
        Offer o1 = new Offer();
        o1.setTitle("this is offer 1");
        Offer o2 = new Offer();
        o2.setTitle("this is offer 2");
        offers.add(o1);
        offers.add(o2);

        when(offerDao.findAll()).thenReturn(offers);

        // Act
        ResultActions response = mockMvc.perform(get("/offers")
                .contentType(MediaType.APPLICATION_JSON));

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(offers.size())));

    }

    @Test
    public void OfferController_offer() throws Exception {
        Offer o1 = new Offer();
        o1.setOfferId(1);
        o1.setTitle("this an offer");

        when(offerDao.findById(1)).thenReturn(o1);

        ResultActions response = mockMvc.perform(get("/offer/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.offerId", CoreMatchers.is(o1.getOfferId())));
    }

    @Test
    public void OfferController_delete() throws Exception {
        doNothing().when(offerDao).delete(1);

        ResultActions response = mockMvc.perform(delete("/offer/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void OfferController_create() throws Exception {
        /*Offer o1 = new Offer();
        o1.setUserId(1);
        o1.setTitle("this is offer 1");

        when(offerDao.save(o1)).thenReturn(o1);
        doNothing().when(offerDao).bindKeywords(o1, Arrays.asList(new Keyword()));

        ResultActions response = mockMvc.perform(post("/offer")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isCreated());*/
    }


}
