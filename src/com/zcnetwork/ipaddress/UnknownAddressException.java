package com.zcnetwork.ipaddress;

import java.io.IOException;

public class UnknownAddressException extends IOException {
    public UnknownAddressException(String message) {
        super(message);
    }
}
