package link.servr.servrlink.api;

public interface Callback<T> {

    void run(T param);
}
