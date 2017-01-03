package vn.tiki.appid.developer_settings;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Giang Nguyen on 1/3/17.
 */

class LeakCanaryProxyImpl implements LeakCanaryProxy {

  private final Application application;

  LeakCanaryProxyImpl(Application application) {
    this.application = application;
  }

  @Override public void init() {
    if (!LeakCanary.isInAnalyzerProcess(application)) {
      LeakCanary.install(application);
    }
  }
}
