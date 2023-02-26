package com.org.ozod.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.org.ozod.dto.SaleOrderDto;
import com.org.ozod.entity.User;

@Component
public class PDFGenerator {

	@Autowired
	private JsonUtil jsonutill;

	public synchronized Document createPDF(User user, List<SaleOrderDto> list, String pdfFilename) {

		Rectangle pageSize = new Rectangle(600, 850);
		pageSize.setBackgroundColor(new BaseColor(0xFF, 0xFF, 0xDE));
		Document document = new Document(pageSize);

		try {

			FileOutputStream file = new FileOutputStream(new File(pdfFilename));

			PdfWriter.getInstance(document, file);

			// Inserting Image in PDF
			Image image = Image.getInstance("D:/ozod/src/main/resources/logo.jpg");// Header Image
			image.scaleAbsolute(540f, 72f);// image width,height

			PdfPTable irdTable = new PdfPTable(1);
			irdTable.setWidthPercentage(100);
			irdTable.addCell(getIRHInvoiceCell(user.getStoreName(), PdfPCell.ALIGN_CENTER));
			irdTable.addCell(getIRDCell("Phulparash,Dist-Madhubani,Bihar-847409"));
			irdTable.addCell(getIRDCell("Mobile :: " + user.getMobile()));
			if (null != user.getEmail())
				irdTable.addCell(getIRDCell("Email  :: " + user.getEmail()));

			PdfPTable irhTable = new PdfPTable(1);
			irhTable.setWidthPercentage(100);

			irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_CENTER));
			irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_CENTER));
			irhTable.addCell(getIRHInvoiceCell("Invoice", PdfPCell.ALIGN_CENTER));
			irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_CENTER));
			irhTable.addCell(getIRHCell("", PdfPCell.ALIGN_CENTER));
			PdfPCell invoiceTable = new PdfPCell(irdTable);
			invoiceTable.setBorder(Rectangle.NO_BORDER);
			irhTable.addCell(invoiceTable);

			PdfPTable billTable = new PdfPTable(4); // one page contains 15 records
			billTable.setWidthPercentage(100);
			billTable.setWidths(new float[] { 1, 5, 2, 2 });
			billTable.setSpacingBefore(30.0f);
			billTable.addCell(getBillHeaderCell("S.N"));
			billTable.addCell(getBillHeaderCell("Product"));
			billTable.addCell(getBillHeaderCell("Quantity"));
			billTable.addCell(getBillHeaderCell("Price"));

			int serialNo = 1;
			for (SaleOrderDto product : list) {
				try {
					JsonNode node = jsonutill.getOneProduct(product.getProductType(), product.getProductNameId());
					billTable.addCell(getBillRowCell(serialNo++ + ""));
					billTable.addCell(getBillRowCellInHindi(node.get("unicode").asText()));
					billTable.addCell(getBillRowCell(product.getQuantity()));
					billTable.addCell(getBillRowCell(product.getPrice()));
				} catch (DocumentException | IOException e) {
					e.printStackTrace();
				}
			}
			;

			if (pageSize.getHeight() != billTable.getTotalHeight()) {
				billTable.addCell(getBillRowCell(" "));
				billTable.addCell(getBillRowCell(""));
				billTable.addCell(getBillRowCell(""));
				billTable.addCell(getBillRowCell(""));

				billTable.addCell(getBillRowCell(" "));
				billTable.addCell(getBillRowCell(""));
				billTable.addCell(getBillRowCell(""));
				billTable.addCell(getBillRowCell(""));

				billTable.addCell(getBillRowCell(" "));
				billTable.addCell(getBillRowCell(""));
				billTable.addCell(getBillRowCell(""));
				billTable.addCell(getBillRowCell(""));
			}

			PdfPTable validity = new PdfPTable(1);
			validity.setWidthPercentage(100);
			validity.addCell(getAccountsCell("Total"));
			PdfPCell summaryL = new PdfPCell(validity);
			summaryL.setColspan(3);
			billTable.addCell(summaryL);

			Long totalPrice = 0l;
			for (SaleOrderDto product : list) {
				totalPrice = totalPrice + Long.parseLong((product.getPrice().substring(4)));
			}
			;
			PdfPTable accounts = new PdfPTable(1);
			accounts.setWidthPercentage(100);
			accounts.addCell(getAccountsCellR("Rs. " + totalPrice + "/-"));

			PdfPCell summaryR = new PdfPCell(accounts);
			summaryR.setColspan(3);
			billTable.addCell(summaryR);

			PdfPTable describer = new PdfPTable(1);
			describer.setWidthPercentage(100);
			describer.addCell(getdescCell(" "));
			describer.addCell(getBillRowCellInHindi(
					"\u0915\u0943\u092A\u092F\u093E \u0926\u0941\u092C\u093E\u0930\u093E \u0938\u0947\u092C\u093E \u0915\u093E \u092E\u094C\u0915\u093E \u0926\u0947\u0964"));

			document.open();// PDF document opened........

			// document.add(image);
			document.add(irhTable);
			document.add(billTable);
			document.add(describer);

			document.close();

			file.close();

			System.out.println("Pdf created successfully..");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	public static void setHeader() {

	}

	public static PdfPCell getIRHCell(String text, int alignment) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 16);
		/* font.setColor(BaseColor.GRAY); */
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setPadding(5);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	public static PdfPCell getIRHInvoiceCell(String text, int alignment) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 16);
		fs.addFont(font);
		Phrase phrase = new Phrase(text, FontFactory.getFont("Arial", 15, Font.BOLD, BaseColor.BLACK));
		phrase.setFont(font);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setPadding(5);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	public static PdfPCell getIRDCell(String text) {
		PdfPCell cell = new PdfPCell(new Paragraph(text));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(5.0f);
		cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}

	public static PdfPCell getBillHeaderCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 11);
		font.setColor(BaseColor.GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(5.0f);
		return cell;
	}

	public static PdfPCell getBillRowCell(String text) {
		PdfPCell cell = new PdfPCell(new Paragraph(text));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(5.0f);
		cell.setBorderWidthBottom(0);
		cell.setBorderWidthTop(0);
		return cell;
	}

	public static PdfPCell getBillRowCellInHindi(String text) throws DocumentException, IOException {
		BaseFont unicode = BaseFont.createFont(Constant.HINDI_FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(unicode);
		PdfPCell balanceLblCell = new PdfPCell(new Phrase(text, font));
		balanceLblCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		balanceLblCell.setPadding(5.0f);
		balanceLblCell.setBorderWidthBottom(0);
		balanceLblCell.setBorderWidthTop(0);
		return balanceLblCell;
	}

	public static PdfPCell getBillFooterCell(String text) {
		PdfPCell cell = new PdfPCell(new Paragraph(text));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(5.0f);
		cell.setBorderWidthBottom(0);
		cell.setBorderWidthTop(0);
		return cell;
	}

	public static PdfPCell getValidityCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		font.setColor(BaseColor.GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setBorder(0);
		return cell;
	}

	public static PdfPCell getAccountsCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthTop(0);
		cell.setPadding(5.0f);
		return cell;
	}

	public static PdfPCell getAccountsCellR(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthTop(0);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPadding(5.0f);
		cell.setPaddingRight(20.0f);
		return cell;
	}

	public static PdfPCell getdescCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		font.setColor(BaseColor.GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		return cell;
	}
}
