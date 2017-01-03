package vn.tiki.appid.product.list;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.tiki.appid.product.R;
import com.tiki.appid.product.R2;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import vn.tiki.appid.common.util.ImageLoader;
import vn.tiki.appid.common.widget.PriceTextView;
import vn.tiki.appid.data.entity.Product;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Giang Nguyen on 1/1/17.
 */
public class ProductListItemViewHolderTest {
  private ImageView ivThumbMocked;
  private TextView tvNameMocked;
  private TextView tvRatingMocked;
  private TextView tvReviewCountMocked;
  private PriceTextView tvPriceMocked;
  private PriceTextView tvOriginalPriceMocked;
  private TextView tvDiscountRateMocked;
  private ImageLoader imageLoaderMocked;
  private Context contextMocked;

  private ProductListItemViewHolder productListItemViewHolder;

  @Before
  public void setUp() throws Exception {

    final View itemView = Mockito.mock(View.class);
    contextMocked = Mockito.mock(Context.class);
    ivThumbMocked = Mockito.mock(ImageView.class);
    tvNameMocked = Mockito.mock(TextView.class);
    tvRatingMocked = Mockito.mock(TextView.class);
    tvReviewCountMocked = Mockito.mock(TextView.class);
    tvPriceMocked = Mockito.mock(PriceTextView.class);
    tvOriginalPriceMocked = Mockito.mock(PriceTextView.class);
    tvDiscountRateMocked = Mockito.mock(TextView.class);

    when(itemView.getContext())
        .thenReturn(contextMocked);
    when(itemView.findViewById(R2.id.ivThumb))
        .thenReturn(ivThumbMocked);
    when(itemView.findViewById(R2.id.tvName))
        .thenReturn(tvNameMocked);
    when(itemView.findViewById(R2.id.tvRating))
        .thenReturn(tvRatingMocked);
    when(itemView.findViewById(R2.id.tvReviewCount))
        .thenReturn(tvReviewCountMocked);
    when(itemView.findViewById(R2.id.tvPrice))
        .thenReturn(tvPriceMocked);
    when(itemView.findViewById(R2.id.tvOriginalPrice))
        .thenReturn(tvOriginalPriceMocked);
    when(itemView.findViewById(R2.id.tvDiscountRate))
        .thenReturn(tvDiscountRateMocked);

    imageLoaderMocked = Mockito.mock(ImageLoader.class);
    productListItemViewHolder = new ProductListItemViewHolder(itemView, imageLoaderMocked);
  }

  @Test
  public void shouldFoundViews() throws Exception {
    assertEquals(ivThumbMocked, productListItemViewHolder.ivThumb);
    assertEquals(tvNameMocked, productListItemViewHolder.tvName);
    assertEquals(tvRatingMocked, productListItemViewHolder.tvRating);
    assertEquals(tvReviewCountMocked, productListItemViewHolder.tvReviewCount);
    assertEquals(tvPriceMocked, productListItemViewHolder.tvPrice);
    assertEquals(tvOriginalPriceMocked, productListItemViewHolder.tvOriginalPrice);
    assertEquals(tvDiscountRateMocked, productListItemViewHolder.tvDiscountRate);
  }

  @Test
  public void shouldBindProductToViews() throws Exception {

    final Product product = new Product() {
      @Override public double ratingAverage() {
        return 4.5;
      }

      @Override public int price() {
        return 100;
      }

      @Override public String name() {
        return "Name";
      }

      @Override public int reviewCount() {
        return 1;
      }

      @Override public int id() {
        return 1;
      }

      @Override public int listPrice() {
        return 80;
      }

      @Override public String thumbnailUrl() {
        return "http://tiki.vn/thumbnail.png";
      }

      @Override public int discountRate() {
        return 20;
      }
    };

    final String discountRateFormatted = "20% off";
    final String reviewCountFormatted = "(1)";

    when(contextMocked.getString(R.string.discount_rate_format, product.discountRate()))
        .thenReturn(discountRateFormatted);
    when(contextMocked.getString(R.string.review_count_format, product.reviewCount()))
        .thenReturn(reviewCountFormatted);

    productListItemViewHolder.bind(product);

    verify(imageLoaderMocked).downloadInto(product.thumbnailUrl(), ivThumbMocked);
    verify(tvNameMocked).setText(product.name());
    verify(tvRatingMocked).setText("4.5");
    verify(tvReviewCountMocked).setText(reviewCountFormatted);
    verify(tvPriceMocked).setPrice(product.listPrice());
    verify(tvOriginalPriceMocked).setPrice(product.price());
    verify(tvDiscountRateMocked).setText(discountRateFormatted);
  }
}