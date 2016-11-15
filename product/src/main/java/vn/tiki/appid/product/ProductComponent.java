package vn.tiki.appid.product;

import vn.tiki.appid.product.detail.ProductListActivity;
import vn.tiki.appid.product.list.ProductDetailActivity;
import dagger.Subcomponent;

/**
 * Created by Giang Nguyen on 11/14/16.
 */
@Subcomponent(modules = ProductModule.class)
public interface ProductComponent {

  void inject(ProductListActivity productListActivity);

  void inject(ProductDetailActivity productDetailActivity);

}
