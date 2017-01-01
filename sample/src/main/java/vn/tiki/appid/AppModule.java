package vn.tiki.appid;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import vn.tiki.appid.common.navigating.Navigating;
import vn.tiki.appid.home.HomeActivity;

/**
 * Created by Giang Nguyen on 10/8/16.
 */
@Module class AppModule {
  private Application application;

  AppModule(Application application) {
    this.application = application;
  }

  @Singleton
  @Provides
  @Named("application") Context provideApplicationContext() {
    return application;
  }

  @Provides Navigating provideNavigator() {
    return new Navigating() {
      @NonNull @Override public Intent homeActivityIntent(Context context) {
        return new Intent(context, HomeActivity.class);
      }

      @NonNull @Override public Intent productListActivityIntent(Context context) {
        return null;
      }
    };
  }
}
