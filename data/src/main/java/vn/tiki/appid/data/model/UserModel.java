package vn.tiki.appid.data.model;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import java.util.concurrent.Callable;
import vn.tiki.appid.data.entity.User;
import vn.tiki.appid.data.exception.AuthenticationException;
import vn.tiki.appid.data.exception.UserNotFoundException;

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
        if (!"user@tiki.vn".equals(email) || !"123456".equals(password)) {
          throw new AuthenticationException();
        }
        return new User();
      }
    }).doOnNext(setUser());
  }

  private Consumer<? super User> setUser() {
    return new Consumer<User>() {
      @Override public void accept(User user) throws Exception {
        UserModel.this.user = user;
      }
    };
  }
}
