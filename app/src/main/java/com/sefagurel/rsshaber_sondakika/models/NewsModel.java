package com.sefagurel.rsshaber_sondakika.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sefa on 20.12.2015.
 */
public class NewsModel {

	@Expose public String						id;
	@Expose public String						direction;
	@Expose public long							updated;
	@Expose public String						title;
	@Expose public String						continuation;
	@Expose public ArrayList<AlternateEntity>	alternate;
	@Expose public ArrayList<ItemsEntity>		items;

	public static class AlternateEntity {
		@Expose public String	href;
		@Expose public String	type;
	}

	public static class ItemsEntity {

		@Expose public String					id;
		@Expose public String					originId;
		@Expose public String					fingerprint;
		@Expose public String					title;
		@Expose public String					published;
		@Expose public String					crawled;
		@Expose public String					recrawled;
		@Expose public String					author;
		@Expose public OriginEntity				origin;
		@Expose public SummaryEntity			summary;
		@Expose public VisualEntity				visual;
		@Expose public boolean					unread;
		@Expose public String					engagement;
		@Expose public String					engagementRate;
		@Expose public List<String>				keywords;
		@Expose public List<ThumbnailEntity>	thumbnail;
		@Expose public List<EnclosureEntity>	enclosure;
		@Expose public List<AlternateEntity>	alternate;
		@Expose public List<CanonicalEntity>	canonical;

		public static class OriginEntity {
			@Expose public String	streamId;
			@Expose public String	title;
			@Expose public String	htmlUrl;
		}

		public static class SummaryEntity {
			@Expose public String	content;
			@Expose public String	direction;
		}

		public static class VisualEntity {

			@Expose public String	url;
			@Expose public String	width;
			@Expose public String	height;
			@Expose public long		expirationDate;
			@Expose public String	edgeCacheUrl;
			@Expose public String	processor;
			@Expose public String	contentType;
		}

		public static class ThumbnailEntity {
			@Expose public String url;
		}

		public static class EnclosureEntity {
			@Expose public String	href;
			@Expose public String	type;
			@Expose public int		length;
		}

		public static class AlternateEntity {
			@Expose public String	href;
			@Expose public String	type;
		}

		public static class CanonicalEntity {
			@Expose public String	href;
			@Expose public String	type;
		}
	}
}
