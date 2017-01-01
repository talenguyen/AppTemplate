package vn.tiki.appid.data.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Giang Nguyen on 1/1/17.
 */
public class ListResponseTest {

  public static final String LIST_RESPONSE_JSON = "{\n"
      + " \"data\" : [\n"
      + "   \"First\",\n"
      + "   \"Second\"\n"
      + " ]\n"
      + "}";
  @Test
  public void shouldParsableByGson() throws Exception {
    final TypeToken<ListResponse<String>> typeToken = new TypeToken<ListResponse<String>>() {};
    final ListResponse<String> listResponse = ListResponse.typeAdapter(new Gson(), typeToken)
        .fromJson(LIST_RESPONSE_JSON);

    final List<String> items = listResponse.items();
    assertEquals(2, items.size());
    assertEquals("First", items.get(0));
    assertEquals("Second", items.get(1));
  }
}