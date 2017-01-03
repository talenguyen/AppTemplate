package vn.tiki.appid.data.entity;

import com.google.gson.Gson;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Giang Nguyen on 1/1/17.
 */
public class ProductTest {

  private static final String PRODUCT_JSON = "{\n"
      + "      \"id\": 143263,\n"
      + "      \"sku\": \"9556001131355\",\n"
      + "      \"name\": \"Bột Ăn Dặm Nestle Cerelac - Lúa Mì Và Sữa (200g)\",\n"
      + "      \"url_key\": \"bot-an-dam-nestle-cerelac-lua-mi-va-sua-200g-p143263\",\n"
      + "      \"type\": \"simple\",\n"
      + "      \"book_cover\": null,\n"
      + "      \"short_description\": \"<p><strong>Bột Ăn Dặm Nestle Cerelac - Lúa Mì Và Sữa "
      + "(200g) </strong>được  bổ sung chất xơ Prebio 1 có khả năng bảo vệ hệ tiêu hóa non yếu "
      + "của bé  suốt giai đoạn ăn dặm. Bột cung cấp nguồn năng lượng dồi dào, cho bé  thêm khỏe "
      + "mạnh khi bắt đầu thời gian ít bú sữa mẹ.</p>\",\n"
      + "      \"price\": 53000,\n"
      + "      \"list_price\": 53000,\n"
      + "      \"discount\": 0,\n"
      + "      \"discount_rate\": 0,\n"
      + "      \"rating_average\": 5,\n"
      + "      \"review_count\": 12,\n"
      + "      \"order_count\": 1318,\n"
      + "      \"favourite_count\": 3,\n"
      + "      \"thumbnail_url\": \"http://tala.tikicdn"
      + ".com/cache/280x280/media/catalog/product/3/d/3d_luamisua.jpg\",\n"
      + "      \"has_ebook\": false,\n"
      + "      \"inventory_status\": \"available\",\n"
      + "      \"is_visible\": true,\n"
      + "      \"salable_type\": \"\"\n"
      + "    }";

  @Test
  public void shouldParsableByGson() throws Exception {
    final Product product = Product.typeAdapter(new Gson()).fromJson(PRODUCT_JSON);

    assertEquals(5.0, product.ratingAverage());
    assertEquals(53000, product.price());
    assertEquals("Bột Ăn Dặm Nestle Cerelac - Lúa Mì Và Sữa (200g)", product.name());
    assertEquals(12, product.reviewCount());
    assertEquals(143263, product.id());
    assertEquals(53000, product.listPrice());
    assertEquals(
        "http://tala.tikicdn.com/cache/280x280/media/catalog/product/3/d/3d_luamisua.jpg",
        product.thumbnailUrl());
    assertEquals(0, product.discountRate());
  }
}