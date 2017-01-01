package vn.tiki.appid.product.list;

import java.util.List;
import vn.tiki.appid.data.entity.Product;

/**
 * Created by Giang Nguyen on 12/31/16.
 */

public interface ProductListView {

  void showLoading();

  void showNetworkError();

  void showProducts(List<Product> products);

  void showError();
}
