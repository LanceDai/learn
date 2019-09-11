/**
 * @author LanceDai
 * @date 2019/1/12 14:35
 * @description *
 */
public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        if ("cheese".equals(type)) {
            pizza = new NYStyleCheesePizza();
        }
        return pizza;
    }
}
