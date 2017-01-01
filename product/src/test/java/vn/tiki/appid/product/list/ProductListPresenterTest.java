package vn.tiki.appid.product.list;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import vn.tiki.appid.data.entity.Product;
import vn.tiki.appid.data.exception.NetworkException;
import vn.tiki.appid.data.model.ProductModel;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by Giang Nguyen on 1/1/17.
 */
public class ProductListPresenterTest {

  private ProductListView productListViewMocked;
  private ProductModel productModelMocked;
  private ProductListPresenter presenter;

  @Before
  public void setUp() throws Exception {
    productModelMocked = mock(ProductModel.class);
    productListViewMocked = mock(ProductListView.class);

    presenter = new ProductListPresenter(
        productModelMocked,
        Schedulers.trampoline(),
        Schedulers.trampoline());
  }

  @Test
  public void shouldShowLoadingWhenLoadProduct() throws Exception {
    when(productModelMocked.products()).thenReturn(Single.just(Collections.<Product>emptyList()));

    presenter.attachView(productListViewMocked);
    presenter.loadProducts();

    verify(productListViewMocked).showLoading();
  }

  @Test
  public void shouldNotShowLoadingAfterViewDetached() throws Exception {
    when(productModelMocked.products()).thenReturn(Single.just(Collections.<Product>emptyList()));

    presenter.attachView(productListViewMocked);
    presenter.detachView();
    presenter.loadProducts();

    verifyZeroInteractions(productListViewMocked);
  }

  @Test
  public void shouldShowErrorWhenErrorOccurred() throws Exception {
    when(productModelMocked.products())
        .thenReturn(Single.<List<Product>>error(new RuntimeException()));

    presenter.attachView(productListViewMocked);
    presenter.loadProducts();

    verify(productListViewMocked).showError();
  }

  @Test
  public void shouldShowNetworkErrorForNetworkException() throws Exception {
    when(productModelMocked.products())
        .thenReturn(Single.<List<Product>>error(new NetworkException()));

    presenter.attachView(productListViewMocked);
    presenter.loadProducts();

    verify(productListViewMocked).showNetworkError();
  }

  @Test
  public void shouldNotShowErrorAfterViewDetached() throws Exception {
    when(productModelMocked.products())
        .thenReturn(Single.<List<Product>>error(new RuntimeException()));

    presenter.attachView(productListViewMocked);
    presenter.detachView();
    presenter.loadProducts();

    verifyZeroInteractions(productListViewMocked);
  }

  @Test
  public void shouldShowProducts() throws Exception {
    final List<Product> products = asList(
        mock(Product.class),
        mock(Product.class)
    );

    when(productModelMocked.products())
        .thenReturn(Single.just(products));

    presenter.attachView(productListViewMocked);
    presenter.loadProducts();

    verify(productListViewMocked).showProducts(products);

  }
}