package vn.tiki.appid.common;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Giang Nguyen on 10/8/16.
 */
@Module
public class ActivityModule {

  private Context context;

  public ActivityModule(Context context) {
    this.context = context;
  }

  @Provides
  @Named("activity")
  public Context provideContext() {
    return context;
  }
}
