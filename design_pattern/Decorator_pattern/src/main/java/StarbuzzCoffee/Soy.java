package StarbuzzCoffee;

public class Soy extends CondimentDecorator {
    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", StarbuzzCoffee.Soy";
    }

    public double cost() {
        return .15 + beverage.cost();
    }
}
