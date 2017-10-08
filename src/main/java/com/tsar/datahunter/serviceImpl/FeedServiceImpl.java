package com.tsar.datahunter.serviceImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
				System.out.println("Title: " + entry.getTitle());
				System.out.println("Description "+entry.getDescription().getValue());
				System.out.println("Published date: "+entry.getPublishedDate());
				System.out.println("Unique Identifier: " + entry.getUri());
				
				Feed newFeed = new Feed(entry.getTitle(),entry.getDescription().getValue(), entry.getPublishedDate(), entry.getUri());
				feeds.add(newFeed);
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		
		return feeds;

	}

	@Override
	public Feed editFeed(Feed feed) {
		feed.setSavedToDb(new Date());
		return feed;
	}

	@Override
	public boolean isNew(Feed feed) {
		if(feedRepository.findByGuid(feed.getGuid())!= null)
			return false;
		
		return true;
	}



	@Override
	public List<Feed> getLastTen() {
		 //return feedRepository.findAll((new PageRequest(0, 10, Direction.DESC, "id")));
		return feedRepository.findFirst10ByOrderByIdDesc();
	}
	
	
	
}