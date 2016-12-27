package vn.tiki.appid.home.widgets;

import com.tiki.appid.home.R;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import vn.tiki.appid.common.base.MvpPresenter;
import vn.tiki.appid.home.entity.CategoryListWidget;

import static java.util.concurrent.TimeUnit.SECONDS;
import static vn.tiki.appid.home.entity.Category.category;

/**
 * Created by Giang Nguyen on 12/26/16.
 */

public class WidgetsPresenter extends MvpPresenter<WidgetsView> {

  void loadWidgets() {
    final WidgetsView view = getView();
    if (view == null) {
      return;
    }
    view.showLoading();
    autoDispose(widgets()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Object>>() {
          @Override public void accept(List<Object> objects) throws Exception {
            final WidgetsView view = getView();
            if (view != null) {
              view.showWidgets(objects);
            }
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(Throwable throwable) throws Exception {
            final WidgetsView view = getView();
            if (view != null) {
              view.showError();
            }
          }
        }));
  }

  private Observable<List<Object>> widgets() {
    return Observable.just(
        Collections.<Object>singletonList(
            CategoryListWidget.make(Arrays.asList(
                category().id("").name("Offers").icon(R.drawable.ic_tag_white_24dp).make(),
                category().id("")
                    .name("Electronics")
                    .icon(R.drawable.ic_electronic_white_24dp)
                    .make(),
                category().id("").name("Books").icon(R.drawable.ic_book_white_24dp).make(),
                category().id("").name("Home").icon(R.drawable.ic_book_white_24dp).make(),
                category().id("").name("More").icon(R.drawable.ic_more_white_24dp).make()
            ))
        )
    ).delay(2, SECONDS);
  }
}
