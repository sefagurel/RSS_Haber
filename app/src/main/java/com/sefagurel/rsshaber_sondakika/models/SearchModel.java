package com.sefagurel.rsshaber_sondakika.models;

import java.util.List;

public final class SearchModel {

	public String				hint;
	public String				queryType;
	public String				scheme;
	public List<ResultsEntity>	results;
	public List<String>			related;


	public static class ResultsEntity {
		public String		feedId;
		public String		language;
		public String		title;
		public double		velocity;
		public int			subscribers;
		public long			lastUpdated;
		public String		website;
		public double		score;
		public double		coverage;
		public double		coverageScore;
		public int			estimatedEngagement;
		public String		hint;
		public String		scheme;
		public String		description;
		public String		contentType;
		public String		coverUrl;
		public String		iconUrl;
		public boolean		partial;
		public String		twitterScreenName;
		public String		visualUrl;
		public String		coverColor;
		public int			twitterFollowers;
		public String		facebookUsername;
		public int			facebookLikes;
		public double		art;
		public List<String>	deliciousTags;
	}
}