package vn.tiki.appid.user;

import vn.tiki.appid.data.entity.User;

/**
 * Created by Giang Nguyen on 11/15/16.
 */

public interface LoginCallback {

  void onSuccess(User user);
}
