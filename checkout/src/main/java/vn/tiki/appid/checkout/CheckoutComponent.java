package vn.tiki.appid.checkout;

import vn.tiki.appid.checkout.cart.CartActivity;
import dagger.Subcomponent;

/**
 * Created by Giang Nguyen on 11/14/16.
 */
@Subcomponent(modules = CheckoutModule.class)
public interface CheckoutComponent {

  void inject(CartActivity cartActivity);
}
