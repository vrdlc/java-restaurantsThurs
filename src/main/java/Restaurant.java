import org.sql2o.*;
import java.util.List;

public class Restaurant {
  private int mId;
  private String mName;
  private String mPrice;
  private String mVibe;
  private int mCuisineId;

  public Restaurant (String name, String price, String vibe, int cuisineId) {
    this.mName = name;
    this.mPrice = price;
    this.mVibe = vibe;
    this.mCuisineId = cuisineId;
  }

  public int getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  public String getPrice() {
    return mPrice;
  }

  public String getVibe() {
    return mVibe;
  }

  public int getCuisineId() {
    return mCuisineId;
  }

  @Override
  public boolean equals(Object otherRestaurant){
    if (!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
        this.getId() == newRestaurant.getId() &&
        this.getprice().equals(newRestaurant.getPrice()) &&
        this.getVibe().equals(newRestaurant.getVibe());
    }
  }

  // //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {

    }
  }

  //READ
  public static List<Restaurant> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurants()"
    }
  }
  //
  // //UPDATE
  // public void update(String newName) {
  //   this.mName = newName;
  //   try(Connection con = DB.sql2o.open()) {
  //     /******************************************************
  //       Students: TODO: Display all restaurants on main page
  //     *******************************************************/
  //     }
  // }
  //
  // //DELETE
  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //     /******************************************************
  //       Students: TODO: Display all restaurants on main page
  //     *******************************************************/
  //   }
  // }
  //
  // /******************************************************
  //   Students:
  //   TODO: Create find method
  //   TODO: Create method to get cuisine type
  // *******************************************************/

}
