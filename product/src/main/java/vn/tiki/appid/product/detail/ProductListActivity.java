package vn.tiki.appid.product.detail;

import vn.tiki.appid.common.base.BaseActivity;
import vn.tiki.appid.product.ProductComponent;
import vn.tiki.appid.product.ProductModule;

/**
 * Created by Giang Nguyen on 11/15/16.
 */

public class ProductListActivity extends BaseActivity {
  private ProductComponent productComponent;

  private ProductComponent productComponent() {
    if (productComponent == null) {
      productComponent = superComponent().plus(new ProductModule());
    }
    return productComponent;
  }
}
