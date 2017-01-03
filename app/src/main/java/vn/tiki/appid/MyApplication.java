package vn.tiki.appid;

import javax.inject.Inject;
import vn.tiki.appid.common.TheApp;
import vn.tiki.appid.data.DataModule;
import vn.tiki.appid.developer_settings.LeakCanaryProxy;
import vn.tiki.appid.home.HomeModule;
import vn.tiki.appid.product.ProductModule;

/**
 * Created by Giang Nguyen on 10/8/16.
 */

public class MyApplication extends TheApp {

  @Inject LeakCanaryProxy leakCanaryProxy;

  private AppComponent appComponent;

  @Override public void onCreate() {
    super.onCreate();

    appComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .dataModule(new DataModule())
        .build();

    appComponent.inject(this);

    // TODO: 1/3/17 Workaround to support run with BUCK
    //leakCanaryProxy.init();
  }

  @Override public void inject(Object object) {
    if (object instanceof SplashActivity) {
      appComponent.inject((SplashActivity) object);
    }
  }

  @SuppressWarnings("unchecked")
  @Override public <T> T plus(Object module) {
    if (module instanceof HomeModule) {
      return (T) appComponent.plus((HomeModule) module);
    } else if (module instanceof ProductModule) {
      return (T) appComponent.plus((ProductModule) module);
    }
    return null;
  }
}
