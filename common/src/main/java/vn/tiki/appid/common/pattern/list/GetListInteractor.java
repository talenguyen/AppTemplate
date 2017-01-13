package vn.tiki.appid.common.pattern.list;

import io.reactivex.Single;
import java.util.List;
import vn.tiki.appid.data.entity.Page;

/**
 * Created by Giang Nguyen on 1/11/17.
 */

public interface GetListInteractor<T> {

  Single<List<T>> items(Page page);
}
