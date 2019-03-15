package com.serverless;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;

import java.util.List;
import java.util.stream.Collectors;

public class FeedFetchResponse {
	public static class Article {
		public String title;
		public String link;

		public Article(SyndEntry entry) {
			this.title = entry.getTitle();
			this.link = entry.getLink();
		}
	}

	public String title;
	public List<Article> articles;

	public FeedFetchResponse(SyndFeed feed) {
		this.title = feed.getTitle();
		this.articles = feed.getEntries().stream().map(e -> new Article(e)).collect(Collectors.toList());
	}
}