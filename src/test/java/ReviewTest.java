import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.List;

public class ReviewTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Review.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfReviewIfAreTheSame() {
    Review firstReview = new Review("Awful", "dudebro", "The worst", 1);
    Review secondReview = new Review("Awful", "dudebro", "The worst", 1);
  }

  @Test
  public void save_returnsTrueIfReviewsAreTheSame() {
    Review testReview = new Review("Awful", "dudebro", "The worst", 1);
    testReview.save();
    assertTrue(Review.all().get(0).equals(testReview));
  }

  @Test
  public void save_assignsIdToObject() {
    Review testReview = new Review("Awful", "dudebro", "The worst", 1);
    testReview.save();
    Review savedReview = Review.all().get(0);
    assertEquals(testReview.getId(), savedReview.getId());
  }

  @Test
  public void find_findsReviewInDB_true() {
    Review testReview = new Review("Awful", "dudebro", "The worst", 1);
    testReview.save();
    Review savedReview = Review.find(testReview.getId());
    assertTrue(testReview.equals(savedReview));
  }

  @Test
  public void updateTitle_updatesTitleInDB() {
    Review testReview = new Review("Awful", "dudebro", "The worst", 1);
    testReview.save();
    testReview.updateTitle("Artful");
    assertEquals(Review.all().get(0).getTitle(), "Artful");
  }

  @Test
  public void updateUsername_updatesUsernameInDB() {
    Review testReview = new Review("Awful", "dudebro", "The worst", 1);
    testReview.save();
    testReview.updateUsername("thisguy");
    assertEquals(Review.all().get(0).getUsername(), "thisguy");
  }

  @Test
  public void updateText_updatesTextInDB() {
    Review testReview = new Review("Awful", "dudebro", "The worst", 1);
    testReview.save();
    testReview.updateText("The best");
    assertEquals(Review.all().get(0).getText(), "The best");
  }

  @Test
  public void updateRestaurantId_updatesRestaurantIdInDB() {
    Review testReview = new Review("Awful", "dudebro", "The worst", 1);
    testReview.save();
    testReview.updateRestaurantId(2);
    assertEquals(Review.all().get(0).getRestaurantId(), 2);
  }

  @Test
  public void delete_deletesReviewFromDB() {
  Review testReview = new Review("Awful", "dudebro", "The worst", 1);
  testReview.save();
  testReview.delete();
  assertTrue(Review.all().size() == 0);

}
