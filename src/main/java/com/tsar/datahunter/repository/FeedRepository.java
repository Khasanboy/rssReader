package com.tsar.datahunter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsar.datahunter.model.Feed;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
	Feed findByGuid(String guid);
	 //List<Feed> findTop10();
	 List<Feed> findFirst10ByOrderByIdDesc();
	 //findFirst5ByOrderByPublicationDateDesc()
}
