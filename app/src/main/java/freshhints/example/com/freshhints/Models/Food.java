package freshhints.example.com.freshhints.models;

/**
 * Created by anniedevine on 12/2/14.
 */
public class Food {

    private String name;
    private int daysLeft;
    private String tips;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}