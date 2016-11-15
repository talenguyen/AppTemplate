package vn.tiki.appid.common.base;

import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;

/**
 * Created by Giang Nguyen on 11/14/16.
 */

public abstract class MvpPresenter<MvpView> {

  private WeakReference<MvpView> viewRef;

  @Nullable public MvpView getView() {
    return viewRef == null ? null : viewRef.get();
  }

  public void attachView(MvpView view) {
    this.viewRef = new WeakReference<MvpView>(view);
  }

  public void detachView() {
    if (viewRef != null) {
      viewRef.clear();
    }
  }
}
