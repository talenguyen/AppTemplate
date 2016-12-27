package vn.tiki.appid.common.widget;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import java.util.Map;

/**
 * Created by Giang Nguyen on 12/27/16.
 */

public class SingleVisibleChildFrameLayout extends FrameLayout {

  private View showing;
  private Map<Integer, View> inflatedViews = new ArrayMap<>();

  public SingleVisibleChildFrameLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public void addView(View child, ViewGroup.LayoutParams params) {
    super.addView(child, params);
    cacheView(child);
  }

  @Override public void addView(View child) {
    super.addView(child);
    cacheView(child);
  }

  public void show(@IdRes int id) {
    View view;
    if (inflatedViews.containsKey(id)) {
      view = inflatedViews.get(id);
    } else {
      view = findViewById(id);
      if (view instanceof ViewStub) {
        view = ((ViewStub) view).inflate();
      }
      inflatedViews.put(id, view);
    }
    if (view.equals(showing)) {
      return;
    }
    hideShowing();
    show(view);
  }

  private void cacheView(View child) {
    child.setVisibility(GONE);
    if (child.getId() > 0) {
      inflatedViews.put(child.getId(), child);
    }
  }

  private void show(View view) {
    view.setVisibility(VISIBLE);
    showing = view;
  }

  private void hideShowing() {
    if (showing != null) {
      showing.setVisibility(GONE);
    }
  }
}
