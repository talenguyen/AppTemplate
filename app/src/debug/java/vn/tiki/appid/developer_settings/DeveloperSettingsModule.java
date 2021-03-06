package vn.tiki.appid.developer_settings;

import android.app.Application;
import android.support.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Giang Nguyen on 1/3/17.
 */
@Module
public class DeveloperSettingsModule {

  @Provides
  @NonNull
  @Singleton LeakCanaryProxy provideLeakCanaryProxy(@NonNull Application application) {
    return new LeakCanaryProxyImpl(application);
  }
}
