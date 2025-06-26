package com.sapril.op.parser;

import com.sapril.op.model.ParsedEfakturData;
import org.springframework.stereotype.Component;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

@Component
public class XmlParser {

    public ParsedEfakturData parseXml(String xmlContent) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new ByteArrayInputStream(xmlContent.getBytes()));

        doc.getDocumentElement().normalize();
        Element root = doc.getDocumentElement();

        return new ParsedEfakturData(
                getText(root, "npwpPenjual"),
                getText(root, "namaPenjual"),
                getText(root, "npwpLawanTransaksi"),
                getText(root, "namaLawanTransaksi"),
                getText(root, "nomorFaktur"),
                getText(root, "tanggalFaktur"),
                getText(root, "jumlahDpp"),
                getText(root, "jumlahPpn")
        );
    }

    private String getText(Element element, String tag) {
        NodeList nodes = element.getElementsByTagName(tag);
        return nodes.getLength() > 0 ? nodes.item(0).getTextContent() : "";
    }
}
