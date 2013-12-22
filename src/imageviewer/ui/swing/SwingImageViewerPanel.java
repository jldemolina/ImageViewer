package imageviewer.ui.swing;

import imageviewer.model.Image;
import imageviewer.ui.ImageViewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class SwingImageViewerPanel extends JPanel implements ImageViewer {

    private Image image;
    private int offset;
    private int initialX;

    private BufferedImage currentBurrefedImage;
    private BufferedImage nextBurrefedImage;
    private BufferedImage prevBurrefedImage;

    public SwingImageViewerPanel() {
        super();
        this.hookEvents();
    }

    @Override
    public void paint(Graphics graphics) {
        if (image == null) return;
        linkBufferedImages();
        graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
        graphics.drawImage(currentBurrefedImage, offset, 0, null);
        if (offset == 0) return;
        if (offset < 0) graphics.drawImage(nextBurrefedImage, currentBurrefedImage.getWidth() + offset, 0, null);
        else graphics.drawImage(prevBurrefedImage, offset - currentBurrefedImage.getWidth(), 0, null);
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
    }

    private void hookEvents() {
        this.hookMouseListener();
        this.hookMouseMotionListener();
    }

    private void hookMouseListener() {
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                initialX = me.getX();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                System.out.println("released" + me.getX());
                if (offset > currentBurrefedImage.getWidth() / 2)
                    image = image.getPrev();
                if (offset < -currentBurrefedImage.getWidth() / 2)
                    image = image.getNext();
                offset = 0;
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
    }

    private void hookMouseMotionListener() {
        this.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent me) {
                offset = me.getX() - initialX;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent me) {
            }
        });
    }

    private void linkBufferedImages() {
        try {
            this.currentBurrefedImage = ImageIO.read(new ByteArrayInputStream(image.getBitmap().getByteArray()));
            this.nextBurrefedImage = ImageIO.read((new ByteArrayInputStream(image.getNext().getBitmap().getByteArray())));
            this.prevBurrefedImage = ImageIO.read((new ByteArrayInputStream(image.getPrev().getBitmap().getByteArray())));
        } catch (IOException ex) {
        }
    }
}
