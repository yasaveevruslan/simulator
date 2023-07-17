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

    private static final String pathArea = "C:\\Users\\1\\Desktop\\simulator-master\\simulator-master\\src\\testFinal.png";
    private static final String pathRobot = "C:\\Users\\1\\Desktop\\simulator-master\\simulator-master\\src\\robot.png";

    static BufferedImage myPicture = null;
    static BufferedImage myPicture2 = null;

    private static float degrees = 90f;
    private static int robotCoordinateX = 290;
    private static int robotCoordinateY = 290;
    static JFrame frame;
    static JPanel panel;
    static JButton start = new JButton("start");
    static JButton stop = new JButton("stop");
    static JButton reset = new JButton("reset");
    static boolean robotMoving = false;
    static boolean resetClicked = false;
    static boolean stopClicked = false;

    public static void main(String[] args)
    {
        //полезный метод для графического интерфейса. Обновляется в отдельном потоке событий.
        SwingUtilities.invokeLater(Main::settingsGUI);

    }

    public static void settingsGUI()
    {
        //основной метод для создания и взаимодействия окна
        createFrame();
        createPanel();
        loadImages();
        createButtons();
        frame.setVisible(true);
    }

    public static void createFrame()
    {
        //метод для работы с окном, в случае измененния окна использовать этот метод
        frame = new JFrame("DEMO");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void createPanel()
    {
        //метод для работы с контейнром, в случае измененния контейнром использовать этот метод
        // JPanel представляет контейнер для группировки других компонентов.
        //Он используется для создания областей или панелей внутри окна, на которые можно добавлять и располагать другие компоненты.

        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                //Метод для рисования компонентов на панели
                super.paintComponent(g);
                g.drawImage(myPicture, 50, 50, this);
                addRobotInFrame(g);
                System.out.println(robotCoordinateX + " " + robotCoordinateY);
            }
        };
        panel.setBounds(0, 0, 1200, 800);
        panel.setBackground(new Color(68, 65, 65));
        frame.add(panel);
        panel.setLayout(null);
    }

    public static void createButtons()
    {
        start.setBounds(250, 600, 100, 50);
        stop.setBounds(550, 600, 100, 50);
        reset.setBounds(850, 600, 100, 50);

        start.setBackground(new Color(124, 176, 176));
        stop.setBackground(new Color(124, 176, 176));
        reset.setBackground(new Color(124, 176, 176));

        //Далее идут слушатели для кнопок. При нажатии на кнопки происходит взаимодествие с роботом
        start.addActionListener(e -> {
            stopClicked = false;
            resetClicked= false;
            degrees = 90;
            robotMoving = true;
            Timer timer = new Timer(100, e1 -> {
                if ((!stopClicked || !resetClicked) && robotMoving && robotCoordinateX <= 290 + 200)
                {
                    robotCoordinateX += 10;
                    panel.repaint();
                }
                else
                {
                    ((Timer)e1.getSource()).stop();
                    robotMoving = false;

                }
            });
            timer.setRepeats(true);
            timer.start();
        });

        stop.addActionListener(e -> {
            panel.repaint();
            stopClicked = true;
            robotMoving = false;

        });

        reset.addActionListener(e -> {
            degrees = 90;
            robotCoordinateX = 290;
            robotCoordinateY = 290;
            panel.repaint();
            resetClicked = true;
            robotMoving = false;
        });

        // Добавление самих кнопок на панель
        panel.add(start);
        panel.add(stop);
        panel.add(reset);

    }

    public static void loadImages()
    {
        try
        {
            myPicture = ImageIO.read(new File(pathArea));
            myPicture2 = ImageIO.read(new File(pathRobot));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void addRobotInFrame(Graphics g)
    {
        if (robotMoving)
        {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.rotate(Math.toRadians(degrees), robotCoordinateX + myPicture2.getWidth() / 2d, robotCoordinateY + myPicture2.getHeight() / 2d);
            g2d.drawImage(myPicture2, robotCoordinateX, robotCoordinateY, null);
            g2d.dispose();
        }
        else
        {
            g.drawImage(myPicture2, robotCoordinateX, robotCoordinateY, null);
        }
    }

//    private static void addAction() {
//    }



    private static void addBorders()
    {
        //Метод для распознавания стенок на поле
        try
        {
            //вставка полного пути к файлу
            System.load("C:\\Users\\1\\Desktop\\simulator-master\\simulator-master\\src\\opencv_java440.dll");

            Mat sourceImage = Imgcodecs.imread(pathArea);

            if (sourceImage.empty())
            {
                System.out.println("Не удалось загрузить изображение.");
            }
            else
            {
                // Применение алгоритма Canny
                Imgproc.cvtColor(sourceImage, sourceImage, Imgproc.COLOR_BGR2GRAY);
                Mat edges = new Mat();
                Imgproc.Canny(sourceImage, edges, 100, 200, 5);

                List<MatOfPoint> contours = new ArrayList<>();
                Mat hierarchy = new Mat();
                Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

                Imgproc.drawContours(sourceImage, contours, -1, new Scalar(0, 0, 255), 2);

                // Отображение результатов
                HighGui.imshow("CannyMethod", edges);
                HighGui.imshow("Borders", sourceImage);

                HighGui.waitKey();
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}