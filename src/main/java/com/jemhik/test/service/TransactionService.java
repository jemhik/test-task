package com.jemhik.test.service;

import com.jemhik.test.dto.TransactionDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TransactionService {

  void importToDb(List<MultipartFile> multipartfiles);
  List<TransactionDto> listAllRecords();
}
