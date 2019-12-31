package cn.weiben.buildingshopping.api.model;

public class Entity5<T1, T2, T3, T4, T5> extends Entity4<T1, T2, T3, T4> {

    private T5 value5;

    public Entity5(T1 value1, T2 value2, T3 value3, T4 value4, T5 value5) {
        super(value1, value2, value3, value4);
        this.value5 = value5;
    }

    public T5 getValue5() {
        return value5;
    }

    public void setValue5(T5 value5) {
        this.value5 = value5;
    }
}
