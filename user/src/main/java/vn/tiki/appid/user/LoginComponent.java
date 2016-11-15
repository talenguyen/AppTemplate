package vn.tiki.appid.user;

import vn.tiki.appid.user.signin.SignInFragment;
import dagger.Subcomponent;

/**
 * Created by Giang Nguyen on 11/14/16.
 */
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent {

  void inject(SignInFragment signInFragment);
}
