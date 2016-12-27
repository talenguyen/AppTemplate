package vn.tiki.appid;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import io.reactivex.Single;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.concurrent.TimeUnit;
import vn.tiki.appid.common.base.BaseActivity;

/**
 * Created by Giang Nguyen on 10/8/16.
 */

public class SplashActivity extends BaseActivity {

  private static final String TAG = "SplashActivity";
  private DisposableSingleObserver<Long> disposable;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    injector().inject(this);
    Log.d(TAG, "onCreate: ");
  }

  @Override protected void onResume() {
    super.onResume();

    disposable = Single.timer(1, TimeUnit.SECONDS)
        .subscribeWith(new DisposableSingleObserver<Long>() {
          @Override public void onSuccess(Long aLong) {
            final Intent homeActivityIntent = new Intent(SplashActivity.this, DemoActivity.class);
            startActivity(homeActivityIntent);
            finish();
          }

          @Override public void onError(Throwable throwable) {
            finish();
          }
        });
  }

  @Override protected void onPause() {
    super.onPause();
    if (disposable != null) {
      disposable.dispose();
    }
  }
}
