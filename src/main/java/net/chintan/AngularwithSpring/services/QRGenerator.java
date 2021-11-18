package net.chintan.AngularwithSpring.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;

public class QRGenerator {
    public static BufferedImage generatecode(String urltext) throws WriterException {


        QRCodeWriter qrCodeWriter=new QRCodeWriter();
        BitMatrix bitMatrix=qrCodeWriter.encode(urltext, BarcodeFormat.QR_CODE,200,200);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);

    }
}
