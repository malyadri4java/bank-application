package com.dailycode.bankapp.util;

import com.dailycode.bankapp.model.Account;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class AccountPDFStatemnt {
    private List<Account> accounts;

    public AccountPDFStatemnt(List<Account> accounts) {
        this.accounts = accounts;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(4);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Account Type", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Account Number", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("balance", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("customerId", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table, Account account) {
        table.addCell(String.valueOf(account.getType()));
        table.addCell(account.getAccNumber().toString());
        table.addCell(String.valueOf(account.getBalance()));
        table.addCell(account.getCustomerId());
    }

    public ByteArrayInputStream export() throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Account", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        for(Account account : accounts) {
            writeTableData(table, account);
            table.completeRow();
        }
        document.add(table);

        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
