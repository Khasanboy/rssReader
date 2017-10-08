package com.tsar.datahunter.configuration;

import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tsar.datahunter.model.Feed;
import com.tsar.datahunter.service.FeedService;

@Component
public class ScheduledTasks {
	
	@Autowired
	FeedService feedService;
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Scheduled(fixedRate = 180000)
	public void scheduleTasksWithFixedRate(){
		List<Feed> feeds = feedService.readFeed("http://feeds.bbci.co.uk/news/world/rss.xml");
		
		for(Feed feed:feeds){
			if(feedService.isNew(feed)){
				feedService.editFeed(feed);
				feedService.addFeed(feed);
			}
		}
	}
}
