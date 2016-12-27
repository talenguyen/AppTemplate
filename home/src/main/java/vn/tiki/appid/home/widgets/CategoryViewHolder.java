package vn.tiki.appid.home.widgets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tiki.appid.home.R;
import com.tiki.appid.home.R2;
import vn.tiki.appid.home.entity.Category;
import vn.tiki.noadapter.AbsViewHolder;

/**
 * Created by Giang Nguyen on 12/27/16.
 */
class CategoryViewHolder extends AbsViewHolder {
  private final Unbinder unbinder;
  @BindView(R2.id.ivIcon) ImageView ivIcon;
  @BindView(R2.id.tvName) TextView tvName;

  private CategoryViewHolder(View itemView) {
    super(itemView);
    itemView.setOnClickListener(this);
    unbinder = ButterKnife.bind(this, itemView);
  }

  public static CategoryViewHolder create(ViewGroup parent) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    final View view = inflater.inflate(R.layout.item_category_horizontal_list, parent, false);
    return new CategoryViewHolder(view);
  }

  @Override public void bind(Object item) {
    super.bind(item);
    final Category category = (Category) item;
    ivIcon.setImageResource(category.icon());
    tvName.setText(category.name());
  }

  @Override public void unbind() {
    super.unbind();
    unbinder.unbind();
  }
}
