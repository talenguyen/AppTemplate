package vn.tiki.appid.home;

import dagger.Module;
import dagger.Provides;
import vn.tiki.appid.home.widgets.WidgetsPresenter;

/**
 * Created by Giang Nguyen on 11/14/16.
 */
@Module
public class HomeModule {

  @Provides WidgetsPresenter provideWidgetsPresenter() {
    return new WidgetsPresenter();
  }
}
