package com.sapril.op.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapril.op.model.Deviation;
import com.sapril.op.model.ParsedEfakturData;
import com.sapril.op.model.ValidationReport;
import com.sapril.op.model.ValidationResult;
import com.sapril.op.parser.PdfParser;
import com.sapril.op.parser.QrCodeExtractor;
import com.sapril.op.parser.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ValidationService {

    @Autowired
    private PdfParser pdfParser;
    @Autowired
    private XmlParser xmlParser;

    public ValidationReport validateEfaktur(MultipartFile file) {
        try {
            // Step 1: Parse PDF text
            ParsedEfakturData parsedPdf = pdfParser.parse(file);

            // Step 2: Extract QR code URL
            InputStream pdfInputStream = file.getInputStream();
            String qrUrl = QrCodeExtractor.extractQrCode(pdfInputStream);

            // Step 3: Call DJP mock API and parse XML
            String xmlContent = fetchXmlFromDjp(qrUrl);
            ParsedEfakturData parsedXml = xmlParser.parseXml(xmlContent);

            // Step 4: Compare fields
            List<Deviation> deviations = compareFields(parsedPdf, parsedXml);

            // Step 5: Build final result
            String status = deviations.isEmpty() ? "validated_successfully" : "validated_with_deviations";
            String message = deviations.isEmpty() ? "Data valid dan cocok." : "Terdapat " + deviations.size() + " perbedaan data antara PDF dan DJP.";

            ValidationResult validationResult = new ValidationResult(deviations, parsedXml);
            return new ValidationReport(status, message, validationResult);

        } catch (Exception e) {
            return new ValidationReport("error", "Terjadi kesalahan: " + e.getMessage(), null);
        }
    }

    private void compare(List<Deviation> list, String field, String pdfVal, String apiVal) {
        if (!Objects.equals(pdfVal, apiVal)) {
            String type = (pdfVal == null || pdfVal.isBlank()) ? "missing_in_pdf"
                    : (apiVal == null || apiVal.isBlank()) ? "missing_in_api"
                    : "mismatch";

            list.add(new Deviation(field, pdfVal, apiVal, type));
        }
    }

    private List<Deviation> compareFields(ParsedEfakturData pdf, ParsedEfakturData api) {
        List<Deviation> list = new ArrayList<>();
        compare(list, "npwpPenjual", pdf.getNpwpPenjual(), api.getNpwpPenjual());
        compare(list, "namaPenjual", pdf.getNamaPenjual(), api.getNamaPenjual());
        compare(list, "npwpPembeli", pdf.getNpwpPembeli(), api.getNpwpPembeli());
        compare(list, "namaPembeli", pdf.getNamaPembeli(), api.getNamaPembeli());
        compare(list, "nomorFaktur", pdf.getNomorFaktur(), api.getNomorFaktur());
        compare(list, "tanggalFaktur", pdf.getTanggalFaktur(), api.getTanggalFaktur());
        compare(list, "jumlahDpp", pdf.getJumlahDpp(), api.getJumlahDpp());
        compare(list, "jumlahPpn", pdf.getJumlahPpn(), api.getJumlahPpn());
        return list;
    }

    private String fetchXmlFromDjp(String qrUrl) throws IOException {
        // Ignore URL, always return mock content from resources
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("mock_xml_djp.xml")) {
            if (is == null) {
                throw new FileNotFoundException("Mock DJP XML not found in resources.");
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}

