package vn.tiki.appid.product.list;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import java.util.List;
import vn.tiki.appid.common.base.MvpPresenter;
import vn.tiki.appid.common.util.Lists;
import vn.tiki.appid.data.entity.Page;
import vn.tiki.appid.data.entity.Product;
import vn.tiki.appid.data.exception.NetworkException;
import vn.tiki.appid.data.model.ProductModel;

import static vn.tiki.appid.common.util.Lists.listOf;
import static vn.tiki.appid.common.util.Lists.spread;
import static vn.tiki.appid.data.entity.LoadingItem.loadingItem;
import static vn.tiki.appid.data.entity.RetryItem.retryItem;

/**
 * Created by Giang Nguyen on 12/31/16.
 */

public class ProductListPresenter extends MvpPresenter<ProductListView> {
  private final ProductModel model;
  private final Scheduler workerScheduler;
  private final Scheduler uiScheduler;
  private Page nextPage = Page.firstPage();
  private List<Product> products;

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

    autoDispose(model.products(nextPage)
        .map(products -> {
          cache(products);
          return listOf(
              spread(products),
              new Object[] {
                  loadingItem()
              }
          );
        })
        .subscribeOn(workerScheduler)
        .observeOn(uiScheduler)
        .subscribe(products -> {
          increasePage();
          final ProductListView productListView = getView();
          if (productListView == null) {
            return;
          }
          productListView.showProducts(products);
        }, throwable -> {
          final ProductListView productListView = getView();
          if (productListView == null) {
            return;
          }
          if (throwable instanceof NetworkException) {
            productListView.showNetworkError();
          } else {
            productListView.showError();
          }
        }));
  }

  void loadMoreProducts() {
    final Single<List<Product>> loadProducts = Single.zip(
        Single.just(products),
        model.products(nextPage),
        (products1, products2) -> Lists.merged(
            products1,
            products2
        )).doOnEvent((products12, throwable) -> {
          if (products12 != null && !products12.isEmpty()) {

          }
        });

    autoDispose(loadProducts
        .subscribeOn(workerScheduler)
        .observeOn(uiScheduler)
        .map(products -> {
          cache(products);
          return listOf(
              spread(products),
              new Object[] {
                  loadingItem()
              }
          );
        })
        .subscribe(products -> {
          increasePage();
          final ProductListView productListView = getView();
          if (productListView == null) {
            return;
          }
          productListView.showProducts(products);
        }, throwable -> {
          final ProductListView productListView = getView();
          if (productListView == null) {
            return;
          }
          productListView.showProducts(
              listOf(
                  spread(products),
                  new Object[] {
                      retryItem()
                  }
              )
          );
        }));
  }

  private void increasePage() {
    nextPage = nextPage.next();
  }

  private void cache(List<Product> products) {
    this.products = products;
  }
}
