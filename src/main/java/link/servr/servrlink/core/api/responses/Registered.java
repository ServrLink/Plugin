package link.servr.servrlink.core.api.responses;

public class Registered {

    /**
     * True if the request to the registry was successful
     */
    public boolean success;

    /**
     * True if the registry reports that the user is registered
     */
    public boolean isRegistered;

    public Registered(boolean success, boolean isRegistered) {
        this.success = success;
        this.isRegistered = isRegistered;
    }
}
