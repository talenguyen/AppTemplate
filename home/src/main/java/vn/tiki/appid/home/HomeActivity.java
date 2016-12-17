package vn.tiki.appid.home;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tiki.appid.home.R;
import com.tiki.appid.home.R2;
import vn.tiki.appid.common.base.BaseActivity;

public class HomeActivity extends BaseActivity {

  @BindView(R2.id.drawerLayout) DrawerLayout drawerLayout;
  @BindView(R2.id.toolbar) Toolbar toolbar;
  private Unbinder bind;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    bind = ButterKnife.bind(this);
    setSupportActionBar(toolbar);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    bind.unbind();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_home, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    final int id = item.getItemId();
    if (id == android.R.id.home) {
      drawerLayout.openDrawer(GravityCompat.START);
    }
    return super.onOptionsItemSelected(item);
  }
}
