package vn.tiki.appid;

import android.support.annotation.NonNull;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import vn.tiki.appid.common.util.ImageLoader;

/**
 * Created by Giang Nguyen on 1/1/17.
 */

public class GlideImageLoader implements ImageLoader {

  @Override public void downloadInto(@NonNull String url, @NonNull ImageView imageView) {
    Glide.with(imageView.getContext())
        .load(url)
        .into(imageView);
  }
}
