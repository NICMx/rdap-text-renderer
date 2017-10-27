package mx.nic.rdap.renderer.text.writer;

import java.io.PrintWriter;

import mx.nic.rdap.core.db.Autnum;
import mx.nic.rdap.renderer.util.RendererUtil;

public class AutnumRenderer {

	public static void writeAutnum(Autnum autnum, PrintWriter writer) {
		writeAutnum(autnum, writer, "");
	}
	
	public static void writeAutnum(Autnum autnum, PrintWriter writer, String tabSpace) {
		writer.write(tabSpace);
		writer.write("Autnum\n");
		
		if (RendererUtil.isObjectVisible(autnum.getStartAutnum())) {
			writer.write(tabSpace);
			writer.write("Start Autnum : ");
			writer.write(autnum.getStartAutnum().toString());
			writer.println();
		}
		
		if (RendererUtil.isObjectVisible(autnum.getEndAutnum())) {
			writer.write(tabSpace);
			writer.write("End Autnum : ");
			writer.write(autnum.getEndAutnum().toString());
			writer.println();
		}
		
		if (RendererUtil.isObjectVisible(autnum.getName())) {
			writer.write(tabSpace);
			writer.write("name : ");
			writer.write(autnum.getName());
			writer.println();
		}
		
		if (RendererUtil.isObjectVisible(autnum.getType())) {
			writer.write(tabSpace);
			writer.write("type : ");
			writer.write(autnum.getType());
			writer.println();
		}
		
		if (RendererUtil.isObjectVisible(autnum.getCountryCode())) {
			writer.write(tabSpace);
			writer.write("country : ");
			writer.write(autnum.getCountryCode());
			writer.println();
		}
		
		CommonRenderer.writeCommonAttributes(autnum, writer, tabSpace);
	}

}
