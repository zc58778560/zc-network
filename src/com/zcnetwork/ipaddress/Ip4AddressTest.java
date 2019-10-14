package com.zcnetwork.ipaddress;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class Ip4AddressTest {
    @Test
    public void should_throw_exception_for_string_ip() {
        String[] ips = new String[]{
                "1",
                "1.1",
                "1.1.1",
                "1.1.1.1.1",
                "1.1.1.256",
                "1.1.1.a",
        };
        for (String ip : ips) {
            boolean is_catched = false;
            try {
                new Ip4Address(ip);
            } catch (Exception e) {
                is_catched = true;
            }
            assertTrue(is_catched);
        }
    }

    @Test
    public void should_throw_exception_for_byte_ip() {
        int[][] ips = new int[][]{
                new int[]{1},
                new int[]{1, 1},
                new int[]{1, 1, 1},
                new int[]{1, 1, 1, 1, 1},
                new int[]{1, 1, 1, 256},
                new int[]{1, 1, 1, -1},
        };
        for (int[] ip : ips) {
            boolean is_catched = false;
            try {
                new Ip4Address(ip);
            } catch (Exception e) {
                is_catched = true;
            }
            assertTrue(is_catched);
        }
    }

    @Test
    public void should_no_exception() {
        String[] ips = new String[]{
                "1.1.1.1",
                "255.255.255.255",
        };
        for (String ip : ips) {
            boolean is_catched = false;
            try {
                new Ip4Address(ip);
            } catch (Exception e) {
                is_catched = true;
            }
            assertFalse(is_catched);
        }
    }

    @Test
    public void should_get_address() throws UnknownAddressException {
        Ip4Address ip = new Ip4Address("1.2.3.4");
        assertArrayEquals(new int[]{1, 2, 3, 4}, ip.getAddress());
    }

    @Test
    public void should_get_host_address() throws UnknownAddressException {
        Ip4Address ip = new Ip4Address(new int[]{1, 2, 3, 4});
        assertEquals("1.2.3.4", ip.getHostAddress());
    }

    @Test
    public void should_hashcode() throws UnknownAddressException {
        Map<String, Integer> map = new HashMap<>();
        map.put("1.2.3.4", 0x01020304);
        map.put("255.255.255.255", 0xffffffff);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Ip4Address addr = new Ip4Address(entry.getKey());
            assertEquals((int) entry.getValue(), addr.hashCode());
        }
    }

    @Test
    public void should_equals() throws UnknownAddressException {
        Ip4Address ip1 = new Ip4Address("1.2.3.4");

        Ip4Address ip2 = new Ip4Address("1.2.3.4");
        assertEquals(ip1, ip2);

        Ip4Address ip3 = new Ip4Address("1.2.3.5");
        assertNotEquals(ip1, ip3);
    }
}
