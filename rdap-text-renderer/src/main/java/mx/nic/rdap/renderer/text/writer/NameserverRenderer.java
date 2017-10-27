package mx.nic.rdap.renderer.text.writer;

import java.io.PrintWriter;
import java.util.List;

import mx.nic.rdap.core.db.IpAddress;
import mx.nic.rdap.core.db.Nameserver;
import mx.nic.rdap.core.db.struct.NameserverIpAddressesStruct;
import mx.nic.rdap.renderer.util.RendererUtil;

public class NameserverRenderer {

	public static void writeNS(List<Nameserver> nameservers, PrintWriter writer) {
		writer.println("Nameservers search result:");
		for (Nameserver ns : nameservers) {
			writeNS(ns, writer, "\t");
			writer.println();
		}
	}

	public static void writeNS(Nameserver ns, PrintWriter writer) {
		writeNS(ns, writer, "");
	}

	public static void writeNS(Nameserver ns, PrintWriter writer, String tabSpace) {
		writer.write(tabSpace);
		writer.write("Nameserver Object: ");
		writer.println();

		if (RendererUtil.isObjectVisible(ns.getLdhName())) {
			writer.write(tabSpace);
			writer.write("LDH name: ");
			writer.write(ns.getLdhName());
			writer.println();
		}

		if (RendererUtil.isObjectVisible(ns.getUnicodeName())) {
			writer.write(tabSpace);
			writer.write("unicode name: ");
			writer.write(ns.getUnicodeName());
			writer.println();
		}

		if (RendererUtil.isObjectVisible(ns.getIpAddresses())) {
			writer.write(tabSpace);
			writer.write("IP addresses: ");
			NameserverIpAddressesStruct ips = ns.getIpAddresses();

			if (RendererUtil.isObjectVisible(ips.getIpv4Adresses())) {
				List<IpAddress> v4 = ips.getIpv4Adresses();
				writer.println();
				writer.write(tabSpace + "\t");
				writer.write("NS IPv4 addresses: ");

				boolean hasPreviousValue = false;
				for (IpAddress ipAddress : v4) {
					if (RendererUtil.isObjectVisible(ipAddress)
							&& RendererUtil.isObjectVisible(ipAddress.getAddress())) {
						if (hasPreviousValue) {
							writer.write(", ");
						}
						writer.write(ipAddress.getAddress().getHostAddress());
						hasPreviousValue = true;
					}
				}

			}
			if (RendererUtil.isObjectVisible(ips.getIpv6Adresses())) {
				List<IpAddress> v6 = ips.getIpv6Adresses();

				writer.println();
				writer.write(tabSpace + "\t");
				writer.write("NS IPv6 addresses: ");
				boolean hasPreviousValue = false;
				for (IpAddress ipAddress : v6) {
					if (RendererUtil.isObjectVisible(ipAddress)
							&& RendererUtil.isObjectVisible(ipAddress.getAddress())) {
						if (hasPreviousValue) {
							writer.write(", ");
						}
						writer.write(ipAddress.getAddress().getHostAddress());
						hasPreviousValue = true;
					}
				}

				writer.println();
			}

		}

		CommonRenderer.writeCommonAttributes(ns, writer, tabSpace);

	}

}
