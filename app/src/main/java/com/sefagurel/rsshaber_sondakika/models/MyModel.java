package com.sefagurel.rsshaber_sondakika.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sefa on 20.12.2015.
 */
public class MyModel {

	public String						id;
	public String						direction;
	public long							updated;
	public String						title;
	public String						continuation;
	public ArrayList<AlternateEntity>	alternate;
	public ArrayList<ItemsEntity>		items;

	public static class AlternateEntity {
		public String	href;
		public String	type;
	}

	public static class ItemsEntity {

		public String					id;
		public String					originId;
		public String					fingerprint;
		public String					title;
		public String					published;
		public String					crawled;
		public String					recrawled;
		public String					author;
		public OriginEntity				origin;
		public SummaryEntity			summary;
		public VisualEntity				visual;
		public boolean					unread;
		public String					engagement;
		public String					engagementRate;
		public List<String>				keywords;
		public List<ThumbnailEntity>	thumbnail;
		public List<EnclosureEntity>	enclosure;
		public List<AlternateEntity>	alternate;
		public List<CanonicalEntity>	canonical;

		public static class OriginEntity {
			public String	streamId;
			public String	title;
			public String	htmlUrl;
		}

		public static class SummaryEntity {
			public String	content;
			public String	direction;
		}

		public static class VisualEntity {

			public String	url;
			public String	width;
			public String	height;
			public long		expirationDate;
			public String	edgeCacheUrl;
			public String	processor;
			public String	contentType;
		}

		public static class ThumbnailEntity {
			public String url;
		}

		public static class EnclosureEntity {
			public String	href;
			public String	type;
			public int		length;
		}

		public static class AlternateEntity {
			public String	href;
			public String	type;
		}

		public static class CanonicalEntity {
			public String	href;
			public String	type;
		}
	}
}
