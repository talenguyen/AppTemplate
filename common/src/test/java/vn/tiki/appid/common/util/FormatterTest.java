package vn.tiki.appid.common.util;

import java.util.Locale;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by Giang Nguyen on 12/31/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class FormatterTest {

  @Test
  public void testFormatPriceWithCurrency() throws Exception {
    final CharSequence priceWithCurrency = Formatter.formatPriceWithCurrency(123456789, Locale.US);
    Assert.assertEquals("$123,456,789", priceWithCurrency);
  }
}