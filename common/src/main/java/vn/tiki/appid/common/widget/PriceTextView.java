package vn.tiki.appid.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;
import com.tiki.appid.common.R;
import java.util.Locale;
import vn.tiki.appid.common.util.Formatter;

/**
 * Created by Giang Nguyen on 12/31/16.
 */
public class PriceTextView extends TextView {

  public PriceTextView(Context context, AttributeSet attrs) {
    super(context, attrs);

    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PriceTextView, 0, 0);

    float price = a.getFloat(
        R.styleable.PriceTextView_price, 0f);

    final boolean strikeThrough = a.getBoolean(R.styleable.PriceTextView_strike_through, false);

    if (strikeThrough) {
      strikeThroughEnabled();
    }

    setPrice(price);

    a.recycle();
  }

  public void strikeThroughEnabled() {
    getPaint().setFlags(getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
  }

  public void setPrice(float price) {
    final CharSequence text = Formatter.formatPriceWithCurrency(price, Locale.getDefault());
    setText(text);
  }

}
