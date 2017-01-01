package vn.tiki.appid.product.list;

import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import java.util.List;
import vn.tiki.appid.common.base.MvpPresenter;
import vn.tiki.appid.data.entity.Product;
import vn.tiki.appid.data.exception.NetworkException;
import vn.tiki.appid.data.model.ProductModel;

/**
 * Created by Giang Nguyen on 12/31/16.
 */

public class ProductListPresenter extends MvpPresenter<ProductListView> {

  private final ProductModel model;
  private final Scheduler workerScheduler;
  private final Scheduler uiScheduler;

  public ProductListPresenter(ProductModel model, Scheduler workerScheduler,
      Scheduler uiScheduler) {
    this.model = model;
    this.workerScheduler = workerScheduler;
    this.uiScheduler = uiScheduler;
  }

  void loadProducts() {
    final ProductListView view = getView();
    if (view == null) {
      return;
    }
    view.showLoading();

    autoDispose(model.products()
        .subscribeOn(workerScheduler)
        .observeOn(uiScheduler)
        .subscribe(new Consumer<List<Product>>() {
          @Override public void accept(List<Product> products) throws Exception {
            final ProductListView productListView = getView();
            if (productListView == null) {
              return;
            }
            productListView.showProducts(products);
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(Throwable throwable) throws Exception {
            final ProductListView productListView = getView();
            if (productListView == null) {
              return;
            }
            if (throwable instanceof NetworkException) {
              productListView.showNetworkError();
            } else {
              productListView.showError();
            }
          }
        }));
  }
}
