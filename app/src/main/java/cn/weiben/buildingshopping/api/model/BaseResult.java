package cn.weiben.buildingshopping.api.model;

/***
 * 基础数据结构
 */
public class BaseResult<T> {

    private int code;
    private String errmsg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code=" + code +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + data +
                '}';
    }
}