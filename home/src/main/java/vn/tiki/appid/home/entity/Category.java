package vn.tiki.appid.home.entity;

/**
 * Created by Giang Nguyen on 12/27/16.
 */
@com.google.auto.value.AutoValue
public abstract class Category {

  public abstract String id();

  public abstract String name();

  public abstract int icon();

  public static Builder builder(Category source) {
    return new AutoValue_Category.Builder(source);
  }

  public static Builder builder() {
    return new AutoValue_Category.Builder();
  }

  @com.google.auto.value.AutoValue.Builder
  public static abstract class Builder {
    public abstract Builder id(String id);

    public abstract Builder name(String name);

    public abstract Builder icon(int icon);

    public abstract Category make();
  }
}
