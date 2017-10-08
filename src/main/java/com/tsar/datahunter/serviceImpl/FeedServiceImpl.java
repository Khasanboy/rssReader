package com.tsar.datahunter.serviceImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.tsar.datahunter.model.Feed;
import com.tsar.datahunter.repository.FeedRepository;
import com.tsar.datahunter.service.FeedService;

@Service
public class FeedServiceImpl implements FeedService {
	
	private static Logger logger = LoggerFactory.getLogger(FeedService.class);

	@Autowired
	FeedRepository feedRepository;

	@Override
	public List<Feed> getAllFeeds() {
		return feedRepository.findAll();
	}
	
	@Override
	public Feed getFeed(Long id) {
		return feedRepository.findOne(id);
	}

	@Override
	public void addFeed(Feed feed) {
		feedRepository.save(feed);
	}

	@Override
	public void updateFeed(Feed feed) {
		feedRepository.save(feed);
	}

	@Override
	public void deleteFeed(Long id) {
		feedRepository.delete(id);
	}

	@Override
	public List<Feed> readFeed(String url) {
		
		List<Feed> feeds = new ArrayList<Feed>();

		try {
			URL feedUrl = new URL(url);

			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedUrl));

			for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {				
				Feed newFeed = new Feed(entry.getTitle(),entry.getDescription().getValue(), entry.getPublishedDate(), entry.getUri());
				feeds.add(newFeed);
			}
		} catch (Exception ex) {
			logger.error("Error: " + ex.getMessage());
		}
		
		return feeds;

	}

	@Override
	public Feed editFeed(Feed feed) {
		feed.setTitle(feed.getTitle().toUpperCase());
		feed.setSavedToDb(new Date());
		return feed;
	}

	@Override
	public boolean isNew(Feed feed) {
		if(feedRepository.findByGuid(feed.getGuid())!= null)
			return false;
		
		return true;
	}


	
	//This function returns last 10 items but not in correct order. If we want we can built new list from this one in correct oreder and return
	@Override
	public List<Feed> getLastTen() {
		 
		return feedRepository.findFirst10ByOrderByIdDesc();
	}
	
	@Override
	public List<Feed> getLastTenWithStream(){
		long count = feedRepository.count();
		List<Feed> list = feedRepository.findAll().stream()
				.filter(feed->feed.getId()>count-10)
				.collect(Collectors.toList());

		return list;
	}
	
	
	
}