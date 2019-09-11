package useOwn;

public class StatisticDisplay implements Observer, DisplayElement {
    private final Subject weatherData;

    public StatisticDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    public void display() {
        System.out.println(this.getClass().getName());
    }

    public void update(float temp, float humidity, float pressure) {
        display();
    }
}
