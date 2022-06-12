package yw.assignment.iodigital.ioplatform.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table
@Entity
public class TedTalk {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String title;
	
	@Column
	private String author;
	
	@Column
	private String date;
	
	@Column
	private BigInteger views;
	
	@Column
	private BigInteger likes;
	
	@Column
	private String link;

	public TedTalk() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigInteger getViews() {
		return views;
	}

	public void setViews(BigInteger views) {
		this.views = views;
	}

	public BigInteger getLikes() {
		return likes;
	}

	public void setLikes(BigInteger likes) {
		this.likes = likes;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	 @Override
    public String toString() {
        return "Tedtalk{" + "id=" + id + ", title=" + title + ", author=" + author
        		+ ", date=" + date+", views=" + views+", likes=" + likes+", link=" + link+"}";
    }

}
