package com.tsar.datahunter;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tsar.datahunter.model.Feed;
import com.tsar.datahunter.service.FeedService;

@RunWith(SpringRunner.class)
@SpringBootTest
// @EnableScheduling
public class DatahunterApplicationTests {

	@Autowired
	FeedService feedService;

	@Before
	public void initialize() {

		List<Feed> feeds = feedService.readFeed("http://feeds.bbci.co.uk/news/world/rss.xml");
		
		for (Feed feed : feeds) {

			if (feedService.isNew(feed)) {
				feedService.editFeed(feed);
				feedService.addFeed(feed);
			}
		}
		
	}

	@After
	public void delete() {
		
		System.out.println("After run");
		feedService.deleteAll();
		
		/*
		  feedService.getAllFeeds().stream()
			.forEach(feed -> feedService.deleteFeed(feed.getId()));
			*/
	}
	/*
	@Test
	public void testGetAllFeeds() {

		List<Feed> feeds = feedService.getAllFeeds();

		assertEquals(feeds.size(), 30);// At the moment there are 30 feeds in
										// the database this number may change
										// according to the rss feeds from BBC
	}
	*/

	@Test
	public void testGetFeed() {
		
		
		Feed feed = feedService.getFeed(feedService.getAllFeeds().get(5).getId());

		assertEquals(feed.getId().intValue(), feedService.getAllFeeds().get(5).getId().intValue());
	}
	
	 @Test 
	 public void testAddFeed(){
	
	  Feed feed = feedService.readFeed("http://feeds.bbci.co.uk/news/world/rss.xml").get(0);

	  int count = feedService.getAllFeeds().size();
	  feed.setId((long)count+1000); feedService.addFeed(feed);
	  
	  assertEquals(feedService.getAllFeeds().size(), count+1);//Number may change
	  
	  }
	 
	 @Test
	 public void testDeleteFeed(){
		 int size = feedService.getAllFeeds().size();
		 Long id = null;
		 if(feedService.getAllFeeds().listIterator().hasNext()){
			 id = feedService.getAllFeeds().listIterator().next().getId();
		 }
		 if(id != null)
			 feedService.deleteFeed(id);
		 
		 assertEquals(size - 1, feedService.getAllFeeds().size());
		 
	 }
	 
	 @Test
	 public void readFeed(){
		 List<Feed> list = feedService.readFeed("http://feeds.bbci.co.uk/news/world/rss.xml");
		 
		 //This may not work if rss feeds are not available in the url
		 assertNotNull(list);
	 }
	 
	 @Test
	 public void testEditFeed(){
		 Feed feed = feedService.readFeed("http://feeds.bbci.co.uk/news/world/rss.xml").get(0);
		 
		 assertFalse(feed.getTitle().equals(feed.getTitle().toUpperCase()));
		 assertNull(feed.getSavedToDb());
		 
		 feedService.editFeed(feed);
		 
		 assertTrue(feed.getTitle().equals(feed.getTitle().toUpperCase()));
		 assertNotNull(feed.getSavedToDb());
		 
	 }
	 
	 @Test
	 public void testIsNew(){
		 
		 feedService.deleteAll();
		 
		 Feed feed = feedService.readFeed("http://feeds.bbci.co.uk/news/world/rss.xml").get(0);
		 
		 assertFalse(feedService.getAllFeeds().contains(feed));
		 
	 }
	 
	 @Test
	 public void testGetLastTenWithStream(){
		 List<Feed> list = feedService.getLastTenWithStream();
		 assertEquals(list.size(), 10);
		 
		 List<Feed> lastFeeds = feedService.getAllFeeds().stream().skip(Math.max(0, feedService.getAllFeeds().size() - 10))
		 .collect(Collectors.toList());
		 
		 assertEquals(lastFeeds.size(), 10);
		 
		 assertEquals(list.size(), lastFeeds.size());
		 
		 for(int i = 0; i<list.size(); i++){
			 assertEquals(list.get(i).getId(), lastFeeds.get(i).getId());
		 }
	 }
	 
}
