package cn.weiben.buildingshopping.api.model;

public class Entity3<T1, T2, T3> extends Entity2<T1,T2> {

    private T3 value3;

    public Entity3(T1 value1, T2 value2, T3 value3) {
        super(value1, value2);
        this.value3 = value3;
    }

    public T3 getValue3() {
        return value3;
    }

    public void setValue3(T3 value3) {
        this.value3 = value3;
    }

}
