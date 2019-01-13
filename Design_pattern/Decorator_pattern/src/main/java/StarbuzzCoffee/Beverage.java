package StarbuzzCoffee;

public abstract class Beverage {
    private String description = "Unknown StarbuzzCoffee.Beverage";

    public String getDescription(){
        return description;
    }

    public abstract double cost();

    public void setDescription(String description) {
        this.description = description;
    }
}
