package imageviewer.ui;

import imageviewer.model.Image;

public interface ImageViewer {
    
    public Image getImage();

    public void setImage(Image image);
    
    public void refresh();

}
