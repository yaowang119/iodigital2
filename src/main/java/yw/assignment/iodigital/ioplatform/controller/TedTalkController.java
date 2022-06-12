package yw.assignment.iodigital.ioplatform.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import yw.assignment.iodigital.ioplatform.model.TedTalk;
import yw.assignment.iodigital.ioplatform.service.TedTalkService;

@RestController
public class TedTalkController {
	
	@Autowired
	TedTalkService service;
	
	private static Logger log = LoggerFactory.getLogger(TedTalkController.class); 
	
	/**
	 * An equal query will be performed here. A TedTalk, whose id equals to the given id, will be returned. 
	 * @param id
	 * @return TedTalk
	 */
	@GetMapping("/tedtalks/id")
	public ResponseEntity<TedTalk> getTedTalkById(@RequestParam("id") Integer id) {
		log.info("get tedtalk by id "+id);
		TedTalk tt = service.findTedTalkById(id);
		if (tt!=null) {
			log.info("tedtalk found "+tt);
			return new ResponseEntity<>(tt, HttpStatus.OK);
		} else {
			log.error("No tedtalk found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	/**
	 * A like query will be performed here. All the TedTalks, whose author name contains string 'author', will be returned.
	 * @param author author name
	 * @return TedTalks
	 */
	@GetMapping("/tedtalks/author")
	public ResponseEntity<List<TedTalk>> getTedTalksByAuthor(@RequestParam("author") String author) {
		log.info("get tedtalk by author "+author);
		List<TedTalk> tedtalks = service.findTedTalkByAuthor(author);
		if (tedtalks!=null&&tedtalks.size()>0) {
			log.info(tedtalks.size()+" tedtalks found");
			return new ResponseEntity<>(tedtalks, HttpStatus.OK);
		} else {
			log.error("No tedtalk found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * A in query will be performed here. All the TedTalks, whose author name equals to one of the author names in the list, will be returned.
	 * @param authors author names
	 * @return TedTalks
	 */
	@GetMapping("/tedtalks/authors")
	public ResponseEntity<List<TedTalk>> getTedTalksByAuthors(@RequestParam("authors") List<String> authors) {
		log.info("get tedtalk by authors "+authors);
		List<TedTalk> tedtalks = service.findTedTalkByAuthors(authors);
		if (tedtalks!=null&&tedtalks.size()>0) {
			log.info(tedtalks.size()+" tedtalks found");
			return new ResponseEntity<>(tedtalks, HttpStatus.OK);
		} else {
			log.error("No tedtalk found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * A like query will be performed here. All the TedTalks, whose title contains string 'title', will be returned.
	 * @param title
	 * @return Ted Talks
	 */
	@GetMapping("/tedtalks/title")
	public ResponseEntity<List<TedTalk>> getTedTalksByTitle(@RequestParam("title") String title) {
		log.info("get tedtalk by title "+title);
		List<TedTalk> tedtalks = service.findTedTalkByTitle(title);
		if (tedtalks!=null&&tedtalks.size()>0) {
			log.info(tedtalks.size()+" tedtalks found");
			return new ResponseEntity<>(tedtalks, HttpStatus.OK);
		} else {
			log.error("No tedtalk found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * A in query will be performed here. All the TedTalks, whose title equals to one of the titles in the list, will be returned.
	 * @param titles
	 * @return TedTalks
	 */
	@GetMapping("/tedtalks/titles")
	public ResponseEntity<List<TedTalk>> getTedTalksByTitles(@RequestParam("titles") List<String> titles) {
		log.info("get tedtalk by titles "+titles);
		List<TedTalk> tedtalks = service.findTedTalkByTitles(titles);
		if (tedtalks!=null&&tedtalks.size()>0) {
			log.info(tedtalks.size()+" tedtalks found");
			return new ResponseEntity<>(tedtalks, HttpStatus.OK);
		} else {
			log.error("No tedtalk found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * An equal query will be performed here. All the TedTalks, whose number of views equals to the given number of views, will be returned.
	 * @param numViews number of views
	 * @return TedTalks
	 */
	@GetMapping("/tedtalks/views")
	public ResponseEntity<List<TedTalk>> getTedTalksByViews(@RequestParam("views") BigInteger numViews) {
		log.info("get tedtalk by number of Views "+numViews);
		List<TedTalk> tedtalks = service.findTedTalkByViews(numViews);
		if (tedtalks!=null&&tedtalks.size()>0) {
			log.info(tedtalks.size()+" tedtalks found");
			return new ResponseEntity<>(tedtalks, HttpStatus.OK);
		} else {
			log.error("No tedtalk found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * A greater than query will be performed here. All the TedTalks, whose number of views is greater than the given number of views, will be returned.
	 * @param numViews number of views
	 * @return TedTalks
	 */
	@GetMapping("/tedtalks/views-greater-than")
	public ResponseEntity<List<TedTalk>> getTedTalksByViewsGreaterThan(@RequestParam("views") BigInteger numViews) {
		log.info("get tedtalk by number of Views greater than "+numViews);
		List<TedTalk> tedtalks = service.findTedTalkByViewsGreaterThan(numViews);
		if (tedtalks!=null&&tedtalks.size()>0) {
			log.info(tedtalks.size()+" tedtalks found");
			return new ResponseEntity<>(tedtalks, HttpStatus.OK);
		} else {
			log.error("No tedtalk found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * A less than query will be performed here. All the TedTalks, whose number of views is less than the given number of views, will be returned.
	 * @param numViews number of views
	 * @return TedTalks
	 */
	@GetMapping("/tedtalks/views-less-than")
	public ResponseEntity<List<TedTalk>> getTedTalksByViewsLessThan(@RequestParam("views") BigInteger numViews) {
		log.info("get tedtalk by number of Views less than "+numViews);
		List<TedTalk> tedtalks = service.findTedTalkByViewsLessThan(numViews);
		if (tedtalks!=null&&tedtalks.size()>0) {
			log.info(tedtalks.size()+" tedtalks found");
			return new ResponseEntity<>(tedtalks, HttpStatus.OK);
		} else {
			log.error("No tedtalk found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * A between query will be performed here. All the TedTalks, whose number of views is between the given minimum number of views and the given
	 * maximum number of views, will be returned.
	 * @param minViews minimum number of views
	 * @param maxViews maximum number of views
	 * @return TedTalks
	 */
	@GetMapping("/tedtalks/views-between")
	public ResponseEntity<List<TedTalk>> getTedTalksByViewsBetween(@RequestParam("minViews") BigInteger minViews, @RequestParam("maxViews") BigInteger maxViews) {
		log.info("get tedtalk by number of Views between "+minViews+" and "+maxViews);
		List<TedTalk> tedtalks = service.findTedTalkByViewsBetween(minViews, maxViews);
		if (tedtalks!=null&&tedtalks.size()>0) {
			log.info(tedtalks.size()+" tedtalks found");
			return new ResponseEntity<>(tedtalks, HttpStatus.OK);
		} else {
			log.error("No tedtalk found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * An equal query will be performed here. All the TedTalks, whose number of likes equals to the given number of likes, will be returned.
	 * @param numViews number of likes
	 * @return TedTalks
	 */
	@GetMapping("/tedtalks/likes")
	public ResponseEntity<List<TedTalk>> getTedTalksByLikes(@RequestParam("likes") BigInteger numLikes) {
		log.info("get tedtalk by number of likes "+numLikes);
		List<TedTalk> tedtalks = service.findTedTalkByLikes(numLikes);
		if (tedtalks!=null&&tedtalks.size()>0) {
			log.info(tedtalks.size()+" tedtalks found");
			return new ResponseEntity<>(tedtalks, HttpStatus.OK);
		} else {
			log.error("No tedtalk found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * A greater than query will be performed here. All the TedTalks, whose number of likes is greater than the given number of likes, will be returned.
	 * @param numViews number of likes
	 * @return TedTalks
	 */
	@GetMapping("/tedtalks/likes-greater-than")
	public ResponseEntity<List<TedTalk>> getTedTalksByLikesGreaterThan(@RequestParam("likes") BigInteger numLikes) {
		log.info("get tedtalk by number of likes greater than "+numLikes);
		List<TedTalk> tedtalks = service.findTedTalkByLikesGreaterThan(numLikes);
		if (tedtalks!=null&&tedtalks.size()>0) {
			log.info(tedtalks.size()+" tedtalks found");
			return new ResponseEntity<>(tedtalks, HttpStatus.OK);
		} else {
			log.error("No tedtalk found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * A less than query will be performed here. All the TedTalks, whose number of likes is less than the given number of likes, will be returned.
	 * @param numViews number of likes
	 * @return TedTalks
	 */
	@GetMapping("/tedtalks/likes-less-than")
	public ResponseEntity<List<TedTalk>> getTedTalksByLikesLessThan(@RequestParam("likes") BigInteger numLikes) {
		log.info("get tedtalk by number of likes less than "+numLikes);
		List<TedTalk> tedtalks = service.findTedTalkByLikesLessThan(numLikes);
		if (tedtalks!=null&&tedtalks.size()>0) {
			log.info(tedtalks.size()+" tedtalks found");
			return new ResponseEntity<>(tedtalks, HttpStatus.OK);
		} else {
			log.error("No tedtalk found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * A between query will be performed here. All the TedTalks, whose number of likes is between the given minimum number of likes and
	 * the maximum number of likes, will be returned.
	 * @param minLikes minimum number of likes
	 * @param maxLikes maximum number of likes
	 * @return TedTalks
	 */
	@GetMapping("/tedtalks/likes-between")
	public ResponseEntity<List<TedTalk>> getTedTalksByLikesBetween(@RequestParam("minLikes") BigInteger minLikes,
			@RequestParam("maxLikes") BigInteger maxLikes) {
		log.info("get tedtalk by number of likes between "+minLikes+" and "+maxLikes);
		List<TedTalk> tedtalks = service.findTedTalkByLikesBetween(minLikes,maxLikes);
		if (tedtalks!=null&&tedtalks.size()>0) {
			log.info(tedtalks.size()+" tedtalks found");
			return new ResponseEntity<>(tedtalks, HttpStatus.OK);
		} else {
			log.error("No tedtalk found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * create new TedTalk
	 * @param tt new TedTalk without id
	 * @return new TedTalk with generated id
	 */
	@PostMapping("/tedtalk")
	public ResponseEntity<TedTalk> createTedTalk(@RequestBody TedTalk tt) {
		try {
			TedTalk newTT=service.addTedTalk(tt);
			log.info("New tedtalk created");
			return new ResponseEntity<>(newTT, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * update TedTalk by id
	 * @param id
	 * @param tt TedTalk to update
	 * @return updated TedTalk
	 */
	@PutMapping("/tedtalk/{id}")
	public ResponseEntity<TedTalk> updateTedTalk(@PathVariable("id") Integer id, @RequestBody TedTalk tt) {
	   TedTalk newTT=service.updateTedTalk(id,tt);
	   if (newTT!=null) {
		   log.info("Tedtalk with id "+id+" updated");
			return new ResponseEntity<>(newTT, HttpStatus.OK);
		} else {
			log.error("No tedtalk found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * delete TedTalk by id
	 * @param id
	 * @return http status if the deletion is successfully done.
	 */
	@DeleteMapping("/tedtalk/{id}")
	public ResponseEntity<HttpStatus> deleteTedTalk(@PathVariable("id") Integer id) {
		try {
			service.deleteTedTalkById(id);
			log.info("Tedtalk with id "+id+" deleted");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
