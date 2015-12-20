package com.sefagurel.rsshaber_sondakika.models;

import com.google.gson.annotations.Expose;

import java.util.List;

public class SearchModel {

	@Expose public String				queryType;
	@Expose public String				hint;
	@Expose public String				scheme;
	@Expose public List<ResultsEntity>	results;
	@Expose public List<String>			related;

	public static class ResultsEntity {
		@Expose public String		feedId;
		@Expose public String		language;
		@Expose public String		title;
		@Expose public float		velocity;
		@Expose public int			subscribers;
		@Expose public long			lastUpdated;
		@Expose public String		website;
		@Expose public float		score;
		@Expose public boolean		curated;
		@Expose public boolean		featured;
		@Expose public float		coverage;
		@Expose public float		coverageScore;
		@Expose public int			estimatedEngagement;
		@Expose public String		hint;
		@Expose public String		scheme;
		@Expose public String		contentType;
		@Expose public String		description;
		@Expose public String		coverUrl;
		@Expose public String		iconUrl;
		@Expose public boolean		partial;
		@Expose public String		twitterScreenName;
		@Expose public String		visualUrl;
		@Expose public String		coverColor;
		@Expose public long			twitterFollowers;
		@Expose public String		facebookUsername;
		@Expose public long			facebookLikes;
		@Expose public float		art;
		@Expose public String		logo;
		@Expose public String		relatedLayout;
		@Expose public String		relatedTarget;
		@Expose public List<String>	deliciousTags;
		public boolean				isSelected	= false;

	}

}