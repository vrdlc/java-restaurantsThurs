import org.sql2o.*;
import java.util.List;

public class Review {
  private int id;
  private String title;
  private String username;
  private String text;
  private int restaurantId;


  public Review (String title, String username, String text, int restaurantId) {
    this.title = title;
    this.username = username;
    this.text = text;
    this.restaurantId = restaurantId;
  }

  public int getId() {
    return id;
  }

  public String getTitle(){
    return title;
  }

  public String getUsername() {
    return username;
  }

  public String getText() {
    return text;
  }

  public int getRestaurantId() {
    return restaurantId;
  }

  @Override
  public boolean equals(Object otherReview) {
    if (!(otherReview instanceof Review)) {
      return false;
    } else {
      Review newReview = (Review) otherReview;
      return this.getTitle().equals(newReview.getTitle()) &&
      this.getUsername().equals(newReview.getUsername()) &&
      this.getText().equals(newReview.getText()) &&
      this.getRestaurantId() == (newReview.getRestaurantId());
    }
  }

  //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO reviews(title, username, text, restaurantId) VALUES (:title, :username, :text, :restaurantId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("title", title)
        .addParameter("username", username)
        .addParameter("text", text)
        .addParameter("restaurantId", restaurantId)
        .executeUpdate()
        .getKey();
    }
  }

  //READ
  public static List<Review> all() {
    String sql = "SELECT id, title, username, text, restaurantId FROM reviews";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Review.class);
    }
  }

  public static Review find(int id) {
    String sql = "SELECT * FROM reviews WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Review review =  con.createQuery(sql)
                          .addParameter("id", id)
                          .executeAndFetchFirst(Review.class);
      return review;
    }
  }

  //UPDATE

  public void updateTitle(String newTitle) {
    this.title = newTitle;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE reviews SET title = :title WHERE id = :id";
      con.createQuery(sql)
      .addParameter("title", title)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void updateUsername(String newUsername) {
    this.username = newUsername;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE reviews SET username = :username WHERE id = :id";
      con.createQuery(sql)
      .addParameter("username", username)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void updateText(String newText) {
    this.text = newText;
    String sql = "UPDATE reviews SET text = :text WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("text", text)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void updateRestaurantId(int newRestaurantId) {
    this.restaurantId = newRestaurantId;
    String sql = "UPDATE reviews SET restaurantId = :restaurantId WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("restaurantId", restaurantId)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void delete() {
    String sql = "DELETE FROM reviews WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
  }



}
