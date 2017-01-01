package vn.tiki.appid.product.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.tiki.appid.product.R;
import com.tiki.appid.product.R2;
import vn.tiki.appid.common.util.ImageLoader;
import vn.tiki.appid.common.widget.PriceTextView;
import vn.tiki.appid.data.entity.Product;
import vn.tiki.noadapter.AbsViewHolder;

/**
 * Created by Giang Nguyen on 12/31/16.
 */
class ProductListItemViewHolder extends AbsViewHolder {
  @NonNull private final ImageLoader imageLoader;
  @BindView(R2.id.ivThumb) ImageView ivThumb;
  @BindView(R2.id.tvName) TextView tvName;
  @BindView(R2.id.tvRating) TextView tvRating;
  @BindView(R2.id.tvReviewCount) TextView tvReviewCount;
  @BindView(R2.id.tvPrice) PriceTextView tvPrice;
  @BindView(R2.id.tvOriginalPrice) PriceTextView tvOriginalPrice;
  @BindView(R2.id.tvDiscountRate) TextView tvDiscountRate;

  ProductListItemViewHolder(@NonNull View itemView, @NonNull ImageLoader imageLoader) {
    super(itemView);
    this.imageLoader = imageLoader;
    ButterKnife.bind(this, itemView);
  }

  static ProductListItemViewHolder create(ViewGroup parent, ImageLoader imageLoader) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    final View view = inflater.inflate(R.layout.item_product_list, parent, false);
    return new ProductListItemViewHolder(view, imageLoader);
  }

  @Override public void bind(Object item) {
    super.bind(item);
    if (isNotInstanceOfProduct(item)) {
      return;
    }

    final Product product = (Product) item;
    final Context context = itemView.getContext();
    imageLoader.downloadInto(product.thumbnailUrl(), ivThumb);

    tvName.setText(product.name());
    tvRating.setText(toString(product.ratingAverage()));
    tvReviewCount.setText(context.getString(R.string.review_count_format, product.reviewCount()));
    tvPrice.setPrice(product.listPrice());
    tvOriginalPrice.setPrice(product.price());
    tvDiscountRate.setText(context.getString(
        R.string.discount_rate_format,
        product.discountRate()));
  }

  @NonNull private String toString(double value) {
    return String.valueOf(value);
  }

  private boolean isNotInstanceOfProduct(Object item) {
    return !(item instanceof Product);
  }
}
