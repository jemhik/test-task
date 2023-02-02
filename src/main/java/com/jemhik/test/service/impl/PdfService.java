package com.jemhik.test.service.impl;

import com.jemhik.test.dto.TransactionDto;
import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PdfService {

  public void generate(List<TransactionDto> transactionsList, HttpServletResponse response) throws DocumentException, IOException {
    Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, response.getOutputStream());
    document.open();
    Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
    fontTiltle.setSize(20);
    Paragraph paragraph1 = new Paragraph("List of the Transactions", fontTiltle);
    paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
    document.add(paragraph1);
    PdfPTable table = new PdfPTable(7);
    table.setWidthPercentage(100f);
    table.setWidths(new int[] {3,3,3,3,3,3,3});
    table.setSpacingBefore(5);
    PdfPCell cell = new PdfPCell();
    cell.setBackgroundColor(CMYKColor.BLUE);
    cell.setPadding(5);
    Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
    font.setColor(CMYKColor.WHITE);
    cell.setPhrase(new Phrase("SenderId", font));
    table.addCell(cell);
    cell.setPhrase(new Phrase("ReceiverId", font));
    table.addCell(cell);
    cell.setPhrase(new Phrase("InitiatorId", font));
    table.addCell(cell);
    cell.setPhrase(new Phrase("BankCode", font));
    table.addCell(cell);
    cell.setPhrase(new Phrase("ServiceCode", font));
    table.addCell(cell);
    cell.setPhrase(new Phrase("TrxnAmount", font));
    table.addCell(cell);
    cell.setPhrase(new Phrase("FeeAmount", font));
    table.addCell(cell);

    for (TransactionDto transaction: transactionsList) {
      table.addCell(String.valueOf(transaction.getSenderId()));
      table.addCell(String.valueOf(transaction.getReceiverId()));
      table.addCell(String.valueOf(transaction.getInitiatorId()));
      table.addCell(transaction.getBankCode());
      table.addCell(String.valueOf(transaction.getServiceCode()));
      table.addCell(String.valueOf(transaction.getTrxnAmount()));
      table.addCell(String.valueOf(transaction.getFeeAmount()));
    }
    document.add(table);
    document.close();
  }
}
