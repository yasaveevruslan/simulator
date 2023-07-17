//import org.opencv.core.*;
//import org.opencv.highgui.HighGui;
//import org.opencv.imgcodecs.Imgcodecs;
//import org.opencv.imgproc.Imgproc;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ComponentAdapter;
//import java.awt.event.ComponentEvent;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class MainE {
//
//    private static final String imageBackground = "C:\\Users\\Monbe\\Documents\\simulator-master\\src\\testFinal.png";
//    private static final String imageRobot = "C:\\Users\\Monbe\\Documents\\simulator-master\\src\\robot.png";
//    private static BufferedImage areaPicture;
//    private static BufferedImage robotPicture;
//
//    private static BufferedImage scaledImage;
//    private static BufferedImage rotatedImage;
//
//    static double degrees = 90;
//
//    private static int robotCoordinateX = 0;
//    private static int robotCoordinateY = 0;
//
//    private static int lastRobotCoordinateX;
//    private static int lastRobotCoordinateY;
//
//    static JFrame frame;
//    static JPanel panel;
//
//    static JButton start = new JButton("start");
//    static JButton stop = new JButton("stop");
//
//    private static void settingsGUI(){
//        frame = new JFrame("DEMO");
//        frame.setSize(1200, 800);
//        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//        frame.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                resizeImage();
//                lastRobotCoordinateX = robotCoordinateX;
//                lastRobotCoordinateY = robotCoordinateY;
//                resizeImageRobot();
//
//            }
//        });
//
//        panel = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                // Добавляем изображение поля на окно
//                if (scaledImage != null) {
//                    int x = (getWidth() - scaledImage.getWidth()) / 2;
//                    int y = (getHeight() - scaledImage.getHeight()) / 2;
//                    g.drawImage(scaledImage, x, y, this);
//                }
//
//                // Добавляем изображение робота на окно
//                if (rotatedImage != null) {
//                    int x = (getWidth() - rotatedImage.getWidth()) / 2 + robotCoordinateX;
//                    int y = (getHeight() - rotatedImage.getHeight()) / 2 + robotCoordinateY;
//                    g.drawImage(rotatedImage, x, y, this);
//                }
//            }
//        };
//
//        try {
//            // Загружаем изображение поля
//            areaPicture = ImageIO.read(new File(imageBackground));
//            resizeImage();
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Выводим ошибку, если не удалось загрузить изображение
//            System.err.println("Не удалось загрузить изображение");
//        }
//
//        frame.add(panel);
//        panel.setBackground(new Color(68, 65, 65));
//
//        addRobot();
//        resizeImageRobot();
//        createButtons();
//        frame.setVisible(true);
//    }
//
//    private static void resizeImage() {
//        if (areaPicture == null || panel.getWidth() <= 0 || panel.getHeight() <= 0) return;
//
//        int width = panel.getWidth();
//        int height = panel.getHeight();
//        double scaleX = (double) width / areaPicture.getWidth();
//        double scaleY = (double) height / areaPicture.getHeight();
//        double scale = Math.min(scaleX, scaleY);
//
//        int newWidth = (int) (areaPicture.getWidth() * scale * 0.7) ;
//        int newHeight = (int) (areaPicture.getHeight() * scale * 0.75);
//
//        if (newWidth <= 0 || newHeight <= 0) return;
//
//        scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
//        Graphics2D g2d = scaledImage.createGraphics();
//        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        g2d.drawImage(areaPicture, 0, 0, newWidth, newHeight, null);
//        g2d.dispose();
//
//        panel.repaint();
//    }
//
//    private static void resizeImageRobot() {
//        if (robotPicture == null || scaledImage == null) return;
//
//        int width = panel.getWidth();
//        int height = panel.getHeight();
//        double scaleX = (double) width / robotPicture.getWidth();
//        double scaleY = (double) height / robotPicture.getHeight();
//        double scale = Math.min(scaleX, scaleY);
//
//        int newWidth = (int) (robotPicture.getWidth() * scale * 0.12);
//        int newHeight = (int) (robotPicture.getHeight() * scale * 0.12);
//
//        if (newWidth <= 0 || newHeight <= 0) return;
//
//        rotatedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
//        Graphics2D g2d = rotatedImage.createGraphics();
//        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        g2d.drawImage(robotPicture, 0, 0, newWidth, newHeight, null);
//        g2d.dispose();
//
//        // Устанавливаем координаты робота в центр окна
//        robotCoordinateX = -170;
//        robotCoordinateY = 30;
//        lastRobotCoordinateX = robotCoordinateX;
//        lastRobotCoordinateY = robotCoordinateY;
//        panel.repaint();
//    }
//
//    public static void createButtons() {
//        Dimension buttonSize = new Dimension(100, 50);
//        start.setPreferredSize(buttonSize);
//        stop.setPreferredSize(buttonSize);
//
//        start.setBackground(new Color(124, 176, 176));
//        stop.setBackground(new Color(124, 176, 176));
//
//        // Добавляем слушатель событий для кнопки "start"
//        start.addActionListener(e -> {
//            // Перемещаем робота горизонтально вправо на 50 пикселей
//            robotCoordinateX += 50;
//            robotCoordinateY += 300;
//            // Обновляем изображение робота, чтобы обновить его положение
////            resizeImageRobot();
//            // Перерисовываем панель, чтобы обновить положение робота
//            panel.repaint();
//        });
//
//        // Добавляем слушатель событий для кнопки "stop"
//        stop.addActionListener(e -> {
//            // Возвращаем робота в исходное положение
//            robotCoordinateX = -170;
//            robotCoordinateY = 30;
//            panel.repaint();
//        });
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        buttonPanel.add(start);
//        buttonPanel.add(stop);
//
//        // для размещения кнопок внизу
//        panel.setLayout(new BorderLayout());
//        panel.add(buttonPanel, BorderLayout.SOUTH);
//    }
//
//    public static void addRobot() {
//        try {
//            // Загрузка изображения с диска
//            robotPicture = ImageIO.read(new File(imageRobot));
//            rotatedImage = new BufferedImage(robotPicture.getWidth(), robotPicture.getHeight(), robotPicture.getType());
//            Graphics2D graphic = rotatedImage.createGraphics();
//
//            graphic.rotate(Math.toRadians(degrees), robotPicture.getWidth() / 2, robotPicture.getHeight() / 2);
//
//            robotCoordinateY = lastRobotCoordinateY;
//            robotCoordinateX = lastRobotCoordinateX;
//            // Устанавливаем координаты робота перед отрисовкой
//            graphic.drawImage(robotPicture, robotCoordinateX, robotCoordinateY, null);
//            graphic.dispose();
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
////    public static void addRobotInFrame(){
////        addRobot();
////        JLabel picLabel = new JLabel(new ImageIcon(rotatedImage));
////        picLabel.setBounds(300, 280, rotatedImage.getWidth(), rotatedImage.getHeight());
////
////
////        panel.add(picLabel);
////        panel.repaint();
////    }
////
////    private static void addBorders(){
////        try{
////
////
//////            System.load("C:\\Users\\Monbe\\OneDrive\\Рабочий стол\\Новая папка (2)\\SimulatorDrive\\src\\opencv_java440.dll");
////            System.load("");
////
////            Mat sourceImage = Imgcodecs.imread("src/testFinal.png");
////
////            if (sourceImage.empty()) {
////                System.out.println("Не удалось загрузить изображение.");
////            } else {
////                // Применение алгоритма Canny
////                Imgproc.cvtColor(sourceImage, sourceImage, Imgproc.COLOR_BGR2GRAY);
////                Mat edges = new Mat();
////                Imgproc.Canny(sourceImage, edges, 100, 200, 5);
////
////                List<MatOfPoint> contours = new ArrayList<>();
////                Mat hierachy = new Mat();
////                Imgproc.findContours(edges, contours, hierachy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
////
////                Imgproc.drawContours(sourceImage, contours, -1, new Scalar(0, 0, 255), 2);
////
////                // Отображение результатов
////                HighGui.imshow("WIN", edges);
////                HighGui.imshow("RES", sourceImage);
////
////                HighGui.waitKey();
////            }
////
////        }catch (Exception e){
////            e.printStackTrace();
////
////        }
////
////    }
//
//
//
//    public static void main(String[] args) {
//
////        degrees++;
//        SwingUtilities.invokeLater(MainE::settingsGUI);
////        addRobotInFrame();
////        addCort();
////        createButtons();
////        createFrame();
////        createPanel();
////        addBorders();
////        for (int i = 90; i < 180; i++){
////            Timer.sleep(100);
////            degrees = i;
////            System.out.println(i);
////        }
//
////        new Thread( () ->{
////            while (!Thread.interrupted()){
////                try{
////                    addRobot();
////
////                    if (degrees<=180){
////
////                        degrees++;
////                        System.out.println(degrees);
////
////                    }
////                    Thread.sleep(200);
////                }catch (Exception e){
////                    e.printStackTrace();
////                }
////
////            }
////        }).start();
//    }
//
//
//}