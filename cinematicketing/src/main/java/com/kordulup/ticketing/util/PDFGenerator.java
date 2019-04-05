package com.kordulup.ticketing.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kordulup.ticketing.entities.Reservation;

@Component
public class PDFGenerator {

	private static final Logger LOGGER = LoggerFactory.getLogger(PDFGenerator.class);

	public void generateItinerary(Reservation reservation, String filePath) {
		LOGGER.info("Inside generateItinerary()");
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));

			document.open();

			document.add(genrateTable(reservation));

			document.close();
		} catch (FileNotFoundException | DocumentException e) {
			LOGGER.error("Exception in generateItinerary()" + e);
		}
	}
	
	private PdfPTable genrateTable(Reservation reservation) {
		LOGGER.info("Inside generateTable()" + reservation);
		PdfPTable table = new PdfPTable(2);
		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Cinema Ticket Itinerary"));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Movie Details"));
		cell.setColspan(2);
		table.addCell(cell);
		
		table.addCell("Title");
		table.addCell(reservation.getProjection().getMovie().getTitle());
		
		table.addCell("Projection Date");
		table.addCell(reservation.getProjection().getProjectionDate().toString());

		table.addCell("Movie Duration");
		table.addCell(reservation.getProjection().getMovie().getDuration().toString() + "min");
		
		table.addCell("Hall");
		table.addCell(reservation.getProjection().getHall().getName());
		
		cell = new PdfPCell(new Phrase("Customer Details"));
		cell.setColspan(2);
		table.addCell(cell);
		
		LOGGER.info("Inside customer()");

		table.addCell("First Name");
		table.addCell(reservation.getCustomer().getFirstName());
		
		table.addCell("Middle Name");
		table.addCell(reservation.getCustomer().getMiddleName());

		table.addCell("Last Name");
		table.addCell(reservation.getCustomer().getLastName());

		table.addCell("Email");
		table.addCell(reservation.getCustomer().getEmail());

		table.addCell("Phone");
		table.addCell(reservation.getCustomer().getPhone());
		
		table.addCell("Seat Number");
		table.addCell(reservation.getSeatNumber().toString());
		
		return table;
	}
}
