package vn.tiki.appid;

import dagger.Subcomponent;
import vn.tiki.appid.common.di.UserScope;

/**
 * Created by Giang Nguyen on 10/8/16.
 */
@UserScope
@Subcomponent(modules = UserModule.class)
interface UserComponent {
  //
  //ProductComponent plus(ProductModule productModule);
  //
  //CheckoutComponent plus(CheckoutModule productModule);
}
