package vn.tiki.appid;

import vn.tiki.appid.common.TheApp;
import vn.tiki.appid.data.DataModule;

/**
 * Created by Giang Nguyen on 10/8/16.
 */

public class MyApplication extends TheApp {

  private AppComponent appComponent;

  @Override public void onCreate() {
    super.onCreate();

    appComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .dataModule(new DataModule())
        .build();
  }

  @Override public void inject(Object object) {
    if (object instanceof SplashActivity) {
      appComponent.inject((SplashActivity) object);
    }
  }

  @SuppressWarnings("unchecked")
  @Override public <T> T plus(Object module) {
    return null;
  }
}
