package imageviewer.ui.console;

import imageviewer.ui.ImageViewer;
import imageviewer.model.Image;
import java.awt.Graphics;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SwingImageViewerPanel extends JPanel implements ImageViewer {

    private Image image;
    private int offset;
    
    public SwingImageViewerPanel() {
        super();
    }
    
    @Override
    public void paint(Graphics graphics) {
        try {
            if (image == null) {
                return;
            }
            graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
            graphics.drawImage(ImageIO.read(new ByteArrayInputStream(image.getBitmap().getByteArray())), offset, 0, null);
        } catch (IOException ex) {
            Logger.getLogger(SwingImageViewerPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
        refresh();
    }

    @Override
    public void refresh() {
        repaint();
        System.out.println("This image is: " + getImage().getBitmap().getByteArray().length);
        System.out.println("The  prev image is: " + getImage().getPrev().getBitmap().getByteArray().length);
        System.out.println("The  next image is: " + getImage().getNext().getBitmap().getByteArray().length);
    }

}
