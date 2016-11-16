/**
 * Created by msrabon on 12-Nov-16.
 * Project Name: JSON GSON.
 */
public class Address {
    private String house_no;
    private String road_no;
    private String area;
    private String post_code;

    /**
     *
     * @param house_no
     * @param road_no
     * @param area
     * @param post_code
     */
    public Address(String house_no, String road_no, String area, String post_code) {
        this.house_no = house_no;
        this.road_no = road_no;
        this.area = area;
        this.post_code = post_code;
    }

    public String getHouse_no() {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getRoad_no() {
        return road_no;
    }

    public void setRoad_no(String road_no) {
        this.road_no = road_no;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    @Override
    public String toString() {
        return String.format("Address: ##############\nHouse # %s\nRoad # %s\nArea : %s\nPost Code # %s",house_no,road_no,area,post_code);
    }
}
