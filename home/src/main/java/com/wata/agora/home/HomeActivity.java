package com.tiki.appid.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.tiki.appid.common.base.BaseActivity;
import com.tiki.appid.data.entity.User;
import javax.inject.Inject;

/**
 * Created by Giang Nguyen on 10/23/16.
 */

public class HomeActivity extends BaseActivity {

  @Inject User user;
  @BindView(R2.id.tvGreeting) TextView tvGreeting;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    butterknife.ButterKnife.bind(this);

    getDiComponent().inject(this);

    tvGreeting.setText(getString(R.string.greeting, "Giang"));
  }

  private HomeComponent getDiComponent() {
    return superComponent().plus(new HomeModule());
  }

  public void showInCome(View view) {
    Toast.makeText(this, "Show In Come", Toast.LENGTH_SHORT).show();
  }

  public void showOutCome(View view) {
    Toast.makeText(this, "Show Out Come", Toast.LENGTH_SHORT).show();
  }
}
