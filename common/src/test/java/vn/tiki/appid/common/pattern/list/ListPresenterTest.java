package vn.tiki.appid.common.pattern.list;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import vn.tiki.appid.data.entity.Page;

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
public class ListPresenterTest {

  private static final Page FIRST_PAGE = Page.firstPage();
  private static final Page SECOND_PAGE = FIRST_PAGE.next();

  private static final List<String> FIRST_PAGE_ITEMS_MOCKED = asList(
      "Item 1",
      "Item 2"
  );
  private static final List<String> SECOND_PAGE_ITEMS_MOCKED = asList(
      "Item 3",
      "Item 4"
  );

  private ListView listViewMocked;
  private GetListInteractor<String> getListInteractor;
  private ListPresenter<String> presenter;

  @Before
  public void setUp() throws Exception {
    getListInteractor = mock(GetListInteractor.class);
    listViewMocked = mock(ListView.class);

    presenter = new ListPresenter<>(
        getListInteractor,
        FIRST_PAGE,
        Schedulers.trampoline(),
        Schedulers.trampoline());

    givenProductWhenLoad(FIRST_PAGE, FIRST_PAGE_ITEMS_MOCKED);
    givenProductWhenLoad(SECOND_PAGE, SECOND_PAGE_ITEMS_MOCKED);

    presenter.attachView(listViewMocked);
  }

  @Test
  public void should_not_interact_after_view_detached() throws Exception {
    presenter.detachView();
    presenter.getItems();

    verifyZeroInteractions(listViewMocked);
  }

  @Test
  public void should_show_loading_when_load() throws Exception {
    presenter.getItems();

    verify(listViewMocked).showLoading();
  }

  @Test
  public void should_show_error_when_error_occurred_during_load() throws Exception {
    final RuntimeException error = new RuntimeException();
    givenErrorWhenLoad(FIRST_PAGE, error);

    presenter.getItems();

    verify(listViewMocked).showError(eq(error));
  }

  @Test
  public void should_show_products_when_load_success() throws Exception {
    presenter.getItems();

    verify(listViewMocked).showItems(
        concat(
            from(FIRST_PAGE_ITEMS_MOCKED),
            just(loadingItem())
        ).toList()
    );
  }

  @Test
  public void should_not_show_loading_when_load_more() throws Exception {
    presenter.getItems();
    verify(listViewMocked).showLoading();

    presenter.getMoreItems();
    verify(listViewMocked, times(1)).showLoading();
  }

  @Test
  public void should_show_retry_when_error_occur_during_load_more() throws Exception {
    givenProductWhenLoad(FIRST_PAGE, FIRST_PAGE_ITEMS_MOCKED);
    givenErrorWhenLoad(SECOND_PAGE, new RuntimeException());

    presenter.getItems();
    presenter.getMoreItems();

    verify(listViewMocked).showItems(
        concat(
            from(FIRST_PAGE_ITEMS_MOCKED),
            just(retryItem())
        ).toList()
    );
  }

  @Test
  public void should_show_appended_product_when_success_during_load_more() throws Exception {
    presenter.getItems();
    presenter.getMoreItems();

    verify(listViewMocked).showItems(
        concat(
            from(FIRST_PAGE_ITEMS_MOCKED),
            from(SECOND_PAGE_ITEMS_MOCKED),
            just(loadingItem())
        ).toList()
    );
  }

  private void givenProductWhenLoad(Page page, List<String> products) {
    when(getListInteractor.items(eq(page)))
        .thenReturn(Single.just(products));
  }

  private void givenErrorWhenLoad(Page page, Throwable error) {
    when(getListInteractor.items(eq(page)))
        .thenReturn(Single.<List<String>>error(error));
  }
}