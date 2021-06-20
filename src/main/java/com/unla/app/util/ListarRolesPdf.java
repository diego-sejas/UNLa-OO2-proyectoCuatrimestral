package com.unla.app.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.unla.app.entities.UsersRole;
import com.unla.app.services.IUserRoleService;

@Component("admin/roles")
public class ListarRolesPdf extends AbstractPdfView {

	@Autowired
	private IUserRoleService userRoleService;

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<UsersRole> listadoRoles = userRoleService.findAll(); // Lista de Objeto Users

		document.setPageSize(PageSize.LETTER.rotate()); // modificando Horizantolamente
		document.setMargins(-20, -20, 40, 20);
		document.open();

		PdfPTable tablaTitulo = new PdfPTable(1); // tabla para el titulo
		PdfPCell celda = null;

		// Fuentes Celdas
		Font fuentetitulo = FontFactory.getFont("Helvetica", 16, Color.blue);
		Font fuenteTituloColumnas = FontFactory.getFont("Helvetica", 10, Color.black);
		Font fuenteDataCeldas = FontFactory.getFont("Helvetica", 10, Color.black);

		celda = new PdfPCell(new Phrase("LISTADO GENERAL DE ROLES", fuentetitulo));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(40, 190, 138));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(20);

		tablaTitulo.addCell(celda); // agregamos la celda con el titulo a la tabla titulo.
		tablaTitulo.setSpacingAfter(30);

		/* Tabla Para Mostrar Listado de Clientes */
		PdfPTable tablaRoles = new PdfPTable(2);
		tablaRoles.setWidths(new float[] { 0.8f, 3.5f });

		celda = new PdfPCell(new Phrase("ID", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaRoles.addCell(celda);

		celda = new PdfPCell(new Phrase("NOMBRE", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaRoles.addCell(celda);

		/* Bucle For, mostrar todos los datos de los clientes */

		for (UsersRole usersRole : listadoRoles) {
			celda = new PdfPCell(new Phrase(usersRole.getId().toString(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaRoles.addCell(celda);

			celda = new PdfPCell(new Phrase(usersRole.getName(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaRoles.addCell(celda);

		}

		document.add(tablaTitulo);
		document.add(tablaRoles);
	}

}
