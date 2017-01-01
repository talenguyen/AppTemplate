package vn.tiki.appid.data.api;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 * Created by Giang Nguyen on 12/28/16.
 */

@AutoValue public abstract class ListResponse<T> {

  public static <T> TypeAdapter<ListResponse<T>> typeAdapter(Gson gson,
      TypeToken<? extends ListResponse<T>> typeToken) {
    return new AutoValue_ListResponse.GsonTypeAdapter<>(gson, typeToken);
  }

  @SerializedName("data")
  public abstract List<T> items();
}
