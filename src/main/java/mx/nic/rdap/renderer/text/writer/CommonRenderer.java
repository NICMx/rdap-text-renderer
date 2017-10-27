package mx.nic.rdap.renderer.text.writer;

import java.io.PrintWriter;
import java.util.List;

import mx.nic.rdap.core.catalog.Status;
import mx.nic.rdap.core.db.Entity;
import mx.nic.rdap.core.db.Event;
import mx.nic.rdap.core.db.Link;
import mx.nic.rdap.core.db.RdapObject;
import mx.nic.rdap.core.db.Remark;
import mx.nic.rdap.core.db.RemarkDescription;
import mx.nic.rdap.renderer.util.RendererUtil;

public class CommonRenderer {

	public static void writeCommonAttributes(RdapObject rdapObj, PrintWriter writer, String tabSpace) {
		if (RendererUtil.isObjectVisible(rdapObj.getHandle())) {
			writer.write(tabSpace);
			writer.write("Handle: ");
			writer.write(rdapObj.getHandle());
			writer.println();

		}

		if (RendererUtil.isObjectVisible(rdapObj.getRemarks())) {
			writer.write(tabSpace);
			writer.write("remarks: ");
			writer.println();
			writerRemarks(rdapObj.getRemarks(), writer, tabSpace + "\t");
		}

		if (RendererUtil.isObjectVisible(rdapObj.getLinks())) {
			writer.write(tabSpace);
			writer.write("links");
			writer.println();
			writeLinks(rdapObj.getLinks(), writer, tabSpace + "\t");
		}

		if (RendererUtil.isObjectVisible(rdapObj.getEvents())) {
			writer.write(tabSpace);
			writer.write("events: ");
			writer.println();
			writeEvents(rdapObj.getEvents(), writer, tabSpace + "\t");
		}

		if (RendererUtil.isObjectVisible(rdapObj.getStatus())) {
			writer.write(tabSpace);
			writer.write("status: ");
			int i;
			List<Status> status = rdapObj.getStatus();
			for (i = 0; i < status.size() - 1; i++) {
				writer.write(status.get(i).toString());
				writer.write(", ");
			}
			if (status.size() > 0) {
				writer.write(status.get(i).toString());
				writer.println();
			}
		}

		if (RendererUtil.isObjectVisible(rdapObj.getPort43())) {
			writer.write(tabSpace);
			writer.write("port43 : ");
			writer.write(rdapObj.getPort43());
			writer.println();
		}

		if (RendererUtil.isObjectVisible(rdapObj.getEntities())) {
			writer.write(tabSpace);
			writer.write("Entities: ");
			List<Entity> entities = rdapObj.getEntities();
			for (int i = 0; i < entities.size(); i++) {
				writer.println();
				EntityRenderer.writeEntity(entities.get(i), writer, tabSpace + "\t");
			}
			writer.println();
		}

	}

	public static void writeNotice(Remark notice, PrintWriter writer, String tabSpace) {
		writeRemarkObject(notice, writer, tabSpace, "notice");
	}

	// private static void writeRemark(Remark remark, PrintWriter writer, String
	// tabSpace) {
	// if (RendererUtil.isObjectVisible(remark.getTitle())) {
	// writer.write(tabSpace);
	// writer.write("remark title: ");
	// writer.write(remark.getTitle());
	// writer.println();
	// }
	//
	// if (RendererUtil.isObjectVisible(remark.getType())) {
	// writer.write(tabSpace);
	// writer.write("remark type: ");
	// writer.write(remark.getType());
	// writer.println();
	// }
	//
	// if (RendererUtil.isObjectVisible(remark.getLanguage())) {
	// writer.write(tabSpace);
	// writer.write("remark lang: ");
	// writer.write(remark.getLanguage());
	// writer.println();
	// }
	//
	// if (RendererUtil.isObjectVisible(remark.getDescriptions())) {
	// writer.write(tabSpace);
	// writer.write("remark description: ");
	// for (RemarkDescription description : remark.getDescriptions()) {
	// writer.write(description.getDescription());
	// writer.write(" ");
	// }
	// writer.println();
	// }
	//
	// if (RendererUtil.isObjectVisible(remark.getLinks())) {
	// writer.write(tabSpace);
	// writer.write("remark links");
	// writer.println();
	// writeLinks(remark.getLinks(), writer, "\t");
	// }
	// }

	public static void writerRemarks(List<Remark> remarks, PrintWriter writer, String tabSpace) {
		writeRemarksObjects(remarks, writer, tabSpace, "remark");
	}

	public static void writeNotices(List<Remark> notices, PrintWriter writer, String tabSpace) {
		writeRemarksObjects(notices, writer, tabSpace, "notice");
	}

	private static void writeRemarksObjects(List<Remark> remarks, PrintWriter writer, String tabSpace, String title) {
		if (!RendererUtil.isObjectVisible(remarks)) {
			return;
		}

		for (Remark remark : remarks) {
			if (!RendererUtil.isObjectVisible(remark)) {
				continue;
			}
			writer.write(tabSpace);
			writer.write(title + ": ");
			writer.println();
			writeRemarkObject(remark, writer, tabSpace + "\t", title);
		}
	}

	private static void writeRemarkObject(Remark remark, PrintWriter writer, String tabSpace, String title) {
		if (RendererUtil.isObjectVisible(remark.getTitle())) {
			writer.write(tabSpace);
			writer.write(title + " title: ");
			writer.write(remark.getTitle());
			writer.println();
		}

		if (RendererUtil.isObjectVisible(remark.getType())) {
			writer.write(tabSpace);
			writer.write(title + " type: ");
			writer.write(remark.getType());
			writer.println();
		}

		if (RendererUtil.isObjectVisible(remark.getLanguage())) {
			writer.write(tabSpace);
			writer.write(title + " lang: ");
			writer.write(remark.getLanguage());
			writer.println();
		}

		if (RendererUtil.isObjectVisible(remark.getDescriptions())) {
			writer.write(tabSpace);
			writer.write(title + " description: ");
			for (RemarkDescription description : remark.getDescriptions()) {
				if (RendererUtil.isObjectVisible(description.getDescription())) {
					writer.write(description.getDescription());
					writer.write(" ");
				}
			}
			writer.println();
		}

		if (RendererUtil.isObjectVisible(remark.getLinks())) {
			writer.write(tabSpace);
			writer.write(title + " links:");
			writer.println();
			writeLinks(remark.getLinks(), writer, tabSpace + "\t");
		}
	}

	public static void writeLink(Link link, PrintWriter writer, String tabSpace) {
		if (RendererUtil.isObjectVisible(link.getValue())) {
			writer.write(tabSpace);
			writer.write("link value: ");
			writer.write(link.getValue());
			writer.println();
		}
		if (RendererUtil.isObjectVisible(link.getRel())) {
			writer.write(tabSpace);
			writer.write("link rel: ");
			writer.write(link.getRel());
			writer.println();
		}
		if (RendererUtil.isObjectVisible(link.getHref())) {
			writer.write(tabSpace);
			writer.write("link href: ");
			writer.write(link.getHref());
			writer.println();
		}
		if (RendererUtil.isObjectVisible(link.getHreflang())) {
			writer.write(tabSpace);
			writer.write("link hreflangs: ");
			int i;
			List<String> langs = link.getHreflang();
			for (i = 0; i < langs.size() - 1; i++) {
				writer.write(langs.get(i));
				writer.write(", ");
			}
			if (langs.size() > 0) {
				writer.write(langs.get(i));
				writer.println();
			}

		}
		if (RendererUtil.isObjectVisible(link.getTitle())) {
			writer.write(tabSpace);
			writer.write("link title: ");
			writer.write(link.getTitle());
			writer.println();
		}
		if (RendererUtil.isObjectVisible(link.getMedia())) {
			writer.write(tabSpace);
			writer.write("link media: ");
			writer.write(link.getMedia());
			writer.println();
		}
		if (RendererUtil.isObjectVisible(link.getType())) {
			writer.write(tabSpace);
			writer.write("link type: ");
			writer.write(link.getType());
			writer.println();
		}
	}

	public static void writeLinks(List<Link> links, PrintWriter writer, String tabSpace) {
		for (Link link : links) {
			writer.write(tabSpace);
			writer.write("link :");
			writer.println();
			writeLink(link, writer, tabSpace + "\t");
		}
	}

	public static void writeEvent(Event event, PrintWriter writer, String tabSpace) {
		if (RendererUtil.isObjectVisible(event.getEventAction())) {
			writer.write(tabSpace);
			writer.write("event action: ");
			writer.write(event.getEventAction().toString());
			writer.println();
		}

		if (RendererUtil.isObjectVisible(event.getEventActor())) {
			writer.write(tabSpace);
			writer.write("event actor: ");
			writer.write(event.getEventActor());
			writer.println();
		}

		if (RendererUtil.isObjectVisible(event.getEventDate())) {
			writer.write(tabSpace);
			writer.write("event date: ");
			writer.write(event.getEventDate().toString());
			writer.println();
		}

		if (RendererUtil.isObjectVisible(event.getLinks())) {
			writer.write(tabSpace);
			writer.write("event links");
			writer.println();
			writeLinks(event.getLinks(), writer, tabSpace + "\t");
		}
	}

	public static void writeEvents(List<Event> events, PrintWriter writer, String tabSpace) {
		for (Event event : events) {
			writer.write(tabSpace);
			writer.write("event: ");
			writer.println();
			writeEvent(event, writer, tabSpace + "\t");
		}
	}
}
