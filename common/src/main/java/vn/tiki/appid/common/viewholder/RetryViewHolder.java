package vn.tiki.appid.common.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tiki.appid.common.R;
import vn.tiki.noadapter.AbsViewHolder;

/**
 * Created by Giang Nguyen on 1/2/17.
 */
public class RetryViewHolder extends AbsViewHolder {
  private RetryViewHolder(View itemView) {
    super(itemView);
  }

  public static RetryViewHolder create(ViewGroup parent) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    final View view = inflater.inflate(R.layout.item_retry, parent, false);
    return new RetryViewHolder(view);
  }
}
