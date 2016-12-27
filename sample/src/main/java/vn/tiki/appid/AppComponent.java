package vn.tiki.appid;

import dagger.Component;
import javax.inject.Singleton;
import vn.tiki.appid.data.DataModule;
import vn.tiki.appid.home.HomeComponent;
import vn.tiki.appid.home.HomeModule;

/**
 * Created by Giang Nguyen on 10/8/16.
 */
@Singleton
@Component(modules = {
    AppModule.class,
    DataModule.class
})
interface AppComponent {

  void inject(SplashActivity splashActivity);

  HomeComponent plus(HomeModule homeModule);
}
