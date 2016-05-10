package classes.web.generator;

import classes.web.entity.Load;
import classes.web.entity.Transport;
import classes.web.entity.user.User;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.List;

public class PdfGenerator {
    private static final PdfGenerator instance = new PdfGenerator();
    private String backGroundImage;

    public void setImage(String image) {
        backGroundImage = image;
    }

    public static PdfGenerator getInstance() {
        return instance;
    }

    BaseFont baseFont;

    private PdfGenerator() {
        try {
            baseFont = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED);
        } catch (DocumentException e) {

        } catch (IOException e) {

        }
    }

    public void generateTransportInformation(Transport transport, OutputStream outputStream) throws DocumentException {
        Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, outputStream);
        pdfWriter.setEncryption("concretepage".getBytes(), "123".getBytes(), PdfWriter.ALLOW_SCREENREADERS, PdfWriter.ENCRYPTION_AES_128);
        pdfWriter.createXmpMetadata();
        doc.open();

        Paragraph title = new Paragraph("Transport Information ", FontFactory.getFont(FontFactory.HELVETICA,
                18, Font.ITALIC, new CMYKColor(0, 255, 255, 17)));
        title.setAlignment(Element.ALIGN_CENTER);

        doc.add(title);
        Font font = new Font(baseFont, 11, Font.BOLD);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Transport model: " + transport.getModel() + ": ", font));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("State number: ", font));
        doc.add(new Phrase(String.valueOf(transport.getStateNumber()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Tonnage: ", font));
        doc.add(new Phrase(String.valueOf(transport.getTonnage()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Payment for kilometre: ", font));
        doc.add(new Phrase(String.valueOf(transport.getPaymentForKilometer()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Trailer type: ", font));
        doc.add(new Phrase(transport.getTrailerType().getTrailerTypeName(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);


        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

    setBackgroundImg(pdfWriter);

    doc.close();
}

    public void generateLoadInformation(Load load, OutputStream outputStream) throws DocumentException {
        Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, outputStream);
        pdfWriter.setEncryption("concretepage".getBytes(), "123".getBytes(), PdfWriter.ALLOW_SCREENREADERS, PdfWriter.ENCRYPTION_AES_128);
        pdfWriter.createXmpMetadata();
        doc.open();

        Paragraph title = new Paragraph("Load Information ", FontFactory.getFont(FontFactory.HELVETICA,
                18, Font.ITALIC, new CMYKColor(0, 255, 255, 17)));
        title.setAlignment(Element.ALIGN_CENTER);

        doc.add(title);
        Font font = new Font(baseFont, 11, Font.BOLD);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Load ID: " + load.getLoadID() + ": ", font));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Weight: ", font));
        doc.add(new Phrase(String.valueOf(load.getWeight()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Cost of delivery: ", font));
        doc.add(new Phrase(String.valueOf(load.getCostOfDelivery()), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Load type: ", font));
        doc.add(new Phrase(load.getLoadType().getLoadTypeName(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Load description: ", font));
        doc.add(new Phrase(load.getLoadDescription(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);


        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        setBackgroundImg(pdfWriter);

        doc.close();
    }

    public void generateUserInformation(User user, OutputStream outputStream) throws DocumentException {
        Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter pdfWriter = PdfWriter.getInstance(doc, outputStream);
        pdfWriter.setEncryption("concretepage".getBytes(), "123".getBytes(), PdfWriter.ALLOW_SCREENREADERS, PdfWriter.ENCRYPTION_AES_128);
        pdfWriter.createXmpMetadata();
        doc.open();

        Paragraph title = new Paragraph("User Information ", FontFactory.getFont(FontFactory.HELVETICA,
                18, Font.ITALIC, new CMYKColor(0, 255, 255, 17)));
        title.setAlignment(Element.ALIGN_CENTER);

        doc.add(title);
        Font font = new Font(baseFont, 11, Font.BOLD);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Login: " + user.getLogin()+ ": ", font));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Namer: ", font));
        doc.add(new Phrase(user.getName(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Surname: ", font));
        doc.add(new Phrase(user.getSurname(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Password: ", font));
        doc.add(new Phrase(user.getPassword(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("User type: ", font));
        doc.add(new Phrase(user.getUserType().getUserType(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Load(s): ", font));
        doc.add(new Phrase(user.getAllLoads().toString(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        doc.add(new Chunk("Transport: ", font));
        doc.add(new Phrase(user.getAllTransports().toString(), new Font(baseFont, 10, Font.UNDERLINE)));
        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);


        doc.add(Chunk.NEWLINE);
        doc.add(Chunk.NEWLINE);

        setBackgroundImg(pdfWriter);

        doc.close();
    }

    private void setBackgroundImg(PdfWriter pdfWriter) throws DocumentException {
        PdfContentByte canvas = pdfWriter.getDirectContentUnder();
        Image image = null;
        try { image = Image.getInstance(backGroundImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (image != null) {
            image.scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            image.setAbsolutePosition(0, 0);
            canvas.saveState();
            PdfGState pdfGState = new PdfGState();
            pdfGState.setFillOpacity(0.5f);
            canvas.setGState(pdfGState);
            canvas.addImage(image);
            canvas.restoreState();
        }
    }
}
