package jetty;

import java.io.File;
import java.net.URL;
import java.security.ProtectionDomain;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Main Class for standalone running.
 *
 * @author calvin
 */
public class Main {

	public static void main(String[] args) throws Exception {

		String contextPath = "/";
		int port = Integer.getInteger("port", 8080);

		Server server = createServer(contextPath, port);
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}

	private static Server createServer(String contextPath, int port) {
		// use Eclipse JDT compiler
		System.setProperty("org.apache.jasper.compiler.disablejsr199", "true");

		Server server = new Server(port);
		server.setStopAtShutdown(true);

		ProtectionDomain protectionDomain = Main.class.getProtectionDomain();
		URL location = protectionDomain.getCodeSource().getLocation();

		String warFile = location.toExternalForm();
		System.out.println(warFile);
		WebAppContext context = new WebAppContext(warFile, contextPath);
		context.setServer(server);

		// ����work dir,war������ѹ����Ŀ¼��jsp�������ļ�Ҳ���������С�
		String currentDir = new File(location.getPath()).getParent();
		File workDir = new File(currentDir, "work");
		context.setTempDirectory(workDir);

		server.setHandler(context);
		return server;
	}
}