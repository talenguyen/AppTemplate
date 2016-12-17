package vn.tiki.appid.home.detail;

import vn.tiki.appid.common.base.BaseActivity;
import vn.tiki.appid.home.ProductComponent;
import vn.tiki.appid.home.ProductModule;

/**
 * Created by Giang Nguyen on 11/15/16.
 */

public class ProductDetailActivity extends BaseActivity {
  private ProductComponent productComponent;

  private ProductComponent productComponent() {
    if (productComponent == null) {
      productComponent = superComponent().plus(new ProductModule());
    }
    return productComponent;
  }
}
