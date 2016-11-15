package vn.tiki.appid.common;

import android.app.Application;
import android.content.Context;
import vn.tiki.appid.common.di.Injector;
import vn.tiki.appid.common.di.SuperComponent;
import vn.tiki.appid.data.entity.User;

public abstract class TheApp extends Application implements SuperComponent, Injector {

  public static TheApp get(Context context) {
    return (TheApp) context.getApplicationContext();
  }

  public abstract void setUser(User user);
}