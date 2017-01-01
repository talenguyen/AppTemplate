package vn.tiki.appid;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import vn.tiki.appid.common.navigating.Navigating;
import vn.tiki.appid.home.HomeActivity;
import vn.tiki.appid.product.list.ProductListActivity;

/**
 * Created by Giang Nguyen on 1/1/17.
 */

class NavigatingImpl implements Navigating {

  @NonNull @Override public Intent homeActivityIntent(Context context) {
    return new Intent(context, HomeActivity.class);
  }

  @NonNull @Override public Intent productListActivityIntent(Context context) {
    return new Intent(context, ProductListActivity.class);
  }
}
