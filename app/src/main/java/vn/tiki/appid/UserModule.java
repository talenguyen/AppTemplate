package vn.tiki.appid;

import vn.tiki.appid.common.di.UserScope;
import vn.tiki.appid.data.entity.User;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Giang Nguyen on 10/8/16.
 */
@Module class UserModule {

  public static final User GUEST = new User();
  private User user;

  UserModule(User user) {
    this.user = user;
  }

  @UserScope
  @Provides
  User provideUser() {
    return user;
  }
}
