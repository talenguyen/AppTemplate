package vn.tiki.appid.common.util;

import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Giang Nguyen on 1/6/17.
 */
public class ListsTest {

  @Test
  public void should_return_new_list_of_items() throws Exception {
    final List<String> list = Lists.listOf("1", "2", "3", "4");
    assertEquals(java.util.Arrays.asList("1", "2", "3", "4"), list);
  }

  @Test
  public void should_return_new_array_of_items_in_list() throws Exception {
    final String[] array = Lists.spread(Lists.listOf("1", "2", "3"));
    assertArrayEquals(new String[] {"1", "2", "3"}, array);
  }

  @Test
  public void should_return_list_of_items_from_two_lists_in_ordered() throws Exception {
    final List<String> list1 = java.util.Arrays.asList("1", "2", "3", "4");
    final List<String> list2 = java.util.Arrays.asList("5", "6");
    final List<String> array = Lists.merged(list1, list2);
    assertEquals(java.util.Arrays.<Object>asList("1", "2", "3", "4", "5", "6"), array);
  }
}