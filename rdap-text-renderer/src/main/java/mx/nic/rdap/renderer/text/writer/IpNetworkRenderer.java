package mx.nic.rdap.renderer.text.writer;

import java.io.PrintWriter;

import mx.nic.rdap.core.db.IpNetwork;
import mx.nic.rdap.renderer.util.RendererUtil;

public class IpNetworkRenderer {

	public static void writeIpNetwork(IpNetwork ip, PrintWriter writer) {
		writeIpNetwork(ip, writer, "");
	}
	
	public static void writeIpNetwork(IpNetwork ipNetwork, PrintWriter writer, String tabSpace) {
		writer.write(tabSpace + "Ip Network\n");
		
		if (RendererUtil.isObjectVisible(ipNetwork.getStartAddress())) {
			writer.write(tabSpace + "Start Address : ");
			writer.write(ipNetwork.getStartAddress().getHostAddress());
			writer.println();
		}
		
		if (RendererUtil.isObjectVisible(ipNetwork.getEndAddress())) {
			writer.write(tabSpace + "End Address : ");
			writer.write(ipNetwork.getEndAddress().getHostAddress());
			writer.println();
		}
		
		if (RendererUtil.isObjectVisible(ipNetwork.getIpVersion())) {
			writer.write(tabSpace + "IP Version : ");
			writer.write(ipNetwork.getIpVersion().getVersionName());
			writer.println();
		}
		
		if (RendererUtil.isObjectVisible(ipNetwork.getName())) {
			writer.write(tabSpace + "Name : ");
			writer.write(ipNetwork.getName());
			writer.println();
		}
		
		if (RendererUtil.isObjectVisible(ipNetwork.getType())) {
			writer.write(tabSpace + "Type : ");
			writer.write(ipNetwork.getType());
			writer.println();
		}
		
		if (RendererUtil.isObjectVisible(ipNetwork.getCountry())) {
			writer.write(tabSpace + "Country : ");
			writer.write(ipNetwork.getCountry());
			writer.println();
		}
		
		if (RendererUtil.isObjectVisible(ipNetwork.getParentHandle())) {
			writer.write(tabSpace + "Parent Handle : ");
			writer.write(ipNetwork.getParentHandle());
			writer.println();
		}
		
		CommonRenderer.writeCommonAttributes(ipNetwork, writer, tabSpace);
		
	}
	
}
