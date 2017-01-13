package vn.tiki.appid.common.pattern.list;

import java.util.List;

/**
 * Created by Giang Nguyen on 12/31/16.
 */

public interface ListView {

  void showLoading();

  void showError(Throwable error);

  void showItems(List<Object> products);
}
