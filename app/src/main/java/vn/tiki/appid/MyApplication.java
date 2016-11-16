package vn.tiki.appid;

import rx.plugins.RxJavaHooks;
import vn.tiki.appid.common.TheApp;
import vn.tiki.appid.data.DataModule;
import vn.tiki.appid.data.entity.User;
import vn.tiki.appid.user.LoginModule;

/**
 * Created by Giang Nguyen on 10/8/16.
 */

public class MyApplication extends TheApp {

  private AppComponent appComponent;
  private UserComponent userComponent;

  @Override public void onCreate() {
    RxJavaHooks.enableAssemblyTracking();
    super.onCreate();

    appComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .dataModule(new DataModule())
        .build();
  }

  @Override public void setUser(User user) {
    if (user == null) {
      userComponent = null;
    } else {
      userComponent = appComponent.plus(new UserModule(user));
    }
  }

  @Override public void inject(Object object) {
    if (object instanceof SplashActivity) {
      appComponent.inject((SplashActivity) object);
    }
  }

  @SuppressWarnings("unchecked")
  @Override public <T> T plus(Object module) {
    if (module instanceof LoginModule) {
      return (T) appComponent.plus((LoginModule) module);
    }
    //else if (module instanceof ProductModule) {
    //  return (T) userComponent.plus((ProductModule) module);
    //} else if (module instanceof CheckoutModule) {
    //  return (T) userComponent.plus((CheckoutModule) module);
    //}
    return null;
  }
}
