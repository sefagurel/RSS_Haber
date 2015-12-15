package com.sefagurel.rsshaber_sondakika.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.sefagurel.rsshaber_sondakika.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class ImageTools {

	public static void showImage(Context ctx, final ImageView imageView, int resurceId) {

		if (resurceId == 0) {
			resurceId = R.drawable.no_image;
		}

		if (imageView == null) {
			Log.w("ImageTools", "imageView is null");
			return;
		}

		Transformation transformation = new Transformation() {

			@Override
			public Bitmap transform(Bitmap source) {
				int targetWidth = imageView.getWidth();

				double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
				int targetHeight = (int) (targetWidth * aspectRatio);
				Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
				if (result != source) {
					// Same bitmap is returned if sizes are the same
					source.recycle();
				}
				return result;
			}

			@Override
			public String key() {
				return "transformation" + " desiredWidth";
			}
		};

		Picasso.with(ctx).load(resurceId).transform(transformation).into(imageView);

	}

	public static void showFitImage(Context ctx, final ImageView imageView, String url) {

		if (imageView == null) {
			Log.w("ImageTools", "imageView is null");
			return;
		}

		Picasso.with(ctx).load(url).fit().placeholder(R.drawable.no_image).error(R.drawable.no_image).into(imageView);

	}

}
