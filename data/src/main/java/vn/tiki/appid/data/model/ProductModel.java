package vn.tiki.appid.data.model;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import java.util.List;
import vn.tiki.appid.data.api.Api;
import vn.tiki.appid.data.api.ListResponse;
import vn.tiki.appid.data.entity.Page;
import vn.tiki.appid.data.entity.Product;

/**
 * Created by Giang Nguyen on 12/28/16.
 */

public class ProductModel {

  private final Api api;

  public ProductModel(Api api) {
    this.api = api;
  }

  public Single<List<Product>> products(Page page) {
    return api.getProducts("")
        .map(new Function<ListResponse<Product>, List<Product>>() {
          @Override public List<Product> apply(ListResponse<Product> productListResponse)
              throws Exception {
            return productListResponse.items();
          }
        });
  }
}
