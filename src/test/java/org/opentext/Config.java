package org.opentext;

public class Config {

    public static final String FTLAB_SERVER =
            getRequiredEnv("FTLAB_SERVER");

    public static final String FTLAB_CLIENT_ID =
            getRequiredEnv("FTLAB_CLIENT_ID");

    public static final String FTLAB_CLIENT_SECRET =
            getRequiredEnv("FTLAB_CLIENT_SECRET");

    public static final String FTLAB_TENANT_ID =
            getRequiredEnv("FTLAB_TENANT_ID");

    public static final String FTLAB_UDID =
            getRequiredEnv("FTLAB_UDID");

    private static String getRequiredEnv(String name) {
        String value = System.getenv(name);

        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Required environment variable is not set: " + name
            );
        }

        return value;
    }
}
