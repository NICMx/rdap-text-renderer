package mx.nic.rdap.renderer.text.writer;

import java.io.PrintWriter;
import java.util.List;

import mx.nic.rdap.core.catalog.VariantRelation;
import mx.nic.rdap.core.db.Domain;
import mx.nic.rdap.core.db.DsData;
import mx.nic.rdap.core.db.Event;
import mx.nic.rdap.core.db.KeyData;
import mx.nic.rdap.core.db.Link;
import mx.nic.rdap.core.db.Nameserver;
import mx.nic.rdap.core.db.PublicId;
import mx.nic.rdap.core.db.SecureDNS;
import mx.nic.rdap.core.db.Variant;
import mx.nic.rdap.core.db.VariantName;
import mx.nic.rdap.renderer.util.RendererUtil;

public class DomainRenderer {

	public static void writeDomains(List<Domain> domains, PrintWriter writer) {
		writer.println("Domains search result:");
		for (Domain domain : domains) {
			writeDomain(domain, writer, "\t");
			writer.println();
		}
	}

	public static void writeDomain(Domain domain, PrintWriter writer) {
		writeDomain(domain, writer, "");
	}

	public static void writeDomain(Domain d, PrintWriter writer, String tabSpace) {

		writer.write("Domain Object: ");
		writer.println();

		if (RendererUtil.isObjectVisible(d.getLdhName())) {
			writer.write("LDH name: ");
			writer.write(d.getLdhName());
			writer.println();
		}

		if (RendererUtil.isObjectVisible(d.getUnicodeName())) {
			writer.write("unicode name: ");
			writer.write(d.getUnicodeName());
			writer.println();
		}

		if (RendererUtil.isObjectVisible(d.getNameServers())) {
			writer.write("Domain nameservers: ");
			List<Nameserver> nameservers = d.getNameServers();
			for (int i = 0; i < nameservers.size(); i++) {
				writer.println();
				NameserverRenderer.writeNS(nameservers.get(i), writer, "\t");
			}
			writer.println();
		}
		if (RendererUtil.isObjectVisible(d.getPublicIds())) {
			writer.write("public ids: ");
			List<PublicId> pids = d.getPublicIds();
			for (PublicId pid : pids) {
				writer.write("\n\t");
				writer.write("type: ");
				if (RendererUtil.isObjectVisible(pid.getType())) {
					writer.write(pid.getType());
				} else {
					writer.write("null");
				}
				writer.write(", ");
				writer.write("identifier: ");
				if (RendererUtil.isObjectVisible(pid.getPublicId())) {
					writer.write(pid.getPublicId());
				} else {
					writer.write("null");
				}
			}
			writer.println();
		}

		if (RendererUtil.isObjectVisible(d.getSecureDNS())) {
			writer.write("Secure DNS: ");
			writer.println();
			writeSecureDNS(d.getSecureDNS(), writer);

		}

		if (RendererUtil.isObjectVisible(d.getVariants())) {
			writer.write("Variants: ");
			writer.println();
			for (Variant v : d.getVariants()) {
				writeVariant(v, writer);
			}
		}

		CommonRenderer.writeCommonAttributes(d, writer, "");

		if (RendererUtil.isObjectVisible(d.getIpNetwork())) {
			writer.write("Domain IpNetwork:");
			writer.println();
			IpNetworkRenderer.writeIpNetwork(d.getIpNetwork(), writer);

		}

	}

	private static void writeSecureDNS(SecureDNS sdns, PrintWriter writer) {
		if (RendererUtil.isObjectVisible(sdns.getZoneSigned())) {
			writer.write("\tZone Signed : ");
			writer.write(sdns.getZoneSigned().toString());
			writer.write("\n");
		}

		if (RendererUtil.isObjectVisible(sdns.getDelegationSigned())) {
			writer.write("\tDelegation Signed : ");
			writer.write(sdns.getDelegationSigned().toString());
			writer.write("\n");

		}

		if (RendererUtil.isObjectVisible(sdns.getMaxSigLife())) {
			writer.write("\tMax Sig Life : ");
			writer.write(sdns.getMaxSigLife().toString());
			writer.write("\n");

		}

		if (RendererUtil.isObjectVisible(sdns.getDsData())) {
			writer.write("\tDs Data : ");
			writer.write("\n");
			writeDsData(sdns.getDsData(), writer);
		}

		if (RendererUtil.isObjectVisible(sdns.getKeyData())) {
			writer.write("\tKey Data : ");
			writer.write("\n");
			writeKeyData(sdns.getKeyData(), writer);
		}
	}

	private static void writeDsData(List<DsData> dsDataList, PrintWriter writer) {
		String tab = "\t\t";
		for (DsData ds : dsDataList) {
			if (RendererUtil.isObjectVisible(ds.getKeytag())) {
				writer.write(tab);
				writer.write("keyTag : ");
				writer.write(ds.getKeytag().toString());
				writer.write("\n");
			}

			if (RendererUtil.isObjectVisible(ds.getAlgorithm())) {
				writer.write(tab);
				writer.write("Algorithm : ");
				writer.write(ds.getAlgorithm().toString());
				writer.write("\n");
			}

			if (RendererUtil.isObjectVisible(ds.getDigest())) {
				writer.write(tab);
				writer.write("Digest : ");
				writer.write(ds.getDigest());
				writer.write("\n");
			}

			if (RendererUtil.isObjectVisible(ds.getDigestType())) {
				writer.write(tab);
				writer.write("Digest Type : ");
				writer.write(ds.getDigestType().toString());
				writer.write("\n");
			}

			if (RendererUtil.isObjectVisible(ds.getEvents())) {
				writer.write(tab);
				writer.write("Ds data events : ");
				writer.write("\n");
				for (Event e : ds.getEvents()) {
					writer.write(tab + "\t");
					writer.write("event: ");
					writer.println();
					CommonRenderer.writeEvent(e, writer, tab + "\t");
				}
			}

			if (RendererUtil.isObjectVisible(ds.getLinks())) {
				writer.write(tab);
				writer.write("Ds data links : ");
				writer.write("\n");

				for (Link l : ds.getLinks()) {
					writer.write(tab + "\t");
					writer.write("link: ");
					writer.println();
					CommonRenderer.writeLink(l, writer, tab + "\t");
				}
			}

		}
	}

	private static void writeKeyData(List<KeyData> keysData, PrintWriter writer) {
		String tab = "\t\t";
		for (KeyData k : keysData) {

			if (RendererUtil.isObjectVisible(k.getFlags())) {
				writer.write(tab);
				writer.write("flags : ");
				writer.write(k.getFlags().toString());
				writer.write("\n");
			}

			if (RendererUtil.isObjectVisible(k.getProtocol())) {
				writer.write(tab);
				writer.write("protocol : ");
				writer.write(k.getProtocol().toString());
				writer.write("\n");
			}

			if (RendererUtil.isObjectVisible(k.getPublicKey())) {
				writer.write(tab);
				writer.write("public key : ");
				writer.write(k.getPublicKey());
				writer.write("\n");
			}
			if (RendererUtil.isObjectVisible(k.getAlgorithm())) {
				writer.write(tab);
				writer.write("algorithm : ");
				writer.write(k.getAlgorithm().toString());
				writer.write("\n");
			}
			if (RendererUtil.isObjectVisible(k.getEvents())) {
				writer.write(tab);
				writer.write("KeyData events : ");
				writer.write("\n");
				for (Event e : k.getEvents()) {
					writer.write(tab + "\t");
					writer.write("event: ");
					writer.println();
					CommonRenderer.writeEvent(e, writer, tab + "\t");
				}
			}

			if (RendererUtil.isObjectVisible(k.getLinks())) {
				writer.write(tab);
				writer.write("KeyData links : ");
				writer.write("\n");

				for (Link l : k.getLinks()) {
					writer.write(tab + "\t");
					writer.write("link: ");
					writer.println();
					CommonRenderer.writeLink(l, writer, tab + "\t");
				}
			}

		}
	}

	private static void writeVariant(Variant variant, PrintWriter writer) {
		String tab = "\t\t";
		writer.write("\t");
		writer.write("variant: ");
		writer.println();

		List<VariantRelation> relations = variant.getRelations();
		if (RendererUtil.isObjectVisible(relations)) {
			writer.write(tab);
			writer.write("relation: ");
			int i;
			for (i = 0; i < relations.size() - 1; i++) {
				writer.write(relations.get(i).toString() + ", ");
			}
			if (relations.size() > 0) {
				writer.write(relations.get(i).toString());
			}
			writer.println();
		}

		if (RendererUtil.isObjectVisible(variant.getIdnTable())) {
			writer.write(tab);
			writer.write("IDN table : ");
			writer.write(variant.getIdnTable());
			writer.println();
		}

		if (RendererUtil.isObjectVisible(variant.getVariantNames())) {
			writer.write(tab);
			writer.write("Variant Names");
			writer.println();
			for (VariantName vn : variant.getVariantNames()) {
				writeVariantName(vn, writer, tab + "\t");
				writer.println();
			}
			writer.println();
		}

	}

	private static void writeVariantName(VariantName vn, PrintWriter writer, String tabSpace) {
		String ldhName = vn.getLdhName();
		if (RendererUtil.isObjectVisible(ldhName)) {
			writer.write(tabSpace + "LDH Name : ");
			writer.write(ldhName);
			writer.println();
		}
		String unicodeName = vn.getUnicodeName();
		if (RendererUtil.isObjectVisible(unicodeName)) {
			writer.write(tabSpace + "Unicode Name : ");
			writer.write(unicodeName);
			writer.println();
		}

	}

}
