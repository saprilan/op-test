package com.sapril.op.parser;

import com.sapril.op.model.ParsedEfakturData;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PdfParser {

    public ParsedEfakturData parse(MultipartFile file) throws IOException {
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();

        return extractFields(text);
    }

    private ParsedEfakturData extractFields(String text) {
        String npwpPenjual = extract(text, "NPWP Penjual\\s*:\\s*([\\d\\.\\-]+)");
        String namaPenjual = extract(text, "Nama Penjual\\s*:\\s*(.+)");
        String npwpPembeli = extract(text, "NPWP Pembeli\\s*:\\s*([\\d\\.\\-]+)");
        String namaPembeli = extract(text, "Nama Pembeli\\s*:\\s*(.+)");
        String nomorFaktur = extract(text, "Nomor Faktur\\s*:\\s*([\\d\\.\\-]+)");
        String tanggalFaktur = extract(text, "Tanggal Faktur\\s*:\\s*(\\d{2}/\\d{2}/\\d{4})");
        String jumlahDpp = cleanCurrency(extract(text, "Jumlah DPP\\s*:\\s*Rp\\s*([\\d\\.,]+)"));
        String jumlahPpn = cleanCurrency(extract(text, "Jumlah PPN\\s*:\\s*Rp\\s*([\\d\\.,]+)"));

        return new ParsedEfakturData(npwpPenjual, namaPenjual, npwpPembeli, namaPembeli,
                nomorFaktur, tanggalFaktur, jumlahDpp, jumlahPpn);
    }

    private String extract(String text, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        if (m.find()) return m.group(1).trim();
        return "";
    }

    private String cleanCurrency(String val) {
        return val.replace(".", "").replace(",", ".");
    }
}

