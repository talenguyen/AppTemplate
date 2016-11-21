package vn.tiki.appid.user.signin;

import android.util.Log;
import hugo.weaving.DebugLog;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import vn.tiki.appid.common.base.MvpPresenter;
import vn.tiki.appid.data.entity.User;
import vn.tiki.appid.data.exception.AuthenticationException;
import vn.tiki.appid.data.exception.NetworkException;
import vn.tiki.appid.data.model.UserModel;

/**
 * Created by Giang Nguyen on 11/14/16.
 */

public class SignInPresenter extends MvpPresenter<SignInView> {

  private final CompositeSubscription subscription = new CompositeSubscription();
  private final UserModel userModel;

  private static final String TAG = "SignInPresenter";

  @DebugLog
  public SignInPresenter(UserModel userModel) {
    this.userModel = userModel;
    Log.d(TAG, "SignInPresenter: ");
  }

  void login(String email, String password) {
    final SignInView signInView = getView();
    if (signInView != null) {
      signInView.showLoading();
    }

    subscription.add(userModel.login(email, password)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<User>() {
          @Override public void call(User user) {
            if (getView() != null) {
              getView().showSuccess(user);
            }
          }
        }, new Action1<Throwable>() {
          @Override public void call(Throwable throwable) {
            final SignInView signInView = getView();
            if (signInView == null) {
              return;
            }
            if (throwable instanceof AuthenticationException) {
              signInView.showLoginError();
            } else if (throwable instanceof NetworkException) {
              signInView.showNetworkError();
            } else {
              signInView.showError();
            }
          }
        }));
  }

  @Override public void detachView() {
    super.detachView();
    subscription.clear();
  }
}
