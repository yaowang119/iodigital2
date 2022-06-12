package yw.assignment.iodigital.knowledgesharingplatform;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.isNotNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import yw.assignment.iodigital.ioplatform.TedTalkApplication;
import yw.assignment.iodigital.ioplatform.model.TedTalk;
import yw.assignment.iodigital.ioplatform.service.TedTalkService;


@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@ContextConfiguration(classes = TedTalkApplication.class)
class TedTalkServiceTests {
	
	@Autowired
	private TedTalkService service;	

	@Test
    @Rollback(false)
    @Order(1)
    public void testCreateTedTalk() {
		TedTalk tt = new TedTalk();
		tt.setAuthor("Yao Wang");
		tt.setTitle("YW Test TedTalk");
		tt.setDate("April 2022");
		tt.setViews(new BigInteger("88888"));
		tt.setLikes(new BigInteger("6666"));
		tt.setLink("https://ted.com/talks/test_tedtalk");
	
        TedTalk newTT = service.addTedTalk(tt);        
        assertThat(newTT.getId()).isGreaterThan(0);
    }
     
    @Test
    @Order(2)
    public void testGetTedTalkByAuthor() {
        List<TedTalk> ttList = service.findTedTalkByAuthor("yao");
        assertThat(ttList.size()).isGreaterThan(0);           
    }
    
    @Test
    @Order(3)
    public void testGetTedTalkByAuthors() {
    	List<String> authors = new ArrayList<String>();
    	authors.add("Yao Wang");
    	authors.add("James K. Thornton");
        List<TedTalk> ttList = service.findTedTalkByAuthors(authors);
        assertThat(ttList.size()).isGreaterThan(1);        
    }
    
    @Test
    @Order(4)
    public void testGetTedTalkByTitle() {
        List<TedTalk> ttList = service.findTedTalkByTitle("Test");
        assertThat(ttList.size()).isGreaterThan(0);        
    }
    
    @Test
    @Order(5)
    public void testGetTedTalkByTitles() {
    	List<String> titles = new ArrayList<String>();
    	titles.add("YW Test TedTalk");
    	titles.add("Why a free and fair internet is more vital than ever");
        List<TedTalk> ttList = service.findTedTalkByTitles(titles);
        assertThat(ttList.size()).isGreaterThan(0);        
    }
    
    @Test
    @Order(6)
    public void testGetTedTalkByViews() {
        List<TedTalk> ttList = service.findTedTalkByViews(new BigInteger("88888"));
        assertThat(ttList.size()).isGreaterThan(0);        
    }
    
    @Test
    @Order(7)
    public void testGetTedTalkByViewsGreaterThan() {
        List<TedTalk> ttList = service.findTedTalkByViewsGreaterThan(new BigInteger("20000"));
        assertThat(ttList.size()).isGreaterThan(0);        
    }
    
    @Test
    @Order(8)
    public void testGetTedTalkByViewsLessThan() {
        List<TedTalk> ttList = service.findTedTalkByViewsLessThan(new BigInteger("20000"));
        assertThat(ttList.size()).isGreaterThan(0);        
    }
    
    @Test
    @Order(9)
    public void testGetTedTalkByViewsBetween() {
        List<TedTalk> ttList = service.findTedTalkByViewsBetween(new BigInteger("20000"), new BigInteger("88888"));
        assertThat(ttList.size()).isGreaterThan(0);        
    }
    
    @Test
    @Order(10)
    public void testGetTedTalkByLikes() {
        List<TedTalk> ttList = service.findTedTalkByLikes(new BigInteger("6666"));
        assertThat(ttList.size()).isGreaterThan(0);        
    }
    
    @Test
    @Order(11)
    public void testGetTedTalkByLikesGreaterThan() {
        List<TedTalk> ttList = service.findTedTalkByLikesGreaterThan(new BigInteger("2000"));
        assertThat(ttList.size()).isGreaterThan(0);        
    }
    
    @Test
    @Order(12)
    public void testGetTedTalkByLikesLessThan() {
        List<TedTalk> ttList = service.findTedTalkByLikesLessThan(new BigInteger("20000"));
        assertThat(ttList.size()).isGreaterThan(0);        
    }
    
    @Test
    @Order(13)
    public void testGetTedTalkByLikesBetween() {
        List<TedTalk> ttList = service.findTedTalkByLikesBetween(new BigInteger("2000"), new BigInteger("20000"));
        assertThat(ttList.size()).isGreaterThan(0);        
    }
    
     
    @Test
    @Rollback(false)
    @Order(14)
    public void testUpdateTedTalk() {
    	List<TedTalk> talks = service.findTedTalkByAuthor("Yao Wang");
    	TedTalk tt = talks.get(0);    	
		tt.setTitle("YW Test TedTalk2");
		tt.setDate("October 2022");
		tt.setViews(new BigInteger("888880"));
        
		TedTalk newTT=service.updateTedTalk(tt.getId(), tt);
		assertEquals(newTT.getDate(), "October 2022");
		assertEquals(newTT.getTitle(), "YW Test TedTalk2");
		assertEquals(newTT.getViews(), new BigInteger("888880"));
    }
     
    @Test
    @Rollback(false)
    @Order(15)
    public void testDeleteTedTalk() {
    	List<TedTalk> talks = service.findTedTalkByAuthor("Yao Wang");
    	TedTalk tt = talks.get(0);
        service.deleteTedTalkById(tt.getId());
        talks = service.findTedTalkByAuthor("Yao Wang");
        assertEquals(talks.size(), 0);
    }
        

    
}
