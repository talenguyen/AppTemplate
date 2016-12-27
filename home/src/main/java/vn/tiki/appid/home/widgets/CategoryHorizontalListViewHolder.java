package vn.tiki.appid.home.widgets;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.tiki.appid.home.R;
import com.tiki.appid.home.R2;
import vn.tiki.appid.home.entity.CategoryListWidget;
import vn.tiki.noadapter.AbsViewHolder;
import vn.tiki.noadapter.OnItemClickListener;
import vn.tiki.noadapter.OnlyAdapter;
import vn.tiki.noadapter.ViewHolderSelector;

/**
 * Created by Giang Nguyen on 12/27/16.
 */
public class CategoryHorizontalListViewHolder extends AbsViewHolder {
  private static final String TAG = "CategoryHorizontalListV";
  private final Unbinder unbinder;
  private final OnlyAdapter adapter;
  @BindView(R2.id.rvList) RecyclerView rvList;
  private OnItemClickListener onItemClickListener;

  private CategoryHorizontalListViewHolder(View itemView) {
    super(itemView);
    itemView.setOnClickListener(this);
    unbinder = ButterKnife.bind(this, itemView);
    adapter = new OnlyAdapter.Builder()
        .viewHolderSelector(new ViewHolderSelector() {
          @Override public AbsViewHolder viewHolderForType(ViewGroup parent, int type) {
            return CategoryViewHolder.create(parent);
          }
        })
        .onItemClickListener(new OnItemClickListener() {
          @Override public void onItemClick(View view, Object item, int position) {
            if (onItemClickListener != null) {
              onItemClickListener.onItemClick(view, item, position);
            }
          }
        })
        .build();

    rvList.setLayoutManager(new GridLayoutManager(itemView.getContext(), 5,
        LinearLayoutManager.VERTICAL, false));

    rvList.setAdapter(adapter);
  }

  @Override protected void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    super.setOnItemClickListener(onItemClickListener);
    // Use this to delegate onClick on Category to WidgetsFragment.
    this.onItemClickListener = onItemClickListener;
  }

  public static CategoryHorizontalListViewHolder create(ViewGroup parent) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    final View view = inflater.inflate(R.layout.item_category_horizontal_list_home, parent, false);
    return new CategoryHorizontalListViewHolder(view);
  }

  @Override public void bind(Object item) {
    super.bind(item);
    adapter.setItems(((CategoryListWidget) item).categories());
  }

  @Override public void unbind() {
    super.unbind();
    unbinder.unbind();
  }
}
