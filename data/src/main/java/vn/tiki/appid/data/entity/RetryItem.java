package vn.tiki.appid.data.entity;

import com.google.auto.value.AutoValue;

/**
 * Created by Giang Nguyen on 1/7/17.
 */
@AutoValue
public abstract class RetryItem {

  protected abstract int id();

  public static RetryItem retryItem() {
    return new AutoValue_RetryItem(2);
  }

}
