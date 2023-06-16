package com.example.readTxt.controller;

import com.example.readTxt.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
@RestController
public class FileController {
    private final FileService fileService;

    @PostMapping(value = "/read")

    public Map<String, Long> read(@Validated @RequestParam("file") MultipartFile file) throws IOException {

        log.info("Received file " + file.getOriginalFilename());

        return fileService.getFrequencyList(file);

    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleAll(){

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
