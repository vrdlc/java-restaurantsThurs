import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfRestaurantsAreTheSame() {
    Restaurant firstRestaurant = new Restaurant("Lardo", "$", "Casual", 2);
    Restaurant secondRestaurant = new Restaurant("Lardo", "$", "Casual", 2);
    assertTrue(firstRestaurant.equals(secondRestaurant));
  }

  @Test
  public void save_returnsTrueIfRestaurantsAreTheSame() {
    Restaurant testRestaurant = new Restaurant("Lardo", "$", "Casual", 2);
    testRestaurant.save();
    assertTrue(Restaurant.all().get(0).equals(testRestaurant));
  }

  @Test
  public void save_assignsIdToObject() {
    Restaurant myRestaurant = new Restaurant("Lardo", "$", "Casual", 2);
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.all().get(0);
    assertEquals(myRestaurant.getId(), savedRestaurant.getId());
  }

  @Test
  public void find_findsRestaurantInDatabase_true() {
    Restaurant testRestaurant = new Restaurant("Lardo", "$", "Casual", 2);
    testRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(testRestaurant.getId());
    assertTrue(testRestaurant.equals(savedRestaurant));
  }

  @Test
  public void updateName_updatesRestaurantNameInDatabase() {
    Restaurant testRestaurant = new Restaurant("Lardo", "$", "Casual", 2);
    testRestaurant.save();
    testRestaurant.updateName("Pig Fat");
    assertEquals(Restaurant.all().get(0).getName(), "Pig Fat");
    // assertTrue(((Restaurant.all().get(0)).getName()).equals("Pig Fat"));
  }

  @Test
  public void updatePrice_updatesRestaurantPriceInDatabase() {
    Restaurant testRestaurant = new Restaurant("Lardo", "$", "Casual", 2);
    testRestaurant.save();
    testRestaurant.updatePrice("$$");
    assertEquals(Restaurant.all().get(0).getPrice(), "$$");
  }

  @Test
  public void updateVibe_updatesRestaurantVibeInDatabase() {
    Restaurant testRestaurant = new Restaurant("Lardo", "$", "Casual", 2);
    testRestaurant.save();
    testRestaurant.updateVibe("Divey");
    assertEquals(Restaurant.all().get(0).getVibe(), "Divey");
  }

  @Test
  public void updateCusineId_updatesRestaurantCuisineIdInDatabase() {
    Restaurant testRestaurant = new Restaurant("Lardo", "$", "Casual", 2);
    testRestaurant.save();
    testRestaurant.updateCuisineId(3);
    assertEquals(Restaurant.all().get(0).getCuisineId(), 3);
  }
}
