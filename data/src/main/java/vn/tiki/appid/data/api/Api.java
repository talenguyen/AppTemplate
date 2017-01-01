package vn.tiki.appid.data.api;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.concurrent.Callable;
import vn.tiki.appid.data.entity.Product;

/**
 * Created by Giang Nguyen on 12/28/16.
 */

public class Api {

  private final Context context;
  private final Gson gson;

  public Api(Context context, Gson gson) {
    this.context = context;
    this.gson = gson;
  }

  public Single<ListResponse<Product>> getProducts(String categoryId) {
    return read("products.json")
        .map(new Function<String, ListResponse<Product>>() {
          @Override public ListResponse<Product> apply(String s) throws Exception {
            final TypeToken<ListResponse<Product>> typeToken =
                new TypeToken<ListResponse<Product>>() {
                };
            return ListResponse.typeAdapter(gson, typeToken).fromJson(s);
          }
        });
  }

  private Single<String> read(final String path) {
    return Single.fromCallable(new Callable<String>() {
      @Override public String call() throws Exception {
        InputStream is = null;
        final Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
          is = context.getAssets().open(path);
          final Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
          int n;
          while ((n = reader.read(buffer)) != -1) {
            writer.write(buffer, 0, n);
          }
          writer.flush();
          return writer.toString();
        } finally {
          writer.close();
          if (is != null) {
            is.close();
          }
        }
      }
    });
  }
}
