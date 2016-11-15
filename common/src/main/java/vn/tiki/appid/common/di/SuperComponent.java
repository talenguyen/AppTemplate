package vn.tiki.appid.common.di;

/**
 * Created by Giang Nguyen on 10/8/16.
 */

public interface SuperComponent {

  <T> T plus(Object module);
}
