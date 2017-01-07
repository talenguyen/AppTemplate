package vn.tiki.appid.product.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tiki.appid.product.R;
import com.tiki.appid.product.R2;
import java.util.List;
import javax.inject.Inject;
import vn.tiki.appid.common.base.BaseFragment;
import vn.tiki.appid.common.util.ImageLoader;
import vn.tiki.appid.common.widget.SingleVisibleChildFrameLayout;
import vn.tiki.noadapter.OnlyAdapter;

/**
 * Created by Giang Nguyen on 12/31/16.
 */

public class ProductListFragment extends BaseFragment implements ProductListView {

  @BindView(R2.id.rvList) RecyclerView rvList;
  @BindView(R2.id.vRootView) SingleVisibleChildFrameLayout vRootView;
  @Inject ProductListPresenter presenter;
  @Inject ImageLoader imageLoader;
  private Unbinder unbinder;
  private OnlyAdapter adapter;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_product_list, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);
    injector().inject(this);
    takePresenter(presenter);

    setupProductListView();
    presenter.attachView(this);
    presenter.loadProducts();
  }

  @Override public void showLoading() {
    vRootView.show(R.id.vLoading);
  }

  @Override public void showNetworkError() {
    vRootView.show(R.id.vErrorNetwork);
  }

  @Override public void showProducts(List<Object> products) {
    vRootView.show(R.id.rvList);
    adapter.setItems(products);
  }

  @Override public void showError() {
    vRootView.show(R.id.vError);
  }

  private void setupProductListView() {
    rvList.setLayoutManager(new LinearLayoutManager(
        getContext(),
        LinearLayoutManager.VERTICAL,
        false));

    rvList.addItemDecoration(new DividerItemDecoration(
        getContext(),
        LinearLayoutManager.VERTICAL));

    rvList.setHasFixedSize(true);

    adapter = productListAdapter();

    rvList.setAdapter(adapter);
  }

  private OnlyAdapter productListAdapter() {
    return new OnlyAdapter.Builder()
        .viewHolderSelector((parent, type) -> ProductListItemViewHolder.create(parent, imageLoader))
        .build();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (unbinder != null) {
      unbinder.unbind();
    }
  }
}