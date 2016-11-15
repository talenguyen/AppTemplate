package vn.tiki.appid;

import vn.tiki.appid.checkout.CheckoutComponent;
import vn.tiki.appid.checkout.CheckoutModule;
import vn.tiki.appid.common.di.UserScope;
import vn.tiki.appid.product.ProductComponent;
import vn.tiki.appid.product.ProductModule;
import dagger.Subcomponent;

/**
 * Created by Giang Nguyen on 10/8/16.
 */
@UserScope
@Subcomponent(modules = UserModule.class)
interface UserComponent {

  ProductComponent plus(ProductModule productModule);

  CheckoutComponent plus(CheckoutModule productModule);
}
