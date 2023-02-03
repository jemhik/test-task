package com.jemhik.test.controller;

import com.jemhik.test.api.TransactionApi;
import com.jemhik.test.dto.TransactionDto;
import com.jemhik.test.service.TransactionService;
import com.jemhik.test.service.impl.PdfService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController implements TransactionApi {

  private final TransactionService transactionService;
  private final PdfService pdfService;

  @Override
  public ResponseEntity<String> importTransactionsFromExcelToDb(@RequestPart(required = true) List<MultipartFile> files) {
    transactionService.importToDb(files);
    return ResponseEntity.ok("Success");
  }

  @Override
  public ResponseEntity<List<TransactionDto>> listAllRecords() {
    return ResponseEntity.ok(transactionService.listAllRecords());
  }

  @Override
  public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException {
    response.setContentType("application/pdf");
    DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
    String currentDateTime = dateFormat.format(new Date());
    String headerkey = "Content-Disposition";
    String headervalue = "attachment; filename=transaction" + currentDateTime + ".pdf";
    response.setHeader(headerkey, headervalue);
    List <TransactionDto> transactionList = transactionService.listAllRecords();
    pdfService.generate(transactionList, response);
  }

}
