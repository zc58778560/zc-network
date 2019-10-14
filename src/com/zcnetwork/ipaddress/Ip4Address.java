package com.zcnetwork.ipaddress;

import java.util.Arrays;

public class Ip4Address {
    private final static int ADDR_SIZE = 4;

    private int[] address = new int[ADDR_SIZE];

    private static boolean checkIpValue(int[] ip) {
        for (int n : ip) {
            if (n < 0 || n > 255) {
                return false;
            }
        }
        return true;
    }

    Ip4Address(int[] addr) throws UnknownAddressException {
        if (addr != null) {
            if (addr.length == ADDR_SIZE && checkIpValue(addr)) {
                this.address[0] = addr[0];
                this.address[1] = addr[1];
                this.address[2] = addr[2];
                this.address[3] = addr[3];
                return;
            }
        }
        throw new UnknownAddressException("addr 错误");
    }

    Ip4Address(String addr) throws UnknownAddressException {
        String[] arr = addr.split("\\.");

        if (arr.length != ADDR_SIZE) {
            throw new UnknownAddressException("addr 错误");
        }

        for (int i = 0; i < arr.length; i++) {
            this.address[i] = Integer.parseInt(arr[i]);
        }

        if (!checkIpValue(this.address)){
            throw new UnknownAddressException("addr 错误");
        }
    }

    public int[] getAddress() {
        return Arrays.copyOf(this.address, this.address.length);
    }

    public String getHostAddress() {
        return this.address[0] + "." + this.address[1] + "." + this.address[2] + "." + this.address[3];
    }

    @Override
    public int hashCode() {
        return (this.address[0] << 24) & 0xFF000000 |
                (this.address[1] << 16) & 0xFF0000 |
                (this.address[2] << 8) & 0xFF00 |
                this.address[3] & 0xFF;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ip4Address that = (Ip4Address) o;
        return Arrays.equals(address, that.address);
    }

    @Override
    public String toString() {
        return "Ip4Address{" +
                "address=" + Arrays.toString(address) +
                '}';
    }
}
