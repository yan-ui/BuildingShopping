package cn.weiben.buildingshopping.api.model;

public class Entity4<T1, T2, T3, T4> extends Entity3<T1,T2,T3> {

    private T4 value4;

    public Entity4(T1 value1, T2 value2, T3 value3, T4 value4) {
        super(value1, value2, value3);
        this.value4 = value4;
    }

    public T4 getValue4() {
        return value4;
    }

    public void setValue4(T4 value4) {
        this.value4 = value4;
    }
}
