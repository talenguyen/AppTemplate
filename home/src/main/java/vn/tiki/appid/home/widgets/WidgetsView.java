package vn.tiki.appid.home.widgets;

import java.util.List;

/**
 * Created by Giang Nguyen on 12/26/16.
 */
public interface WidgetsView {

  void showWidgets(List<Object> widgets);

  void showLoading();

  void showError();

}
