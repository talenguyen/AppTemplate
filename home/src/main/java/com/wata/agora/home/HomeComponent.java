package com.tiki.appid.home;

import dagger.Subcomponent;

/**
 * Created by Giang Nguyen on 11/14/16.
 */
@Subcomponent(modules = HomeModule.class)
public interface HomeComponent {

  void inject(HomeActivity homeActivity);
}
