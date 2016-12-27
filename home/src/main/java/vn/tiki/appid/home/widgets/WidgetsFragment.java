package vn.tiki.appid.home.widgets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tiki.appid.home.R;
import com.tiki.appid.home.R2;
import java.util.List;
import javax.inject.Inject;
import vn.tiki.appid.common.base.BaseFragment;
import vn.tiki.appid.common.widget.SingleVisibleChildFrameLayout;
import vn.tiki.noadapter.AbsViewHolder;
import vn.tiki.noadapter.OnlyAdapter;
import vn.tiki.noadapter.ViewHolderSelector;

/**
 * Created by Giang Nguyen on 12/27/16.
 */

public class WidgetsFragment extends BaseFragment implements WidgetsView {

  @Inject WidgetsPresenter presenter;
  @BindView(R2.id.rvList) RecyclerView rvList;
  @BindView(R2.id.vRootView) SingleVisibleChildFrameLayout vRootView;

  private Unbinder unbinder;
  private OnlyAdapter adapter;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_widgets, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);
    injector().inject(this);

    setupWidgetsList();

    presenter.attachView(this);
    takePresenter(presenter);

    presenter.loadWidgets();
  }

  private void setupWidgetsList() {
    rvList.setLayoutManager(new LinearLayoutManager(
        getContext(),
        LinearLayoutManager.VERTICAL,
        false));

    adapter = new OnlyAdapter.Builder()
        .viewHolderSelector(new ViewHolderSelector() {
          @Override public AbsViewHolder viewHolderForType(ViewGroup parent, int type) {
            return CategoryHorizontalListViewHolder.create(parent);
          }
        })
        .build();

    rvList.setAdapter(adapter);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (unbinder != null) {
      unbinder.unbind();
    }
  }

  @Override public void showWidgets(List<Object> widgets) {
    vRootView.show(R.id.rvList);
    adapter.setItems(widgets);
  }

  @Override public void showLoading() {
    vRootView.show(R.id.vLoading);
  }

  @Override public void showError() {
    vRootView.show(R.id.vError);
  }
}
