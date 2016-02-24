import org.sql2o.*;
import java.util.List;

public class Restaurant {
  private int id;
  private String name;
  private String price;
  private String vibe;
  private int cuisineId; //these MUST match sql table column names exactly

  public Restaurant (String name, String price, String vibe, int cuisineId) {
    this.name = name;
    this.price = price;
    this.vibe = vibe;
    this.cuisineId = cuisineId;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPrice() {
    return price;
  }

  public String getVibe() {
    return vibe;
  }

  public int getCuisineId() {
    return cuisineId;
  }

  @Override
  public boolean equals(Object otherRestaurant){
    if (!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
        this.getId() == newRestaurant.getId() &&
        this.getPrice().equals(newRestaurant.getPrice()) &&
        this.getVibe().equals(newRestaurant.getVibe());
    }
  }

  //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurants(name, price, vibe, cuisineId) VALUES (:name, :price, :vibe, :cuisineId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("price", price)
        .addParameter("vibe", vibe)
        .addParameter("cuisineId", cuisineId)
        .executeUpdate()
        .getKey();
    }
  }

  //READ
  public static List<Restaurant> all() {
      String sql = "SELECT id, name, price, vibe, cuisineId FROM restaurants";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  //FIND
  public static Restaurant find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants WHERE id=:id";
      Restaurant restaurant = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Restaurant.class);
      return restaurant;
    }
  }

  //UPDATE
  public void updateName(String newName) {
    this.name = newName;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE restaurants SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeUpdate();
      }
  }

  public void updatePrice(String newPrice) {
    this.price = newPrice;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE restaurants SET price = :price WHERE id = :id";
      con.createQuery(sql)
        .addParameter("price", price)
        .addParameter("id", id)
        .executeUpdate();
      }
  }

  public void updateVibe(String newVibe) {
    this.vibe = newVibe;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE restaurants SET vibe = :vibe WHERE id = :id";
      con.createQuery(sql)
        .addParameter("vibe", vibe)
        .addParameter("id", id)
        .executeUpdate();
      }
  }

  public void updateCuisineId(int newCuisineId) {
    this.cuisineId = newCuisineId;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE restaurants SET cuisineId = :cuisineId WHERE id = :id";
      con.createQuery(sql)
        .addParameter("cuisineId", cuisineId)
        .addParameter("id", id)
        .executeUpdate();
      }
  }

  //DESTROY
  public void deleteRestaurant() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM restaurants WHERE id = :id";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
  //
  // /******************************************************
  //   Students:

  //   TODO: Create method to get cuisine type
  // *******************************************************/

}
