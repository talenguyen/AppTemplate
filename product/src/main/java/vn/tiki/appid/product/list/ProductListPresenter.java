package vn.tiki.appid.product.list;

import android.support.annotation.NonNull;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import ix.Ix;
import ix.IxPredicate;
import java.util.List;
import java.util.NoSuchElementException;
import vn.tiki.appid.common.base.MvpPresenter;
import vn.tiki.appid.data.entity.Page;
import vn.tiki.appid.data.entity.Product;
import vn.tiki.appid.data.exception.NetworkException;
import vn.tiki.appid.data.model.ProductModel;

import static ix.Ix.concat;
import static ix.Ix.from;
import static ix.Ix.just;
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
  private Ix<Product> productIx;

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
        .map(
            loadingItemAppending()
        )
        .subscribeOn(workerScheduler)
        .observeOn(uiScheduler)
        .subscribe(new Consumer<List<Object>>() {
          @Override public void accept(List<Object> items) throws Exception {
            cache(items);
            increasePage();
            showItemsInView(items);
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
        Single.just(productIx.toList()),
        model.products(nextPage),
        new BiFunction<List<Product>, List<Product>, List<Product>>() {
          @Override public List<Product> apply(List<Product> products, List<Product> products2)
              throws Exception {
            return concat(
                from(products),
                from(products2)
            ).toList();
          }
        });

    autoDispose(loadProducts
        .subscribeOn(workerScheduler)
        .observeOn(uiScheduler)
        .map(
            loadingItemAppending()
        )
        .subscribe(new Consumer<List<Object>>() {
          @Override public void accept(List<Object> items) throws Exception {
            cache(items);
            increasePage();
            showItemsInView(items);
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(Throwable throwable) throws Exception {
            if (throwable instanceof NoSuchElementException) {
              showItemsInView(
                  productIx
                      .cast(Object.class)
                      .toList()
              );
            } else {
              showItemsInView(
                  concat(
                      productIx,
                      just(retryItem())
                  ).toList()
              );
            }
          }
        }));
  }

  private void increasePage() {
    nextPage = nextPage.next();
  }

  private void cache(List<Object> items) {
    productIx = from(items)
        .filter(
            isInstanceOfProduct()
        )
        .cast(Product.class);
  }

  private void showItemsInView(List<Object> items) {
    final ProductListView productListView = getView();
    if (productListView == null) {
      return;
    }
    productListView.showProducts(items);
  }

  @NonNull private Function<List<Product>, List<Object>> loadingItemAppending() {
    return new Function<List<Product>, List<Object>>() {
      @Override public List<Object> apply(List<Product> products) throws Exception {
        return concat(
            from(products),
            just(loadingItem())
        ).toList();
      }
    };
  }

  @NonNull private IxPredicate<Object> isInstanceOfProduct() {
    return new IxPredicate<Object>() {
      @Override public boolean test(Object o) {
        return o instanceof Product;
      }
    };
  }
}
