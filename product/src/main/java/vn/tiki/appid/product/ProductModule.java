package vn.tiki.appid.product;

import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import vn.tiki.appid.data.model.ProductModel;
import vn.tiki.appid.product.list.ProductListPresenter;

/**
 * Created by Giang Nguyen on 11/14/16.
 */
@Module
public class ProductModule {

  @Provides ProductListPresenter provideProductListPresenter(ProductModel model) {
    return new ProductListPresenter(model, Schedulers.io(), AndroidSchedulers.mainThread());
  }
}
