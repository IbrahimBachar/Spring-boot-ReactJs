package com.clinic_system.clinic_alshifa.model;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PDFGeneratorService {

    public ByteArrayInputStream generateUserListPDF(List<MyAppUser> users) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Title Font
            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            document.add(new Paragraph("User List", titleFont));

            // Table with User Data
            PdfPTable table = new PdfPTable(6); // Now 6 columns without dateOfBirth
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);

            // Add Table Headers
            addTableHeader(table, "ID");
            addTableHeader(table, "Username");
            addTableHeader(table, "Email");
            addTableHeader(table, "Full Name");
            addTableHeader(table, "Phone Number");
            addTableHeader(table, "Role");

            // Add User Data
            for (MyAppUser user : users) {
                table.addCell(String.valueOf(user.getId()));
                table.addCell(user.getUsername());
                table.addCell(user.getEmail());
                table.addCell(user.getFullName());
                table.addCell(user.getPhoneNumber());
                table.addCell(user.getRole());
            }

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void addTableHeader(PdfPTable table, String headerTitle) {
        PdfPCell header = new PdfPCell();
        header.setPhrase(new Phrase(headerTitle));
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(header);
    }
}
