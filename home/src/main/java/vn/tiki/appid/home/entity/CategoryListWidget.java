package vn.tiki.appid.home.entity;

import java.util.List;

/**
 * Created by Giang Nguyen on 12/27/16.
 */
@com.google.auto.value.AutoValue
public abstract class CategoryListWidget {

  public abstract List<Category> categories();

  public static CategoryListWidget make(List<Category> categories) {
    return new AutoValue_CategoryListWidget(categories);
  }
}
