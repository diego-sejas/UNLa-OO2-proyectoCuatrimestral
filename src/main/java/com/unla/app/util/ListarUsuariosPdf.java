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
import com.unla.app.entities.Users;
import com.unla.app.entities.UsersRole;
import com.unla.app.services.IUserRoleService;
import com.unla.app.services.IUserService;

@Component("admin/users")
public class ListarUsuariosPdf extends AbstractPdfView {

	@Autowired
	private IUserService usuarioService;

	@Autowired
	private IUserRoleService userRoleService;
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Users> listadoUsuarios = usuarioService.findAll(); // Lista de Objeto Users
		
		document.setPageSize(PageSize.LETTER.rotate()); // modificando Horizantolamente
		document.setMargins(-20, -20, 40, 20);
		document.open();
		
		PdfPTable tablaTitulo = new PdfPTable(1); // tabla para el titulo
		PdfPCell celda =null;
		
		//Fuentes Celdas
		Font fuentetitulo = FontFactory.getFont("Helvetica",16,Color.blue);
		Font fuenteTituloColumnas = FontFactory.getFont("Helvetica",10,Color.black);
		Font fuenteDataCeldas = FontFactory.getFont("Helvetica",10,Color.black);
		
		celda = new PdfPCell (new Phrase("LISTADO GENERAL DE USUARIOS" , fuentetitulo));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(40,190,138));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(20);
		
		tablaTitulo.addCell(celda); // agregamos la celda con el titulo a la tabla titulo.
		tablaTitulo.setSpacingAfter(30);
		
		
		/*Tabla Para Mostrar Listado de Clientes*/
		PdfPTable tablaUsers = new PdfPTable(8);
		tablaUsers.setWidths(new float[] {0.8f, 2f, 2f, 3.5f, 2f, 1.5f,1.5f,1.7f});		
		
		celda = new PdfPCell(new Phrase("ID", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsers.addCell(celda);
		
		celda = new PdfPCell(new Phrase("NOMBRES", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsers.addCell(celda);
		
		celda = new PdfPCell(new Phrase("APELLIDO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsers.addCell(celda);
		
		celda = new PdfPCell(new Phrase("EMAIL", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsers.addCell(celda);
		
		celda = new PdfPCell(new Phrase("TIPO_DOC", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsers.addCell(celda);
		
		celda = new PdfPCell(new Phrase("NÚMERO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsers.addCell(celda);		
		
		celda = new PdfPCell(new Phrase("USUARIO", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsers.addCell(celda);

		celda = new PdfPCell(new Phrase("ROL", fuenteTituloColumnas));
		celda.setBackgroundColor(Color.lightGray);
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(10);
		tablaUsers.addCell(celda);
		
		/*Bucle For, mostrar todos los datos de los clientes*/		
		List<UsersRole> roles = userRoleService.findAll();		

		for (Users user : listadoUsuarios) {
			celda = new PdfPCell(new Phrase(user.getId().toString(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaUsers.addCell(celda);
			
			celda = new PdfPCell(new Phrase(user.getFirstName(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaUsers.addCell(celda);
			
			celda = new PdfPCell(new Phrase(user.getLastName(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaUsers.addCell(celda);
			
			celda = new PdfPCell(new Phrase(user.getEmail(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaUsers.addCell(celda);
			
			celda = new PdfPCell(new Phrase(user.getTypeDni(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaUsers.addCell(celda);
			
			celda = new PdfPCell(new Phrase(user.getDni(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaUsers.addCell(celda);
			
			celda = new PdfPCell(new Phrase(user.getUserName(), fuenteDataCeldas));
			celda.setPadding(5);
			tablaUsers.addCell(celda);


			celda = new PdfPCell(new Phrase(user.getRoleName(roles), fuenteDataCeldas));
			celda.setPadding(5);
			tablaUsers.addCell(celda);
			
		}
		
		/*
		PdfPTable tablaUsers = new PdfPTable(7); // Objeto Tabla con cantidad de columnas a mostrar
		
		listadoUsuarios.forEach(users->{
			tablaUsers.addCell(users.getId().toString());// llenamos la tabla con los campos de la tabla
			tablaUsers.addCell(users.getFirstName());
			tablaUsers.addCell(users.getLastName());
			tablaUsers.addCell(users.getEmail());
			tablaUsers.addCell(users.getTypeDni());
			tablaUsers.addCell(users.getDni());
			tablaUsers.addCell(users.getUserName());
			//tablaUsers.addCell(users.getUserRole().toString());
		});*/
		
		document.add(tablaTitulo);
		document.add(tablaUsers);
	}

}
