package vn.tiki.appid.common.util;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Giang Nguyen on 1/6/17.
 */

public class Lists {

  private Lists() {
    //no instance
  }

  @SafeVarargs public static <T> List<T> listOf(T... items) {
    if (items.length == 0) {
      return Collections.emptyList();
    }
    return java.util.Arrays.asList(items);
  }

  @SafeVarargs public static <T> List<T> listOf(T[]... arrays) {
    if (arrays.length == 0) {
      return Collections.emptyList();
    }

    final ArrayList<T> result = new ArrayList<>();

    for (T[] array : arrays) {
      result.addAll(Arrays.asList(array));
    }
    return result;
  }

  @SafeVarargs public static <T> List<T> merged(@NonNull List<T>... lists) {
    if (lists.length == 0) {
      return Collections.emptyList();
    }

    final ArrayList<T> result = new ArrayList<>();

    for (List<T> list : lists) {
      result.addAll(list);
    }
    return result;
  }

  @SuppressWarnings("unchecked")
  public static <T> T[] spread(List<T> list) {
    return (T[]) list.toArray();
  }
}
