package yw.assignment.iodigital.ioplatform.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import yw.assignment.iodigital.ioplatform.model.TedTalk;
import yw.assignment.iodigital.ioplatform.repositories.TedTalkRepository;

@Service
@CacheConfig(cacheNames={"tedtalks"})
public class TedTalkService {

	@Autowired
	TedTalkRepository repo;
	
	private static Logger log = LoggerFactory.getLogger(TedTalkService.class); 
	
	public TedTalk addTedTalk(TedTalk tt) {
		TedTalk newTT=repo.save(tt);
		log.info("TedTalk (id="+newTT.getId()+") has been added to the database");
		return newTT;
	}
	
	@CacheEvict(key="#id")
	public TedTalk updateTedTalk(Integer id, TedTalk newTT) {
		Optional<TedTalk> op=repo.findById(id);
		if (op.isPresent()) {
			TedTalk tt=op.get();
			tt.setAuthor(newTT.getAuthor());
			tt.setTitle(newTT.getTitle());
			tt.setDate(newTT.getDate());
			tt.setViews(newTT.getViews());
			tt.setLikes(newTT.getLikes());
			tt.setLink(newTT.getLink());
			log.info("TedTalk (id="+id+") has been updated in the database and the cache");
			return repo.save(tt);
		}else {
		    log.info("TedTalk (id="+id+") doesn't exist");
		    return null;
		}
	}
	
	@CacheEvict(key="#id")
	public void deleteTedTalkById(Integer id) {
		 TedTalk tt = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	     repo.delete(tt);
		
		log.info("TedTalk (id="+id+") has been deleted from the database and the cache");
	}
	
	@Cacheable
	public TedTalk findTedTalkById(Integer id) {		
		log.info("find TedTalk by id "+id);
		return repo.findById(id).orElse(null);
		
	}
	
	@Cacheable
	public List<TedTalk> findTedTalkByAuthor(String author){
		 log.info("find tedtalk by author "+author);
		return repo.findByAuthorContainingIgnoreCase(author);
	}
	
	@Cacheable
	public List<TedTalk> findTedTalkByAuthors(List<String> authors){
		log.info("find tedtalk by authors "+authors);
		return repo.findByAuthorIn(authors);
	}
	
	@Cacheable
	public List<TedTalk> findTedTalkByTitle(String title){
		log.info("find tedtalk by title "+title);
		return repo.findByTitleContainingIgnoreCase(title);
	}
	
	@Cacheable
	public List<TedTalk> findTedTalkByTitles(List<String> titles){
		log.info("find tedtalk by titles "+titles);
		return repo.findByTitleIn(titles);
	}
	
	@Cacheable
	public List<TedTalk> findTedTalkByViews(BigInteger numViews){
		log.info("find tedtalk by number of Views "+numViews);
		return repo.findByViews(numViews);
	}
	
	@Cacheable (key="'greater than'+ #views")
	public List<TedTalk> findTedTalkByViewsGreaterThan(BigInteger numViews){
		log.info("find tedtalk by number of Views greater than "+numViews);
		return repo.findByViewsGreaterThanOrderByViewsDesc(numViews);
	}
	
	@Cacheable (key="'less than'+ #views")
	public List<TedTalk> findTedTalkByViewsLessThan(BigInteger numViews){
		log.info("find tedtalk by number of Views less than "+numViews);
		return repo.findByViewsLessThanOrderByViewsDesc(numViews);
	}
	
	@Cacheable (key="'between'+ #viewsMin +'and'+#viewsMax")
	public List<TedTalk> findTedTalkByViewsBetween(BigInteger minViews, BigInteger maxViews){
		log.info("find tedtalk by number of Views between "+minViews+" and "+maxViews);
		return repo.findByViewsBetweenOrderByViewsDesc(minViews, maxViews);
	}
	
	@Cacheable
	public List<TedTalk> findTedTalkByLikes(BigInteger numLikes){
		log.info("find tedtalk by number of likes "+numLikes);
		return repo.findByLikes(numLikes);
	}
	
	@Cacheable (key="'greater than'+ #likes")
	public List<TedTalk> findTedTalkByLikesGreaterThan(BigInteger numLikes){
		log.info("find tedtalk by number of likes greater than "+numLikes);
		return repo.findByLikesGreaterThanOrderByLikesDesc(numLikes);
	}
	
	@Cacheable (key="'less than'+ #likes")
	public List<TedTalk> findTedTalkByLikesLessThan(BigInteger numLikes){
		log.info("find tedtalk by number of likes less than "+numLikes);
		return repo.findByLikesLessThanOrderByLikesDesc(numLikes);
	}
	
	@Cacheable (key="'between'+ #likesMin +'and'+#likesMax")
	public List<TedTalk> findTedTalkByLikesBetween(BigInteger minLikes, BigInteger maxLikes){
		log.info("find tedtalk by number of likes between "+minLikes+" and "+maxLikes);
		return repo.findByLikesBetweenOrderByLikesDesc(minLikes, maxLikes);
	}
	
	
	
}
