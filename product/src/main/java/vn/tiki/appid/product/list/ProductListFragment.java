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
import vn.tiki.appid.common.pattern.list.ListPresenter;
import vn.tiki.appid.common.pattern.list.ListView;
import vn.tiki.appid.common.util.ImageLoader;
import vn.tiki.appid.common.viewholder.LoadMoreViewHolder;
import vn.tiki.appid.common.viewholder.RetryViewHolder;
import vn.tiki.appid.common.widget.InfiniteScrollListener;
import vn.tiki.appid.common.widget.SingleVisibleChildFrameLayout;
import vn.tiki.appid.data.entity.LoadingItem;
import vn.tiki.appid.data.entity.Product;
import vn.tiki.appid.data.entity.RetryItem;
import vn.tiki.appid.data.exception.NetworkException;
import vn.tiki.noadapter.AbsViewHolder;
import vn.tiki.noadapter.OnlyAdapter;
import vn.tiki.noadapter.TypeDeterminer;
import vn.tiki.noadapter.ViewHolderSelector;

/**
 * Created by Giang Nguyen on 12/31/16.
 */

public class ProductListFragment extends BaseFragment implements ListView {

  @BindView(R2.id.rvList) RecyclerView rvList;
  @BindView(R2.id.vRootView) SingleVisibleChildFrameLayout vRootView;
  @Inject ListPresenter<Product> presenter;
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
    presenter.getItems();
  }

  @Override public void showLoading() {
    vRootView.show(R.id.vLoading);
  }

  @Override public void showError(Throwable error) {
    if (error instanceof NetworkException) {
      vRootView.show(R.id.vErrorNetwork);
    } else {
      vRootView.show(R.id.vError);
    }
  }

  @Override public void showItems(List<Object> products) {
    vRootView.show(R.id.rvList);
    adapter.setItems(products);
  }

  private void setupProductListView() {
    final LinearLayoutManager layoutManager = new LinearLayoutManager(
        getContext(),
        LinearLayoutManager.VERTICAL,
        false);
    rvList.setLayoutManager(layoutManager);

    rvList.addItemDecoration(new DividerItemDecoration(
        getContext(),
        LinearLayoutManager.VERTICAL));

    rvList.setHasFixedSize(true);

    rvList.addOnScrollListener(new InfiniteScrollListener(layoutManager, 3, new Runnable() {
      @Override public void run() {
        presenter.getMoreItems();
      }
    }));

    adapter = productListAdapter();
    rvList.setAdapter(adapter);
  }

  private OnlyAdapter productListAdapter() {
    return new OnlyAdapter.Builder()
        .typeDeterminer(new TypeDeterminer() {
          @Override public int typeOf(Object item) {
            if (item instanceof LoadingItem) {
              return 1;
            } else if (item instanceof RetryItem) {
              return 2;
            }
            return 0;
          }
        })
        .viewHolderSelector(new ViewHolderSelector() {
          @Override public AbsViewHolder viewHolderForType(ViewGroup parent, int type) {
            switch (type) {
              case 1:
                return LoadMoreViewHolder.create(parent);
              case 2:
                return RetryViewHolder.create(parent);
              default:
                return ProductListItemViewHolder.create(parent, imageLoader);
            }
          }
        })
        .build();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (unbinder != null) {
      unbinder.unbind();
    }
  }
}