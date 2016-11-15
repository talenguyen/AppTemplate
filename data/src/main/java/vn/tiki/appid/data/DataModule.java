package vn.tiki.appid.data;

import vn.tiki.appid.data.model.UserModel;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Giang Nguyen on 11/14/16.
 */
@Module
public class DataModule {
  @Singleton
  @Provides
  public UserModel provideUserModel() {
    return new UserModel();
  }
}
