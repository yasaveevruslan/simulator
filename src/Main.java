import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    static BufferedImage myPicture = null;
    static BufferedImage myPicture2 = null;

    static BufferedImage rotatedImage;

    static double degrees = 90;

    static JFrame frame = new JFrame("DEMO");
    static JPanel panel = new JPanel(){
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            // Draws the img to the BackgroundPanel.
            g.drawImage(myPicture, 50, 50, this);
        }
    };



    static JButton start = new JButton("start");
    static JButton stop = new JButton("stop");


    public static void createFrame(){
        frame.setVisible(true);
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());
        frame.getContentPane();
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }

    public static void createPanel(){
        panel.setBounds(0, 0, 1200, 800);
        panel.setBackground(new Color(68, 65, 65));
        frame.add(panel);
        panel.setLayout(null);

    }

    public static void createButtons(){

        start.setBounds(250, 600, 100, 50);
        stop.setBounds(850, 600, 100, 50);

        start.setBackground(new Color(124, 176, 176));
        stop.setBackground(new Color(124, 176, 176));

        panel.add(start);
        panel.add(stop);

    }

    public static void addCort(){
        try {
            myPicture = ImageIO.read(new File("C:\\Users\\1\\IdeaProjects\\SimulatorDriveFirst\\src\\testFinal.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
//        picLabel.setBounds(50, 50, myPicture.getWidth(), myPicture.getHeight());
//
//        panel.add(picLabel);
    }

    public static void addRobot() {

        try {
            // Загрузка изображения с диска
            myPicture2 = ImageIO.read(new File("C:\\Users\\1\\IdeaProjects\\SimulatorDriveFirst\\src\\robot.png"));

            rotatedImage = new BufferedImage(myPicture2.getHeight(), myPicture2.getWidth(), myPicture2.getType());
            Graphics2D graphic = rotatedImage.createGraphics();
            graphic.rotate(Math.toRadians(degrees), rotatedImage.getWidth() / 2, rotatedImage.getHeight() / 2);
            graphic.drawImage(myPicture2, 0, 0, null);
            graphic.dispose();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void addRobotInFrame(){
        addRobot();
        JLabel picLabel = new JLabel(new ImageIcon(rotatedImage));
        picLabel.setBounds(300, 280, rotatedImage.getWidth(), rotatedImage.getHeight());


        panel.add(picLabel);
        panel.repaint();
    }

    private static void addBorders(){
        try{


//            System.load("C:\\Users\\Monbe\\OneDrive\\Рабочий стол\\Новая папка (2)\\SimulatorDrive\\src\\opencv_java440.dll");
            System.load("");

            Mat sourceImage = Imgcodecs.imread("src/testFinal.png");

            if (sourceImage.empty()) {
                System.out.println("Не удалось загрузить изображение.");
            } else {
                // Применение алгоритма Canny
                Imgproc.cvtColor(sourceImage, sourceImage, Imgproc.COLOR_BGR2GRAY);
                Mat edges = new Mat();
                Imgproc.Canny(sourceImage, edges, 100, 200, 5);

                List<MatOfPoint> contours = new ArrayList<>();
                Mat hierachy = new Mat();
                Imgproc.findContours(edges, contours, hierachy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

                Imgproc.drawContours(sourceImage, contours, -1, new Scalar(0, 0, 255), 2);

                // Отображение результатов
                HighGui.imshow("WIN", edges);
                HighGui.imshow("RES", sourceImage);

                HighGui.waitKey();
            }

        }catch (Exception e){
            e.printStackTrace();

        }

    }



    public static void main(String[] args) {

//        degrees++;
        addRobotInFrame();
        addCort();
        createButtons();
        createFrame();
        createPanel();
        addBorders();
//        for (int i = 90; i < 180; i++){
//            Timer.sleep(100);
//            degrees = i;
//            System.out.println(i);
//        }

//        new Thread( () ->{
//            while (!Thread.interrupted()){
//                try{
//                    addRobot();
//
//                    if (degrees<=180){
//
//                        degrees++;
//                        System.out.println(degrees);
//
//                    }
//                    Thread.sleep(200);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();
    }


}