package vn.tiki.appid.common.base;

import android.support.annotation.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.lang.ref.WeakReference;

/**
 * Created by Giang Nguyen on 11/14/16.
 */

public abstract class MvpPresenter<MvpView> {

  private final CompositeDisposable disposables = new CompositeDisposable();

  private WeakReference<MvpView> viewRef;

  @Nullable public MvpView getView() {
    return viewRef == null ? null : viewRef.get();
  }

  public void attachView(MvpView view) {
    this.viewRef = new WeakReference<>(view);
  }

  protected void autoDispose(Disposable disposable) {
    disposables.add(disposable);
  }

  public void detachView() {
    if (viewRef != null) {
      viewRef.clear();
    }
    disposables.clear();
  }
}
