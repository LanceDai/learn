/**
 * @author LanceDai
 * @date 2019/1/12 12:35
 * @description *
 */
public abstract class PizzaStore {
    Pizza orderPizza(String type){
        Pizza pizza;
        pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
    protected abstract Pizza createPizza(String type);
}
