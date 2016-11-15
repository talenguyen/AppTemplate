package vn.tiki.appid.common.navigating;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Created by Giang Nguyen on 10/8/16.
 */

public interface Navigating {

  @NonNull Intent productListActivityIntent(Context context);

  @NonNull Intent loginActivityIntent(Context context);
}
