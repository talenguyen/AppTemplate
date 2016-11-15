package vn.tiki.appid.user.signin;

import vn.tiki.appid.data.entity.User;

/**
 * Created by Giang Nguyen on 11/14/16.
 */

public interface SignInView {

  void showLoading();

  void showLoginError();

  void showSuccess(User user);

  void showNetworkError();

  void showError();
}
