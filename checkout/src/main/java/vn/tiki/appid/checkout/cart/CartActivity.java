package vn.tiki.appid.checkout.cart;

import javax.inject.Inject;
import vn.tiki.appid.checkout.CheckoutComponent;
import vn.tiki.appid.checkout.CheckoutModule;
import vn.tiki.appid.common.base.BaseActivity;
import vn.tiki.appid.data.entity.User;

/**
 * Created by Giang Nguyen on 11/15/16.
 */

public class CartActivity extends BaseActivity {

  @Inject User user;

  private CheckoutComponent checkoutComponent;

  private CheckoutComponent checkoutComponent() {
    if (checkoutComponent == null) {
      checkoutComponent = superComponent().plus(new CheckoutModule());
    }
    return checkoutComponent;
  }
}
