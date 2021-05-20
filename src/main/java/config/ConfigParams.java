package config;

public enum ConfigParams {

    USER("user"),
    DATABASEUSERS("databaseusers"),
    DATABASE("database"),
    IP("ip"),
    PORT("port"),
    PASSWORD("password"),
    COLLECTIONS("collections"),
    CLONEMODE("clonemode"),
    AUTOSTART("autostart"),
    MQTTBROKER("mqttbroker"),
    MQTTQOS("mqttqos"),
    MQTTTOPIC("mqtttopic");

    private final String label;

    ConfigParams(String label) {
        this.label = label;
    }

    public final String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return label;
    }
}
