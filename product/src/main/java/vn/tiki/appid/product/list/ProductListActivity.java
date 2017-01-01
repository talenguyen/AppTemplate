package vn.tiki.appid.product.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.tiki.appid.product.R;
import vn.tiki.appid.common.base.BaseActivity;
import vn.tiki.appid.common.di.Injector;
import vn.tiki.appid.product.ProductComponent;
import vn.tiki.appid.product.ProductModule;

/**
 * Created by Giang Nguyen on 11/15/16.
 */

public class ProductListActivity extends BaseActivity implements Injector {

  private ProductComponent productComponent;

  private ProductComponent productComponent() {
    if (productComponent == null) {
      productComponent = superComponent().plus(new ProductModule());
    }
    return productComponent;
  }

  @Override public void inject(Object object) {
    if (object instanceof ProductListFragment) {
      productComponent().inject((ProductListFragment) object);
    }
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_product_list);

    if (savedInstanceState == null) {
      getSupportFragmentManager()
          .beginTransaction()
          .replace(R.id.fragmentContainer, new ProductListFragment())
          .commit();
    }
  }
}
