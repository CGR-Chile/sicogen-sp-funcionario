package cl.contraloria.sicogen.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;

public class QRCodeGenerator {

    public static byte[] generateQRCodeImage(String qrcodeText) throws Exception {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(qrcodeText, BarcodeFormat.QR_CODE, 100, 100);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "jpg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        return imageInByte;
    }
}
