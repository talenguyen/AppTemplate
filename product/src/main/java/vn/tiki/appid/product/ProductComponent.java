package vn.tiki.appid.product;

import dagger.Subcomponent;
import vn.tiki.appid.product.list.ProductListFragment;

/**
 * Created by Giang Nguyen on 11/14/16.
 */
@Subcomponent(modules = ProductModule.class)
public interface ProductComponent {

  void inject(ProductListFragment productListFragment);

}
