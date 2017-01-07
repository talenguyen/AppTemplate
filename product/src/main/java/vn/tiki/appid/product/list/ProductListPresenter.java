package vn.tiki.appid.product.list;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
        .map(new Function<List<Product>, List<Object>>() {
          @Override public List<Object> apply(List<Product> products) throws Exception {
            cache(products);
            return listOf(
                spread(products),
                new Object[] {
                    loadingItem()
                }
            );
          }
        })
        .subscribeOn(workerScheduler)
        .observeOn(uiScheduler)
        .subscribe(new Consumer<List<Object>>() {
          @Override public void accept(List<Object> products) throws Exception {
            increasePage();
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

  void loadMoreProducts() {
    final Single<List<Product>> loadProducts = Single.zip(
        Single.just(products),
        model.products(nextPage),
        new BiFunction<List<Product>, List<Product>, List<Product>>() {
          @Override public List<Product> apply(List<Product> products, List<Product> products2)
              throws Exception {
            return Lists.merged(
                products,
                products2
            );
          }
        }).doOnEvent(new BiConsumer<List<Product>, Throwable>() {
      @Override public void accept(List<Product> products, Throwable throwable) throws Exception {
        if (products != null && !products.isEmpty()) {

        }
      }
    });

    autoDispose(loadProducts
        .subscribeOn(workerScheduler)
        .observeOn(uiScheduler)
        .map(new Function<List<Product>, List<Object>>() {
          @Override public List<Object> apply(List<Product> products) throws Exception {
            cache(products);
            return listOf(
                spread(products),
                new Object[] {
                    loadingItem()
                }
            );
          }
        })
        .subscribe(new Consumer<List<Object>>() {
          @Override public void accept(List<Object> products) throws Exception {
            increasePage();
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
            productListView.showProducts(
                listOf(
                    spread(products),
                    new Object[] {
                        retryItem()
                    }
                )
            );
          }
        }));
  }

  private void increasePage() {
    nextPage = nextPage.next();
  }

  private void cache(List<Product> products) {
    this.products = products;
  }
}
