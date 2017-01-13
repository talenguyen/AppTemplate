package vn.tiki.appid.common.pattern.list;

import android.support.annotation.NonNull;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import ix.Ix;
import ix.IxFunction;
import ix.IxPredicate;
import java.util.List;
import java.util.NoSuchElementException;
import vn.tiki.appid.common.base.MvpPresenter;
import vn.tiki.appid.data.entity.LoadingItem;
import vn.tiki.appid.data.entity.Page;
import vn.tiki.appid.data.entity.RetryItem;

import static ix.Ix.concat;
import static ix.Ix.from;
import static ix.Ix.just;
import static vn.tiki.appid.data.entity.LoadingItem.loadingItem;
import static vn.tiki.appid.data.entity.RetryItem.retryItem;

/**
 * Created by Giang Nguyen on 12/31/16.
 */

public class ListPresenter<T> extends MvpPresenter<ListView> {
  private final GetListInteractor<T> interactor;
  private final Scheduler workerScheduler;
  private final Scheduler uiScheduler;
  private Page nextPage;
  private Ix<T> itemIx;

  public ListPresenter(GetListInteractor<T> interactor, Page firstPage, Scheduler workerScheduler,
      Scheduler uiScheduler) {
    this.interactor = interactor;
    this.nextPage = firstPage;
    this.workerScheduler = workerScheduler;
    this.uiScheduler = uiScheduler;
  }

  public void getItems() {
    final ListView view = getView();
    if (view == null) {
      return;
    }
    view.showLoading();

    autoDispose(interactor.items(nextPage)
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
            final ListView listView = getView();
            if (listView == null) {
              return;
            }
            listView.showError(throwable);
          }
        }));
  }

  public void getMoreItems() {
    final Single<List<T>> loadProducts = Single.zip(
        Single.just(itemIx.toList()),
        interactor.items(nextPage),
        new BiFunction<List<T>, List<T>, List<T>>() {
          @Override public List<T> apply(List<T> items1, List<T> items2)
              throws Exception {
            return concat(
                from(items1),
                from(items2)
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
            final List<Object> items;
            if (throwable instanceof NoSuchElementException) {
              items = itemIx
                  .cast(Object.class)
                  .toList();
            } else {
              items = concat(
                  itemIx,
                  just(retryItem())
              ).toList();
            }
            showItemsInView(
                items
            );
          }
        }));
  }

  @SuppressWarnings("unchecked")
  @NonNull private IxFunction<Object, T> castToT() {
    return new IxFunction<Object, T>() {
      @Override public T apply(Object o) {
        return (T) o;
      }
    };
  }

  @NonNull private Function<List<T>, List<Object>> loadingItemAppending() {
    return new Function<List<T>, List<Object>>() {
      @Override public List<Object> apply(List<T> items) throws Exception {
        return concat(
            from(items),
            just(loadingItem())
        ).toList();
      }
    };
  }

  private IxPredicate<Object> isNotRetry() {
    return new IxPredicate<Object>() {
      @Override public boolean test(Object t) {
        return !(t instanceof RetryItem);
      }
    };
  }

  private IxPredicate<Object> isNotLoading() {
    return new IxPredicate<Object>() {
      @Override public boolean test(Object t) {
        return !(t instanceof LoadingItem);
      }
    };
  }

  private void showItemsInView(List<Object> items) {
    final ListView listView = getView();
    if (listView == null) {
      return;
    }
    listView.showItems(items);
  }

  private void increasePage() {
    nextPage = nextPage.next();
  }

  private void cache(List<Object> items) {
    itemIx = from(items)
        .filter(
            isNotLoading()
        )
        .filter(
            isNotRetry()
        )
        .map(
            castToT()
        );
  }
}
