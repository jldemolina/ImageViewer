package imageviewer.ui.console;

import imageviewer.ui.ImageViewer;
import imageviewer.model.Image;
import javax.swing.JPanel;

public class SwingImageViewerPanel extends JPanel implements ImageViewer {

    private Image image;

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
        System.out.print(getImage().getBitmap().getByteArray().length);
    }

 
}
