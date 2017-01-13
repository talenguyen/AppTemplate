package vn.tiki.appid.product;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import vn.tiki.appid.common.pattern.list.GetListInteractor;
import vn.tiki.appid.common.pattern.list.ListPresenter;
import vn.tiki.appid.data.entity.Page;
import vn.tiki.appid.data.entity.Product;
import vn.tiki.appid.data.model.ProductModel;
import vn.tiki.appid.product.list.ProductListPresenter;

/**
 * Created by Giang Nguyen on 11/14/16.
 */
@Module
public class ProductModule {

  @Provides ListPresenter<Product> provideListPresenter(GetListInteractor<Product> interactor) {
    return new ListPresenter<>(
        interactor,
        Page.firstPage(),
        Schedulers.io(),
        AndroidSchedulers.mainThread());
  }

  @Provides ProductListPresenter provideProductListPresenter(ProductModel model) {
    return new ProductListPresenter(model, Schedulers.io(), AndroidSchedulers.mainThread());
  }

  @Provides GetListInteractor<Product> provideProductGetListItemInteractor(final ProductModel model) {
    return new GetListInteractor<Product>() {
      @Override public Single<List<Product>> items(Page page) {
        return model.products(page);
      }
    };
  }
}
