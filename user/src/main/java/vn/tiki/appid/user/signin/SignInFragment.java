package vn.tiki.appid.user.signin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tiki.appid.user.R;
import javax.inject.Inject;
import vn.tiki.appid.common.base.BaseFragment;
import vn.tiki.appid.data.entity.User;
import vn.tiki.appid.user.LoginCallback;

/**
 * Created by Giang Nguyen on 11/14/16.
 */

public class SignInFragment extends BaseFragment implements SignInView {

  @Inject SignInPresenter presenter;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_sign_in, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    injector().inject(this);
    presenter.attachView(this);
  }

  @Override public void onDestroyView() {
    presenter.detachView();
    super.onDestroyView();
  }

  @Override public void showLoading() {

  }

  @Override public void showLoginError() {

  }

  @Override public void showSuccess(User user) {
    if (getActivity() instanceof LoginCallback) {
      ((LoginCallback) getActivity()).onSuccess(user);
    }
  }

  @Override public void showNetworkError() {

  }

  @Override public void showError() {

  }
}
