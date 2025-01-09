package model;

import java.util.Objects;

public class Key {
    private final int dstport;
    private final String protocol;

    public Key(int dstport, String protocol) {
        this.dstport = dstport;
        this.protocol = protocol;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return dstport == key.dstport && Objects.equals(protocol, key.protocol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dstport, protocol);
    }

    @Override
    public String toString() {
        return dstport + "," + protocol;
    }

}