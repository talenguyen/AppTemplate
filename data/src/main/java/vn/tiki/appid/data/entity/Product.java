package vn.tiki.appid.data.entity;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Product {

  public static TypeAdapter<Product> typeAdapter(Gson gson) {
    return new AutoValue_Product.GsonTypeAdapter(gson);
  }

  @SerializedName("rating_average")
  public abstract double ratingAverage();

  @SerializedName("price")
  public abstract int price();

  @SerializedName("name")
  public abstract String name();

  @SerializedName("review_count")
  public abstract int reviewCount();

  @SerializedName("id")
  public abstract int id();

  @SerializedName("list_price")
  public abstract int listPrice();

  @SerializedName("thumbnail_url")
  public abstract String thumbnailUrl();

  @SerializedName("discount_rate")
  public abstract int discountRate();
}