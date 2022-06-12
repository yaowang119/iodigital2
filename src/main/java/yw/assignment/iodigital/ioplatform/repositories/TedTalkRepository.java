package yw.assignment.iodigital.ioplatform.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yw.assignment.iodigital.ioplatform.model.TedTalk;

@Repository
public interface TedTalkRepository extends JpaRepository<TedTalk, Integer> {

	List<TedTalk> findByAuthorContainingIgnoreCase(String author);

	List<TedTalk> findByAuthorIn(List<String> authors);

	List<TedTalk> findByTitleContainingIgnoreCase(String title);

	List<TedTalk> findByTitleIn(List<String> titles);

	List<TedTalk> findByViews(BigInteger views);

	List<TedTalk> findByViewsGreaterThanOrderByViewsDesc(BigInteger views);

	List<TedTalk> findByViewsLessThanOrderByViewsDesc(BigInteger views);

	List<TedTalk> findByViewsBetweenOrderByViewsDesc(BigInteger viewsMin, BigInteger viewsMax);

	List<TedTalk> findByLikes(BigInteger likes);

	List<TedTalk> findByLikesGreaterThanOrderByLikesDesc(BigInteger likes);

	List<TedTalk> findByLikesLessThanOrderByLikesDesc(BigInteger likes);

	List<TedTalk> findByLikesBetweenOrderByLikesDesc(BigInteger likesMin, BigInteger likesMax);

}
