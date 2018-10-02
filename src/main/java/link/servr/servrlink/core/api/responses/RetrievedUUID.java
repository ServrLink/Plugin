package link.servr.servrlink.core.api.responses;

import java.util.UUID;

public class RetrievedUUID {

    /**
     * True if the request to the registry was successful
     */
    public boolean success;

    /**
     * The user's UUID.
     * null if the user is not registered
     */
    public UUID uuid;

    public RetrievedUUID(boolean success, UUID uuid) {
        this.success = success;
        this.uuid = uuid;
    }
}
