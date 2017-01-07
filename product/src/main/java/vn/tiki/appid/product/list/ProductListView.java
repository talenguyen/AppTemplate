package vn.tiki.appid.product.list;

import java.util.List;

/**
 * Created by Giang Nguyen on 12/31/16.
 */

public interface ProductListView {

  void showLoading();

  void showNetworkError();

  void showProducts(List<Object> products);

  void showError();
}
