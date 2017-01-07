package vn.tiki.appid.data.entity;

import com.google.auto.value.AutoValue;

/**
 * Created by Giang Nguyen on 1/7/17.
 */
@AutoValue
public abstract class Page {

  private static final int FIRST_PAGE_INDEX = 1;

  public static Page page(int index) {
    return new AutoValue_Page(index);
  }

  public static Page firstPage() {
    return new AutoValue_Page(FIRST_PAGE_INDEX);
  }

  public abstract int index();

  public Page next() {
    return new AutoValue_Page(index() + 1);
  }
}
