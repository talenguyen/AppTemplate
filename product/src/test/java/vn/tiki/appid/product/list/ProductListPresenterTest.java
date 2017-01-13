package vn.tiki.appid.product.list;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import vn.tiki.appid.data.entity.Page;
import vn.tiki.appid.data.entity.Product;
import vn.tiki.appid.data.exception.NetworkException;
import vn.tiki.appid.data.model.ProductModel;

import static ix.Ix.concat;
import static ix.Ix.from;
import static ix.Ix.just;
import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static vn.tiki.appid.data.entity.LoadingItem.loadingItem;
import static vn.tiki.appid.data.entity.RetryItem.retryItem;

/**
 * Created by Giang Nguyen on 1/1/17.
 */
public class ProductListPresenterTest {

  private static final Page FIRST_PAGE = Page.firstPage();
  private static final Page SECOND_PAGE = FIRST_PAGE.next();

  private static final List<Product> FIRST_PAGE_PRODUCTS_MOCKED = asList(
      mock(Product.class),
      mock(Product.class)
  );
  private static final List<Product> SECOND_PAGE_PRODUCTS_MOCKED = asList(
      mock(Product.class),
      mock(Product.class)
  );

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

    givenProductWhenLoad(FIRST_PAGE, FIRST_PAGE_PRODUCTS_MOCKED);
    givenProductWhenLoad(SECOND_PAGE, SECOND_PAGE_PRODUCTS_MOCKED);

    presenter.attachView(productListViewMocked);
  }

  @Test
  public void should_not_interact_after_view_detached() throws Exception {
    presenter.detachView();
    presenter.loadProducts();

    verifyZeroInteractions(productListViewMocked);
  }

  @Test
  public void should_show_loading_when_load() throws Exception {
    presenter.loadProducts();

    verify(productListViewMocked).showLoading();
  }

  @Test
  public void should_show_error_when_error_occurred_during_load() throws Exception {
    givenErrorWhenLoad(FIRST_PAGE);

    presenter.loadProducts();

    verify(productListViewMocked).showError();
  }

  @Test
  public void should_show_network_error_when_network_error_occurred_during_load() throws Exception {
    givenErrorNetworkWhenLoad(FIRST_PAGE);

    presenter.loadProducts();

    verify(productListViewMocked).showNetworkError();
  }

  @Test
  public void should_show_products_when_load_success() throws Exception {
    presenter.loadProducts();

    verify(productListViewMocked).showProducts(
        concat(
            from(FIRST_PAGE_PRODUCTS_MOCKED),
            just(loadingItem())
        ).toList()
    );
  }

  @Test
  public void should_not_show_loading_when_load_more() throws Exception {
    presenter.loadProducts();
    verify(productListViewMocked).showLoading();

    presenter.loadMoreProducts();
    verify(productListViewMocked, times(1)).showLoading();
  }

  @Test
  public void should_show_retry_when_error_occur_during_load_more() throws Exception {
    givenProductWhenLoad(FIRST_PAGE, FIRST_PAGE_PRODUCTS_MOCKED);
    givenErrorWhenLoad(SECOND_PAGE);

    presenter.loadProducts();
    presenter.loadMoreProducts();

    verify(productListViewMocked).showProducts(
        concat(
            from(FIRST_PAGE_PRODUCTS_MOCKED),
            just(retryItem())
        ).toList()
    );
  }

  @Test
  public void should_show_appended_product_when_success_during_load_more() throws Exception {
    presenter.loadProducts();
    presenter.loadMoreProducts();

    verify(productListViewMocked).showProducts(
        concat(
            from(FIRST_PAGE_PRODUCTS_MOCKED),
            from(SECOND_PAGE_PRODUCTS_MOCKED),
            just(loadingItem())
        ).toList()
    );
  }

  private void givenProductWhenLoad(Page page, List<Product> products) {
    when(productModelMocked.products(eq(page)))
        .thenReturn(Single.just(products));
  }

  private void givenErrorWhenLoad(Page page) {
    when(productModelMocked.products(eq(page)))
        .thenReturn(Single.<List<Product>>error(new RuntimeException()));
  }

  private void givenErrorNetworkWhenLoad(Page page) {
    when(productModelMocked.products(eq(page)))
        .thenReturn(Single.<List<Product>>error(new NetworkException()));
  }
}