package imageviewer.ui.console;

import imageviewer.ui.swing.ImageViewer;

public class ConsoleImageViewer extends ImageViewer {
    
    @Override
    public void refresh() {
        System.out.print(getImage().getDimension().getWidth());
        System.out.print("x");
        System.out.print(getImage().getDimension().getHeight());
        System.out.println("");
    }

}
