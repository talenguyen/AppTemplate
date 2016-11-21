package vn.tiki.appid.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.tiki.appid.user.R;
import vn.tiki.appid.common.TheApp;
import vn.tiki.appid.common.base.BaseActivity;
import vn.tiki.appid.common.di.Injector;
import vn.tiki.appid.data.entity.User;
import vn.tiki.appid.user.signin.SignInFragment;

/**
 * Created by Giang Nguyen on 11/14/16.
 */

public class LoginActivity extends BaseActivity
    implements Injector, LoginCallback {

  private LoginComponent loginComponent;

  protected LoginComponent loginComponent() {
    if (loginComponent == null) {
      loginComponent = superComponent().plus(new LoginModule());
    }
    return loginComponent;
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.contentView, new SignInFragment())
          .commit();
    }
  }

  @Override public void inject(Object object) {
    if (object instanceof SignInFragment) {
      loginComponent().inject((SignInFragment) object);
    }
  }

  @Override public void onSuccess(User user) {
    TheApp.get(this).setUser(user);
    setResult(RESULT_OK);
    finish();
  }
}
