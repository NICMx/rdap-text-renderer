package mx.nic.rdap.renderer.text.writer;

import java.io.PrintWriter;
import java.util.List;

import mx.nic.rdap.core.catalog.Role;
import mx.nic.rdap.core.db.Autnum;
import mx.nic.rdap.core.db.Entity;
import mx.nic.rdap.core.db.IpNetwork;
import mx.nic.rdap.core.db.PublicId;
import mx.nic.rdap.renderer.util.RendererUtil;

public class EntityRenderer {

	public static void writeEntity(List<Entity> entities, PrintWriter printWriter) {
		printWriter.write("Entities Search Result:\n");
		for (Entity entity : entities) {
			writeEntity(entity, printWriter, "\t");
			printWriter.println();
		}
	}

	public static void writeEntity(Entity entity, PrintWriter writer) {
		writeEntity(entity, writer, "");
	}

	public static void writeEntity(Entity entity, PrintWriter writer, String tabSpace) {
		writer.write(tabSpace);
		writer.write("Entity Object");
		writer.println();

		if (RendererUtil.isObjectVisible(entity.getRoles())) {
			writer.write(tabSpace);
			writer.write("Roles ");
			List<Role> roles = entity.getRoles();
			int i = 0;

			for (i = 0; i < roles.size() - 1; i++) {
				writer.write(roles.get(i).toString());
				writer.write(", ");
			}

			if (roles.size() > 0) {
				writer.write(roles.get(i).toString());
			}
			writer.println();

		}

		if (RendererUtil.isObjectVisible(entity.getPublicIds())) {
			writer.write(tabSpace);
			writer.write("public ids: ");
			List<PublicId> pids = entity.getPublicIds();
			for (PublicId pid : pids) {
				writer.write("\n\t");
				writer.write(tabSpace);
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

		CommonRenderer.writeCommonAttributes(entity, writer, tabSpace);

		if (RendererUtil.isObjectVisible(entity.getAutnums())) {
			writer.write(tabSpace);
			writer.write("Entity Autnums:");
			writer.println();
			for (Autnum autnum : entity.getAutnums()) {
				AutnumRenderer.writeAutnum(autnum, writer, "\t");
			}
		}

		if (RendererUtil.isObjectVisible(entity.getIpNetworks())) {
			writer.write(tabSpace);
			writer.write("Entity IpNetworks:");
			writer.println();
			for (IpNetwork ip : entity.getIpNetworks()) {
				IpNetworkRenderer.writeIpNetwork(ip, writer, "\t");
			}
		}

	}
}
