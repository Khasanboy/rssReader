package com.tsar.datahunter.service;

import java.util.List;

import com.tsar.datahunter.model.Feed;

public interface FeedService {
	
	public List<Feed> getAllFeeds();
	
	public List<Feed> getLastTen(); 
	
	public Feed getFeed(Long id);
	
	public void addFeed(Feed feed);
	
	public void updateFeed(Feed feed);
	
	public void deleteFeed(Long id);
	
	public List<Feed> readFeed(String url);
	
	public Feed editFeed(Feed feed);
	
	public boolean isNew(Feed feed);

	List<Feed> getLastTenWithStream();

	void deleteAll();

}
