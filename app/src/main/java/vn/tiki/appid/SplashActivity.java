package vn.tiki.appid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import javax.inject.Inject;
import rx.Subscription;
import rx.functions.Action1;
import vn.tiki.appid.common.TheApp;
import vn.tiki.appid.common.base.BaseActivity;
import vn.tiki.appid.common.navigating.Navigating;
import vn.tiki.appid.data.entity.User;
import vn.tiki.appid.data.model.UserModel;

/**
 * Created by Giang Nguyen on 10/8/16.
 */

public class SplashActivity extends BaseActivity {

  @Inject Navigating navigating;
  @Inject UserModel userModel;
  private Subscription subscription;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    injector().inject(this);
  }


  @Override protected void onResume() {
    super.onResume();
    subscription = userModel.getUser()
        .subscribe(new Action1<User>() {
          @Override public void call(User user) {
            TheApp.get(SplashActivity.this).setUser(user);
            startActivity(navigating.productListActivityIntent(SplashActivity.this));
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            startActivity(navigating.loginActivityIntent(SplashActivity.this));
          }
        });

    finish();
  }

  @Override protected void onPause() {
    super.onPause();
    if (subscription != null) {
      subscription.unsubscribe();
    }
  }
}
