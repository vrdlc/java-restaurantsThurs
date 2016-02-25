import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Cuisine.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfCuisinesAreTheSame() {
    Cuisine cuisineOne = new Cuisine("American");
    Cuisine cuisineTwo = new Cuisine("American");
    assertTrue(cuisineOne.equals(cuisineTwo));
  }

  @Test
  public void save_returnsTrueIfCuisinesAreTheSame() {
    Cuisine testCuisine = new Cuisine("American");
    testCuisine.save();
    assertTrue(Cuisine.all().get(0).equals(testCuisine));
  }

  @Test
  public void save_assignsIdToObject() {
    Cuisine myCuisine = new Cuisine("American");
    myCuisine.save();
    Cuisine savedCuisine = Cuisine.all().get(0);
    assertEquals(myCuisine.getId(), savedCuisine.getId());
  }

  @Test
  public void update_updatesCuisineTypeInDB() {
    Cuisine myCuisine = new Cuisine("American");
    myCuisine.save();
    myCuisine.update("Italian");
    assertEquals(Cuisine.all().get(0).getType(), "Italian");
  }

  @Test
  public void delete_deletesCuisineTypeFromDB() {
    Cuisine myCuisine = new Cuisine("American");
    myCuisine.save();
    myCuisine.delete();
    assertEquals(Cuisine.all().size(), 0);
  }
}
