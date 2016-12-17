package vn.tiki.appid.common.base;

import android.support.v4.app.Fragment;
import vn.tiki.appid.common.di.Injector;

/**
 * Created by Giang Nguyen on 10/8/16.
 */

public class BaseFragment extends Fragment {

  private MvpPresenter<?> presenter;

  protected void takePresenter(MvpPresenter<?> presenter) {
    this.presenter = presenter;
  }

  protected Injector injector() {
    if (getActivity() instanceof Injector) {
      return ((Injector) getActivity());
    }
    throw new IllegalStateException("host activity must implement " + Injector.class.getName());
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (presenter != null) {
      presenter.detachView();
    }
  }
}
