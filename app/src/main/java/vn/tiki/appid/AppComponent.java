package vn.tiki.appid;

import dagger.Component;
import javax.inject.Singleton;
import vn.tiki.appid.data.DataModule;
import vn.tiki.appid.developer_settings.DeveloperSettingsModule;
import vn.tiki.appid.home.HomeComponent;
import vn.tiki.appid.home.HomeModule;
import vn.tiki.appid.product.ProductComponent;
import vn.tiki.appid.product.ProductModule;

/**
 * Created by Giang Nguyen on 10/8/16.
 */
@Singleton
@Component(modules = {
    AppModule.class,
    DataModule.class,
    DeveloperSettingsModule.class
})
interface AppComponent {

  void inject(MyApplication application);

  void inject(SplashActivity splashActivity);

  HomeComponent plus(HomeModule homeModule);

  ProductComponent plus(ProductModule productModule);
}
