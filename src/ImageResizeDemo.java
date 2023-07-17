import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageResizeDemo {

    private static BufferedImage originalImage;
    private static BufferedImage scaledImage;
    private static JFrame frame;
    private static JPanel panel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImageResizeDemo::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Image Resize Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeImage();
            }
        });

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (scaledImage != null) {
                    int x = (getWidth() - scaledImage.getWidth()) / 2;
                    int y = (getHeight() - scaledImage.getHeight()) / 2;
                    g.drawImage(scaledImage, x, y, this);
                }
            }
        };
        frame.add(panel);
        loadImage();

        frame.setVisible(true);
    }

    private static void loadImage() {
        try {
            originalImage = ImageIO.read(new File("C:\\Users\\Monbe\\Documents\\simulator-master\\src\\testFinal.png"));
            resizeImage();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load the image.");
        }
    }

    private static void resizeImage() {
        if (originalImage == null || panel.getWidth() <= 0 || panel.getHeight() <= 0) return;

        int width = panel.getWidth();
        int height = panel.getHeight();
        double scaleX = (double) width / originalImage.getWidth();
        double scaleY = (double) height / originalImage.getHeight();
        double scale = Math.min(scaleX, scaleY);

        int newWidth = (int) (originalImage.getWidth() * scale * 0.7) ;
        int newHeight = (int) (originalImage.getHeight() * scale* 0.75);

        if (newWidth <= 0 || newHeight <= 0) return;

        scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        panel.repaint();
    }

}
