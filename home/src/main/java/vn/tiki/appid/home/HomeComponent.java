package vn.tiki.appid.home;

import dagger.Subcomponent;
import vn.tiki.appid.home.widgets.WidgetsFragment;

/**
 * Created by Giang Nguyen on 11/14/16.
 */
@Subcomponent(modules = HomeModule.class)
public interface HomeComponent {

  void inject(HomeActivity homeActivity);

  void inject(WidgetsFragment widgetsFragment);

}
