package vn.tiki.appid.common.base;

import android.support.v7.app.AppCompatActivity;
import vn.tiki.appid.common.TheApp;
import vn.tiki.appid.common.di.Injector;
import vn.tiki.appid.common.di.SuperComponent;

/**
 * Created by Giang Nguyen on 10/8/16.
 */

public class BaseActivity extends AppCompatActivity {

  protected Injector injector() {
    return TheApp.get(this);
  }

  protected SuperComponent superComponent() {
    return TheApp.get(this);
  }

}
