package vn.tiki.appid.common.base;

import android.support.v4.app.Fragment;
import vn.tiki.appid.common.di.Injector;

/**
 * Created by Giang Nguyen on 10/8/16.
 */

public class BaseFragment extends Fragment {

  protected Injector injector() {
    if (getActivity() instanceof Injector) {
      return ((Injector) getActivity());
    }
    throw new IllegalStateException("host activity must implement " + Injector.class.getName());
  }
}
