package imageviewer.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Bitmap {

    private final String file;
    private final byte[] byteArray;

    public Bitmap(String file) {
        this.file = file;
        this.byteArray = extractFromFile();
    }

    public byte[] getByteArray() {
        return byteArray;
    }

    private byte[] extractFromFile() {
        try {
            File fnew = new File(file);
            BufferedImage originalImage = ImageIO.read(fnew);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);
            
            return baos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(Bitmap.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        // File fi = new File("myfile.jpg");
        // byte[] fileContent = Files.readAllBytes(fi.toPath())

    }

}
