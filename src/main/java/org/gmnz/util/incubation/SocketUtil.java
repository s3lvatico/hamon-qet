package org.gmnz.util.incubation;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public final class SocketUtil {

	public static void execute(String hostname, int port, int order) throws IOException {
		InetAddress host = InetAddress.getByName(hostname);
		Socket socket = null;
		ObjectOutputStream oos = null;
		try {
			socket = new Socket(host, port);
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeInt(order);
		} //@formatter:off
		finally {
			if (oos != null) try { oos.close(); } catch (IOException e) {/* ignored */}
			if (socket != null) try { socket.close(); } catch (IOException e) {/* ignored */}
		} //@formatter:on
	}
}
