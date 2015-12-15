package com.sefagurel.rsshaber_sondakika.models;

import java.util.List;

/**
 * Created by Sefa on 12.12.2015.
 */
public class NewsModel {

	public String					id;
	public String					direction;
	public long						updated;
	public String					title;
	public String					continuation;
	public List<AlternateEntity>	alternate;
	public List<ItemsEntity>		items;

	public static class AlternateEntity {
		public String	href;
		public String	type;
	}

	public static class ItemsEntity {
		public String					id;
		public String					originId;
		public String					fingerprint;
		public String					title;
		public long						published;
		public long						crawled;
		public OriginEntity				origin;
		public SummaryEntity			summary;
		public VisualEntity				visual;
		public boolean					unread;
		public List<AlternateEntity>	alternate;
		public List<EnclosureEntity>	enclosure;

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
			public long		expirationDate;
			public int		width;
			public int		height;
			public String	edgeCacheUrl;
			public String	processor;
			public String	contentType;
		}

		public static class AlternateEntity {
			public String	href;
			public String	type;
		}

		public static class EnclosureEntity {
			public String	href;
			public String	type;
			public int		length;
		}
	}
}
