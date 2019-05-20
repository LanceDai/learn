package StarbuzzCoffee;

public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", StarbuzzCoffee.Mocha";
    }

    public double cost() {
        return .20 + beverage.cost();
    }
}
