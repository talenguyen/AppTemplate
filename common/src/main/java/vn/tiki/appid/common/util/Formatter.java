package vn.tiki.appid.common.util;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by Giang Nguyen on 12/31/16.
 */

public class Formatter {

  private Formatter() {
    //no instance
  }

  public static CharSequence formatPriceWithCurrency(double value, Locale locale) {
    NumberFormat format = NumberFormat.getCurrencyInstance(locale);
    format.setMinimumFractionDigits(0);
    format.setCurrency(Currency.getInstance(locale));
    return format.format(value);
  }
}
