package vn.tiki.appid.data;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import vn.tiki.appid.data.api.Api;
import vn.tiki.appid.data.entity.Product;
import vn.tiki.appid.data.model.ProductModel;
import vn.tiki.appid.data.model.UserModel;

/**
 * Created by Giang Nguyen on 11/14/16.
 */
@Module
public class DataModule {
  @Singleton
  @Provides
  public UserModel provideUserModel() {
    return new UserModel();
  }

  @Singleton
  @Provides
  public Gson provideGson() {
    return new GsonBuilder()
        .registerTypeAdapter(Product.class, Product.typeAdapter(new Gson()))
        .create();
  }

  @Singleton
  @Provides
  public Api provideApi(@Named("application") Context context, Gson gson) {
    return new Api(context, gson);
  }

  @Singleton
  @Provides
  public ProductModel provideProductModel(Api api) {
    return new ProductModel(api);
  }
}
