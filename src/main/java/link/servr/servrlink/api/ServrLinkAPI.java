package link.servr.servrlink.api;

import java.util.UUID;

interface ServrLinkAPI {

    /**
     * Retrieves whether the specified Minecraft account has been registered through ServrLink.
     * @param uuid The user's Minecraft UUID
     * @return Whether the user is registered in the ServrLink registry
     */
    boolean isRegistered(UUID uuid);

    /**
     * Retrieves whether the specified Discord account has been registered through ServrLink.
     * @param id The user's Discord user ID
     * @return Whether the user is registered in the ServrLink registry
     */
    boolean isRegistered(Long id);

    /**
     * Retrieves the Minecraft UUID associated to a Discord user ID
     * @param id The user's Discord user ID
     * @return The associated Minecraft UUID, if registered. If the Discord ID is not registered, null will be returned
     */
    UUID getUUID(Long id);

    /**
     * Retrieves the Discord user ID associated to a Minecraft UUID
     * @param uuid The user's Minecraft UUID
     * @return The associated Discord user ID, if registered. If the Minecraft UUID is not registered, null will be returned
     */
    Long getDiscordID(UUID uuid);
}
