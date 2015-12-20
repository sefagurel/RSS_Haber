package com.sefagurel.rsshaber_sondakika.adapters;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sefagurel.rsshaber_sondakika.R;
import com.sefagurel.rsshaber_sondakika.database.Favorites;
import com.sefagurel.rsshaber_sondakika.models.SearchModel;
import com.sefagurel.rsshaber_sondakika.tools.ImageTools;

public class SearchResultListAdapter extends RecyclerView.Adapter<SearchResultListAdapter.CardviewHolder> {

	private Context							context;
	private int								lastPosition	= -1;
	private List<SearchModel.ResultsEntity>	resultList;

	public SearchResultListAdapter(Context context, SearchModel searchModel) {
		this.context = context;
		this.resultList = searchModel.results;
	}

	public SearchModel.ResultsEntity getItemByPosition(int position) {
		return resultList.get(position);
	}

	@Override
	public CardviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result, parent, false);
		return new CardviewHolder(v);
	}

	@Override
	public void onBindViewHolder(final CardviewHolder holder, int position) {

		final SearchModel.ResultsEntity result = resultList.get(position);

		ImageTools.showFitImage(holder.imageView.getContext(), holder.imageView, result.visualUrl);

		holder.tvTitle.setText(result.title);
		holder.tvSubTitle.setText(result.description);

		holder.checkBox.setOnCheckedChangeListener(null);
		holder.checkBox.setChecked(result.isSelected);

		holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				result.isSelected = isChecked;

				Favorites favorites = new Favorites();

				if (isChecked) {
					favorites.id = result.feedId;
					favorites.Insert();
				}
				else {
                    favorites.DeleteRow(result.feedId);
				}

			}
		});

		// setAnimation(holder.cardView, position);
	}

	private void setAnimation(View viewToAnimate, int position) {

		if (position > lastPosition) {
			Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
			viewToAnimate.startAnimation(animation);
			lastPosition = position;
		}
	}

	@Override
	public int getItemCount() {
		return resultList.size();
	}

	public static class CardviewHolder extends RecyclerView.ViewHolder {

		CardView	cardView;
		ImageView	imageView;
		TextView	tvTitle;
		TextView	tvSubTitle;
		CheckBox	checkBox;

		public CardviewHolder(View itemView) {
			super(itemView);
			cardView = (CardView) itemView.findViewById(R.id.cv_card);
			imageView = (ImageView) itemView.findViewById(R.id.iv_image);
			tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
			tvSubTitle = (TextView) itemView.findViewById(R.id.tv_subtitle);
			checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
		}

	}

}
