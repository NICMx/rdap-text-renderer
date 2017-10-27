package mx.nic.rdap.renderer.text;

import java.io.PrintWriter;
import java.util.List;

import mx.nic.rdap.core.db.Autnum;
import mx.nic.rdap.core.db.Domain;
import mx.nic.rdap.core.db.Entity;
import mx.nic.rdap.core.db.IpNetwork;
import mx.nic.rdap.core.db.Nameserver;
import mx.nic.rdap.core.db.Remark;
import mx.nic.rdap.renderer.Renderer;
import mx.nic.rdap.renderer.object.ExceptionResponse;
import mx.nic.rdap.renderer.object.HelpResponse;
import mx.nic.rdap.renderer.object.RequestResponse;
import mx.nic.rdap.renderer.object.SearchResponse;
import mx.nic.rdap.renderer.text.writer.AutnumRenderer;
import mx.nic.rdap.renderer.text.writer.CommonRenderer;
import mx.nic.rdap.renderer.text.writer.DomainRenderer;
import mx.nic.rdap.renderer.text.writer.EntityRenderer;
import mx.nic.rdap.renderer.text.writer.IpNetworkRenderer;
import mx.nic.rdap.renderer.text.writer.NameserverRenderer;
import mx.nic.rdap.renderer.util.RendererUtil;

public class TextRenderer implements Renderer {

	@Override
	public void renderEntity(RequestResponse<Entity> response, PrintWriter printWriter) {
		Entity rdapObject = response.getRdapObject();
		EntityRenderer.writeEntity(rdapObject, printWriter);
	}

	@Override
	public void renderDomain(RequestResponse<Domain> response, PrintWriter printWriter) {
		Domain rdapObject = response.getRdapObject();
		DomainRenderer.writeDomain(rdapObject, printWriter);

	}

	@Override
	public void renderAutnum(RequestResponse<Autnum> response, PrintWriter printWriter) {
		Autnum rdapObject = response.getRdapObject();
		AutnumRenderer.writeAutnum(rdapObject, printWriter);

	}

	@Override
	public void renderNameserver(RequestResponse<Nameserver> response, PrintWriter printWriter) {
		Nameserver rdapObject = response.getRdapObject();
		NameserverRenderer.writeNS(rdapObject, printWriter);
	}

	@Override
	public void renderIpNetwork(RequestResponse<IpNetwork> response, PrintWriter printWriter) {
		IpNetwork rdapObject = response.getRdapObject();
		IpNetworkRenderer.writeIpNetwork(rdapObject, printWriter);
	}

	@Override
	public void renderEntities(SearchResponse<Entity> response, PrintWriter printWriter) {
		List<Entity> rdapObjects = response.getRdapObjects();
		EntityRenderer.writeEntity(rdapObjects, printWriter);

	}

	@Override
	public void renderNameservers(SearchResponse<Nameserver> response, PrintWriter printWriter) {
		List<Nameserver> rdapObjects = response.getRdapObjects();
		NameserverRenderer.writeNS(rdapObjects, printWriter);
	}

	@Override
	public void renderDomains(SearchResponse<Domain> response, PrintWriter printWriter) {
		List<Domain> rdapObjects = response.getRdapObjects();
		DomainRenderer.writeDomains(rdapObjects, printWriter);
	}

	@Override
	public void renderException(ExceptionResponse response, PrintWriter printWriter) {
		printWriter.println("Error response:");

		if (RendererUtil.isObjectVisible(response.getErrorCode())) {
			printWriter.write("Error code: ");
			printWriter.write(response.getErrorCode());
			printWriter.println();
		}

		if (RendererUtil.isObjectVisible(response.getErrorTitle())) {
			printWriter.write("Error title: ");
			printWriter.write(response.getErrorTitle());
			printWriter.println();
		}

		if (RendererUtil.isObjectVisible(response.getDescription())) {
			printWriter.write("Error description: \n");
			for (String desc : response.getDescription()) {
				printWriter.write(desc + " ");
			}
			printWriter.println();
		}

		printWriter.println("notices : \n");
		if (RendererUtil.isObjectVisible(response.getNotices())) {
			for (Remark notice : response.getNotices()) {
				CommonRenderer.writeNotice(notice, printWriter, "\t");
			}
		}

	}

	@Override
	public void renderHelp(HelpResponse response, PrintWriter printWriter) {
		printWriter.println("Help response:");
		CommonRenderer.writeNotices(response.getNotices(), printWriter, "");

	}

}
