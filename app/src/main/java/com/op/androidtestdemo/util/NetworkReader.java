package com.op.androidtestdemo.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkReader {
    public static String getLocalHostname() {
        String hostname = "";
        try {
            InetAddress address = InetAddress.getLocalHost();
            hostname = address.getHostName();
        } catch (UnknownHostException e) {

        }
        return hostname;
    }
}
