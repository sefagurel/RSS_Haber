package com.sefagurel.rsshaber_sondakika.models.search;

import java.util.List;

public final class SearchModel {

	public String				queryType;
	public String				hint;
	public String				scheme;
	public List<ResultsEntity>	results;
	public List<String>			related;

	public static class ResultsEntity {
		public String		feedId;
		public String		language;
		public String		title;
		public float		velocity;
		public int			subscribers;
		public long			lastUpdated;
		public String		website;
		public float		score;
		public boolean		curated;
		public boolean		featured;
		public float		coverage;
		public float		coverageScore;
		public int			estimatedEngagement;
		public String		hint;
		public String		scheme;
		public String		contentType;
		public String		description;
		public String		coverUrl;
		public String		iconUrl;
		public boolean		partial;
		public String		twitterScreenName;
		public String		visualUrl;
		public String		coverColor;
		public long			twitterFollowers;
		public String		facebookUsername;
		public long			facebookLikes;
		public float		art;
		public String		logo;
		public String		relatedLayout;
		public String		relatedTarget;
		public List<String>	deliciousTags;

	}

}