package Entity;

/**
 * @author sanske
 * @date 2018/7/16 下午4:30
 **/
public class Address {
  private String city;
  private String provance;

    public String getProvance() {
        return provance;
    }

    public void setProvance(String provance) {
        this.provance = provance;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address: {" +
                "city='" + city + '\'' +
                ", provance='" + provance + '\'' +
                '}';
    }
}
