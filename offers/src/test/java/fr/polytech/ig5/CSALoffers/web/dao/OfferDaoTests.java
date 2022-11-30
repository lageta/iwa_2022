package fr.polytech.ig5.CSALoffers.web.dao;

import fr.polytech.ig5.CSALoffers.model.Advantage;
import fr.polytech.ig5.CSALoffers.model.Keyword;
import fr.polytech.ig5.CSALoffers.model.Offer;
import fr.polytech.ig5.CSALoffers.web.dao.OfferDaoImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class OfferDaoTests {

    @Autowired
    private OfferDaoImpl offerDao;

    //@Test
    /**
     * Tests the app integration with the postgres database by saving a new offer.
     *
     */
    /*
    public void OfferDao_save(){
        Offer offer = new Offer();
        offer.setUserId(1);
        Offer saveOffer = offerDao.save(offer);
        Assertions.assertThat(saveOffer).isNotNull();
        Assertions.assertThat(saveOffer.getOfferId()).isGreaterThan(0);
    }

    @Test
    public void OfferDao_findAll() {
        List<Offer> list = offerDao.findAll();
        Assertions.assertThat(list.size()).isEqualTo(3);
    }


    @Test
    public void OfferDao_findById(){
        List<Offer> list = offerDao.findAll();
        Offer o1 = offerDao.findById(list.get(0).getOfferId());
        Assertions.assertThat(o1.getOfferId()).isEqualTo(list.get(0).getOfferId());
    }

    @Test
    public void OfferDao_update(){
        List<Offer> list = offerDao.findAll();
        Offer o1 = offerDao.findById(list.get(0).getOfferId());
        o1.setTitle("UnTitre");
        offerDao.update(o1);

        Offer o2 = offerDao.findById(list.get(0).getOfferId());
        Assertions.assertThat(o2.getTitle()).isEqualTo("UnTitre");
    }

    @Test
    public void OfferDao_delete() {
        Offer o1 = offerDao.findAll().get(0);

        offerDao.delete(o1.getOfferId());

        Offer o2 = offerDao.findById(o1.getOfferId());
        Assertions.assertThat(o2).isNull();

    }

  @Test
  public void  OfferDao_findAllKeyword(){
        Assertions.assertThat(true).isEqualTo(true);
    }
  @Test
  public void OfferDao_findAllAdvantage(){Assertions.assertThat(true).isEqualTo(true);}
  @Test
  public void OfferDao_bindKeyword(){Assertions.assertThat(true).isEqualTo(true);}
  @Test
  public void OfferDao_bindAdvantage(){Assertions.assertThat(true).isEqualTo(true);}
  @Test
  public void OfferDao_outdatedOffers(){Assertions.assertThat(true).isEqualTo(true);}
  @Test
  public void OfferDao_bindKeywords(){Assertions.assertThat(true).isEqualTo(true);}
  */

}
