package link.servr.servrlink.core.api;

import link.servr.servrlink.core.Core;
import link.servr.servrlink.core.ServrLink;
import link.servr.servrlink.core.Utils;
import link.servr.servrlink.core.api.responses.Registered;
import link.servr.servrlink.core.api.responses.RetrievedID;
import link.servr.servrlink.core.api.responses.RetrievedUUID;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

public class ServrLinkAPI {

    private ServrLink instance = Core.getInstance().servrLink;

    private String baseUrl;

    public ServrLinkAPI(String baseRegistryUrl) {
        if(baseRegistryUrl.endsWith("/"))
            baseUrl = baseRegistryUrl;
        else
            baseUrl = baseRegistryUrl + "/";
    }

    /**
     * Synchronously retrieves whether the specified Minecraft account has been registered through ServrLink.
     * @param uuid The user's Minecraft UUID
     * @return A Registered object that contains 2 variables: success (Whether the request was successful), and isRegistered (Whether the user is registered)
     * @see link.servr.servrlink.core.api.responses.Registered
     */
    public Registered isRegistered(UUID uuid) throws IOException {
        URL queryURL = new URL(baseUrl + "api/minecraft/isregistered?uuid=" + uuid.toString());
        String res = Utils.readURLToString(queryURL);
        JSONObject json = new JSONObject(res);

        boolean success = json.getBoolean("success");
        boolean isRegistered = json.getBoolean("registered");

        return new Registered(success, isRegistered);
    }

    /**
     * Synchronously retrieves whether the specified Discord account has been registered through ServrLink.
     * @param id The user's Discord user ID
     * @return A Registered object that contains 2 variables: success (Whether the request was successful), and isRegistered (Whether the user is registered)
     * @see link.servr.servrlink.core.api.responses.Registered
     */
    public Registered isRegistered(Long id) throws IOException {
        URL queryURL = new URL(baseUrl + "api/discord/isregistered?id=" + id.toString());
        String res = Utils.readURLToString(queryURL);
        JSONObject json = new JSONObject(res);

        boolean success = json.getBoolean("success");
        boolean isRegistered = json.getBoolean("registered");

        return new Registered(success, isRegistered);
    }

    /**
     * Synchronously retrieves the Minecraft UUID associated to a Discord user ID
     * @param id The user's Discord user ID
     * @return A RetrievedUUID object that contains 2 variables: success (Whether the request was successful), and uuid (The user's UUID, or null if they are not registered)
     * @see link.servr.servrlink.core.api.responses.RetrievedUUID
     */
    public RetrievedUUID getUUID(Long id) throws IOException {
        URL queryURL = new URL(baseUrl + "api/discord/getuuid?id=" + id.toString());
        String res = Utils.readURLToString(queryURL);
        JSONObject json = new JSONObject(res);

        boolean success = json.getBoolean("success");
        String uuidStr = json.getString("uuid");

        UUID uuid;
        try {
            uuid = UUID.fromString(uuidStr);
        } catch(IllegalArgumentException ex) { // Occurs if the user is not registered
            uuid = null;
        }

        return new RetrievedUUID(success, uuid);
    }

    /**
     * Synchronously retrieves the Minecraft UUID associated to a Discord user ID
     * @param uuid The user's Minecraft UUID
     * @return A RetrievedID object that contains 2 variables: success (Whether the request was successful), and id (The user's Discord ID, or null if they are not registered)
     * @see link.servr.servrlink.core.api.responses.RetrievedUUID
     */
    public RetrievedID getDiscordID(UUID uuid) throws IOException {
        URL queryURL = new URL(baseUrl + "api/minecraft/getid?uuid=" + uuid.toString());
        String res = Utils.readURLToString(queryURL);
        JSONObject json = new JSONObject(res);

        boolean success = json.getBoolean("success");
        String id = json.getString("id");

        if(id.isEmpty())
            id = null;

        return new RetrievedID(success, id);
    }

    /**
     * Asynchronously retrieves whether the specified Minecraft account has been registered through ServrLink.
     * @param uuid The user's Minecraft UUID
     * @param callback A Registered object that contains 2 variables: success (Whether the request was successful), and isRegistered (Whether the user is registered)
     * @see link.servr.servrlink.core.api.responses.Registered
     */
    public void isRegistered(UUID uuid, Callback<Registered> callback) {
        instance.runAsync(() -> {
            try {
                Registered registered = isRegistered(uuid);
                instance.runSync(() -> callback.run(registered));
            } catch(Exception ex) { // Relay error
                callback.onError(ex);
            }
        });
    }

    /**
     * Asynchronously retrieves whether the specified Discord account has been registered through ServrLink.
     * @param id The user's Discord user ID
     * @param callback A Registered object that contains 2 variables: success (Whether the request was successful), and isRegistered (Whether the user is registered)
     * @see link.servr.servrlink.core.api.responses.Registered
     */
    public void isRegistered(Long id, Callback<Registered> callback) {
        instance.runAsync(() -> {
            try {
                Registered registered = isRegistered(id);
                instance.runSync(() -> callback.run(registered));
            } catch (Exception ex) { // Relay error
                callback.onError(ex);
            }
        });
    }

    /**
     * Asynchronously retrieves the Minecraft UUID associated to a Discord user ID
     * @param id The user's Discord user ID
     * @param callback A RetrievedUUID object that contains 2 variables: success (Whether the request was successful), and uuid (The user's UUID, or null if they are not registered)
     * @see link.servr.servrlink.core.api.responses.RetrievedUUID
     */
    public void getUUID(Long id, Callback<RetrievedUUID> callback) {
        instance.runAsync(() -> {
            try {
                RetrievedUUID retrievedUUID = getUUID(id);
                instance.runSync(() -> callback.run(retrievedUUID));
            } catch (Exception ex) { // Relay error
                callback.onError(ex);
            }
        });
    }

    /**
     * Asynchronously retrieves the Minecraft UUID associated to a Discord user ID
     * @param uuid The user's Minecraft UUID
     * @param callback A RetrievedID object that contains 2 variables: success (Whether the request was successful), and id (The user's Discord ID, or null if they are not registered)
     * @see link.servr.servrlink.core.api.responses.RetrievedUUID
     */
    public void getDiscordID(UUID uuid, Callback<RetrievedID> callback) {
        instance.runAsync(() -> {
            try {
                RetrievedID retrievedID = getDiscordID(uuid);
                instance.runSync(() -> callback.run(retrievedID));
            } catch (Exception ex) { // Relay error
                callback.onError(ex);
            }
        });
    }
}
