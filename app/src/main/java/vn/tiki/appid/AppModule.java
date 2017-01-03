package vn.tiki.appid;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import vn.tiki.appid.common.navigating.Navigating;
import vn.tiki.appid.common.util.ImageLoader;

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
  Application provideApplication() {
    return application;
  }

  @Singleton
  @Provides
  @Named("application") Context provideApplicationContext() {
    return application;
  }

  @Provides Navigating provideNavigator() {
    return new NavigatingImpl();
  }

  @Provides ImageLoader provideImageLoader() {
    return new GlideImageLoader();
  }
}
