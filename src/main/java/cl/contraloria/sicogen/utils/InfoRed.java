package cl.contraloria.sicogen.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class InfoRed {

    public static List<String> getListHostAdresses() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        List<String> hostAdresses = new ArrayList<String>();

        while (interfaces.hasMoreElements())
        {
            NetworkInterface nif = interfaces.nextElement();
            Enumeration<InetAddress> inetAddresses = nif.getInetAddresses();

            while (inetAddresses.hasMoreElements())
            {
                InetAddress inetAddr = inetAddresses.nextElement();
                hostAdresses.add(inetAddr.getHostAddress());
            }
        }

        return hostAdresses;
    }
}
