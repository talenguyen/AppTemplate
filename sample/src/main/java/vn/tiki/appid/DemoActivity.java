package vn.tiki.appid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.tiki.appid.R;
import vn.tiki.appid.common.base.BaseActivity;
import vn.tiki.appid.common.widget.SingleVisibleChildFrameLayout;

/**
 * Created by Giang Nguyen on 12/27/16.
 */

public class DemoActivity extends BaseActivity {

  private static final String TAG = "DemoActivity";
  @BindView(R.id.vContent) SingleVisibleChildFrameLayout vContent;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo);
    ButterKnife.bind(this, this);
    Log.d(TAG, "onCreate: ");
  }

  @OnClick(R.id.btShowLoading) public void onClickShowLoading() {
    vContent.show(R.id.vLoading);
  }

  @OnClick(R.id.btShowErrorConnection) public void onClickShowErrorConnection() {
    vContent.show(R.id.vErrorNetwork);
  }

  @OnClick(R.id.btShowError) public void onClickShowError() {
    vContent.show(R.id.vError);
  }
}
