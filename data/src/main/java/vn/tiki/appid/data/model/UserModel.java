package vn.tiki.appid.data.model;

import vn.tiki.appid.data.entity.User;
import vn.tiki.appid.data.exception.AuthenticationException;
import vn.tiki.appid.data.exception.UserNotFoundException;
import java.util.concurrent.Callable;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Giang Nguyen on 11/14/16.
 */

public class UserModel {
  public static final User GUEST = new User();
  private User user;

  public Observable<User> getUser() {
    return Observable.fromCallable(new Callable<User>() {
      @Override public User call() throws Exception {
        if (user == null) {
          throw new UserNotFoundException();
        }
        return user;
      }
    });
  }

  public Observable<User> login(final String email, final String password) {
    return Observable.fromCallable(new Callable<User>() {
      @Override public User call() throws Exception {
        if (!"user@wata.com".equals(email) || !"123456".equals(password)) {
          throw new AuthenticationException();
        }
        return new User();
      }
    }).doOnNext(setUser());
  }

  private Action1<? super User> setUser() {
    return new Action1<User>() {
      @Override public void call(User user) {
        UserModel.this.user = user;
      }
    };
  }
}
