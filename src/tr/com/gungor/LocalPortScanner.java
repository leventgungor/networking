package tr.com.gungor;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.stream.IntStream;

public class LocalPortScanner {

    private static final int MAX_PORT_NUMBER = 65535;

    public static void main(String[] args) {
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
            String hostAddress = inetAddress.getHostAddress();

            //scanPorts(hostAddress);
            scanPorts2(hostAddress);

        } catch (UnknownHostException ex) {
            System.err.println(ex.getMessage());
        }



    }

    @Deprecated
    private static void scanPorts(String hostAddress) {


        for(int i = 0 ; i<= MAX_PORT_NUMBER; i++){
            try {
                ServerSocket serverSocket = new ServerSocket(i);
                //System.out.println(i + " port is free right now");
                serverSocket.close();
            } catch (IOException e) {
                System.out.println(i + " port is full right now");
            }
        }

    }

    private static void scanPorts2(String hostAddress) {
        IntStream.range(0, MAX_PORT_NUMBER).parallel().forEachOrdered(portIndex -> {
            try {
                ServerSocket serverSocket = new ServerSocket(portIndex);
                //System.out.println(portIndex + " port is free right now");
                serverSocket.close();
            } catch (IOException e) {
                System.out.println(portIndex + " port is full right now");
            }
        });
    }
}
