package vn.tiki.appid.data.entity;

import com.google.auto.value.AutoValue;

/**
 * Created by Giang Nguyen on 1/7/17.
 */
@AutoValue
public abstract class LoadingItem {

  protected abstract int id();

  public static LoadingItem loadingItem() {
    return new AutoValue_LoadingItem(1);
  }

}
