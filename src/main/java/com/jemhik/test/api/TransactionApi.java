package com.jemhik.test.api;

import com.jemhik.test.dto.TransactionDto;
import com.lowagie.text.DocumentException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(tags = "Transaction management API")
@RequestMapping("/api/v1/transactions")
public interface TransactionApi {

  @ApiOperation("Import excel file to database")
  @ResponseStatus(HttpStatus.OK)
  @PostMapping(path = "/import-to-db")
  public ResponseEntity<String> importTransactionsFromExcelToDb(@RequestPart(required = true) List<MultipartFile> files);

  @ApiOperation("Show all records from database")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/listRecords")
  public ResponseEntity<List<TransactionDto>> listAllRecords();

  @ApiOperation("Export all records from database to pdf")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/export-to-pdf")
  public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException;
}
