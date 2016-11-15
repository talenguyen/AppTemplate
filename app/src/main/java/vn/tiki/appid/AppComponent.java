package vn.tiki.appid;

import vn.tiki.appid.data.DataModule;
import vn.tiki.appid.user.LoginComponent;
import vn.tiki.appid.user.LoginModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by Giang Nguyen on 10/8/16.
 */
@Singleton
@Component(modules = {
    AppModule.class,
    DataModule.class
})
interface AppComponent {

  UserComponent plus(UserModule userModule);

  LoginComponent plus(LoginModule loginModule);

  void inject(SplashActivity splashActivity);

}
