package vn.tiki.appid.common.util;

import android.support.annotation.NonNull;
import android.widget.ImageView;

/**
 * Created by Giang Nguyen on 1/1/17.
 */

public interface ImageLoader {
  void downloadInto(@NonNull String url, @NonNull ImageView imageView);
}
