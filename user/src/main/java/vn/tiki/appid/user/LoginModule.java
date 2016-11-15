package vn.tiki.appid.user;

import vn.tiki.appid.data.model.UserModel;
import vn.tiki.appid.user.signin.SignInPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Giang Nguyen on 11/14/16.
 */
@Module
public class LoginModule {

  @Provides
  SignInPresenter provideSignInPresenter(UserModel userModel) {
    return new SignInPresenter(userModel);
  }
}
