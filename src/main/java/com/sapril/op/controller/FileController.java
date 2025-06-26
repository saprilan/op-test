package com.sapril.op.controller;

import com.sapril.op.model.ValidationReport;
import com.sapril.op.model.ValidationResult;
import com.sapril.op.service.ValidationService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("")
@Tag(name = "PDF & QR Upload API", description = "Endpoints for uploading PDF and extracting text or QR code")
public class FileController {
    @Autowired
    private ValidationService validationService;


    @Operation(
            summary = "Upload PDF and extract text",
            description = "Uploads a PDF file and returns the extracted text"
    )
    @PostMapping(value = "/validate-efaktur", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ValidationReport> validateEfaktur(
            @Parameter(
                    description = "E-Faktur file to upload",
                    content = @Content(mediaType = MediaType.APPLICATION_PDF_VALUE)
            )
            @RequestPart("file") MultipartFile file
    ) {
        try {
            ValidationReport result = validationService.validateEfaktur(file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            ValidationReport errorReport = new ValidationReport(
                    "error",
                    "Parsing error: " + e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorReport);
        }
    }


}
