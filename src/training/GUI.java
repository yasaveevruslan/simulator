package training;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI {

    private static final String pathArea = "src/testFinal.png";
    private static final String pathRobot = "src/none.png";

    private BufferedImage myPicture;
    private BufferedImage myPicture2;



    private JFrame frame;
    private JPanel panel;
    private JPanel smartPanel;

    private final JToggleButton start = new JToggleButton("START");
    private final JToggleButton stop = new JToggleButton("STOP");
    private final JToggleButton reset = new JToggleButton("RESET");
    private final JToggleButton smartBoard = new JToggleButton("ENABLE");

    private final JTextArea infaCoordinateX = new JTextArea(1, 7);
    private final JTextArea infaCoordinateY = new JTextArea(1, 7);
    private final JTextArea infaCoordinateZ = new JTextArea(1, 7);

    private final JTextArea infaModul = new JTextArea(1, 7);

    private final JTextArea infaArray = new JTextArea(1, 7);
    private final JTextArea infaIndex = new JTextArea(1, 7);

    private final JTextArea infaFrontUS = new JTextArea(1, 7);
    private final JTextArea infaRightUS = new JTextArea(1, 7);
    private final JTextArea infaFrontIR = new JTextArea(1, 7);
    private final JTextArea infaBackIR = new JTextArea(1, 7);


    public static boolean robotMoving = false;
    public static boolean resetClicked = false;
    public static boolean stopClicked = false;

    public static void showGUI() {
        GUI gui = new GUI();
        gui.settingsGUI();
    }

    private void settingsGUI() {
        loadImages();

        createFrame();
        createPanel();
        frame.add(panel);
        frame.setVisible(true);
    }

    private void createFrame() {
        frame = new JFrame("DEMO");
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createSmartPanel();
        createButtons();
        frame.setLayout(new BorderLayout());
        frame.add(smartPanel, BorderLayout.EAST);
        frame.add(createButtonPanel(), BorderLayout.SOUTH);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        start.setPreferredSize(new Dimension(150,42));
        stop.setPreferredSize(new Dimension(150,42));
        reset.setPreferredSize(new Dimension(150,42));
        smartBoard.setPreferredSize(new Dimension(150,42));

        start.setBackground(new Color(127, 223, 66));
        stop.setBackground(new Color(205, 52, 52));
        reset.setBackground(new Color(245, 169, 91));
        smartBoard.setBackground(new Color(124, 176, 176));

        start.setForeground(Color.black);
        stop.setForeground(Color.black);
        reset.setForeground(Color.black);
        smartBoard.setForeground(Color.black);

        Font font = new Font("Arial", Font.BOLD, 16);
        start.setFont(font);
        stop.setFont(font);
        reset.setFont(font);
        smartBoard.setFont(font);


        buttonPanel.add(start);
        buttonPanel.add(stop);
        buttonPanel.add(reset);
        buttonPanel.add(smartBoard);

        return buttonPanel;
    }

    private void createPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(myPicture, 50, 50, this);
                addRobotInFrame(g);
//                System.out.println(Elements.positionXRobot + " " + Elements.positionYRobot);
            }
        };
        panel.setLayout(new OverlayLayout(panel));
        panel.setPreferredSize(new Dimension(1200, 800));
        panel.setBackground(new Color(68, 65, 65));
    }

    private void createSmartPanel() {
        createBlockInfa();
        smartPanel = new JPanel();
        smartPanel.setPreferredSize(new Dimension(400, 100));
        smartPanel.setBackground(Color.gray);

        smartPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                smartPanel.setVisible(!smartPanel.isVisible());
                frame.revalidate();
                frame.repaint();
            }
        });

//        smartPanel.add(Box.createVerticalStrut(50));

        smartPanel.add(infaCoordinateX);
        smartPanel.add(infaCoordinateY);
        smartPanel.add(infaCoordinateZ);

        smartPanel.add(infaModul);

        smartPanel.add(infaArray);
        smartPanel.add(infaIndex);

        smartPanel.add(infaFrontUS);
        smartPanel.add(infaRightUS);
        smartPanel.add(infaFrontIR);
        smartPanel.add(infaBackIR);

    }


    private void createBlockInfa(){
        Font font = new Font("Arial", Font.BOLD, 16);

        infaCoordinateX.setFont(font);
        infaCoordinateY.setFont(font);
        infaCoordinateZ.setFont(font);

        infaModul.setFont(font);

        infaArray.setFont(font);
        infaIndex.setFont(font);

        infaFrontUS.setFont(font);
        infaRightUS.setFont(font);
        infaFrontIR.setFont(font);
        infaBackIR.setFont(font);

        infaCoordinateX.setLineWrap(true);
        infaCoordinateX.setEditable(false);

        infaCoordinateY.setLineWrap(true);
        infaCoordinateY.setEditable(false);

        infaCoordinateZ.setLineWrap(true);
        infaCoordinateZ.setEditable(false);


        infaModul.setLineWrap(true);
        infaModul.setEditable(false);


        infaArray.setLineWrap(true);
        infaArray.setEditable(false);

        infaIndex.setLineWrap(true);
        infaIndex.setEditable(false);


        infaFrontUS.setLineWrap(true);
        infaFrontUS.setEditable(false);

        infaRightUS.setLineWrap(true);
        infaRightUS.setEditable(false);

        infaFrontIR.setLineWrap(true);
        infaFrontIR.setEditable(false);

        infaBackIR.setLineWrap(true);
        infaBackIR.setEditable(false);

        Insets padding = new Insets(10, 20, 10, 20); // Верхний, правый, нижний, левый отступы
        infaCoordinateX.setMargin(padding);
        infaCoordinateY.setMargin(padding);
        infaCoordinateZ.setMargin(padding);

        infaModul.setMargin(padding);

        infaArray.setMargin(padding);
        infaIndex.setMargin(padding);

        infaFrontUS.setMargin(padding);
        infaRightUS.setMargin(padding);
        infaFrontIR.setMargin(padding);
        infaBackIR.setMargin(padding);

    }

    private void createButtons() {
        start.addActionListener(e -> {
            stopClicked = false;
            resetClicked = false;
//            degrees = 90;
            robotMoving = true;
            Timer timer = new Timer(50, e1 -> {
                if ((!stopClicked || !resetClicked) && robotMoving) {
//                if (robotMoving) {
                    panel.repaint();
                } else {
                    ((Timer) e1.getSource()).stop();
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
            Elements.positionZRobot = 90;
            Elements.positionXRobot = 290;
            Elements.positionYRobot = 290;
            Elements.positionX = 0;
            Elements.positionY = 0;
            Elements.angle = 0;

            StateMachine.currentArray = 0;
            StateMachine.currentIndex = 0;
            panel.repaint();
            resetClicked = true;
            robotMoving = false;
        });



            smartBoard.addActionListener(e -> {
                System.out.println(smartPanel.isVisible());
                if(!smartPanel.isVisible()) {
                    smartPanel.setVisible(true);
                }else {
                    smartPanel.setVisible(false);

                }
                panel.repaint();

            });


    }

    private void loadImages() {
        try {
            myPicture = ImageIO.read(new File(pathArea));
            myPicture2 = ImageIO.read(new File(pathRobot));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addRobotInFrame(Graphics g) {
        if (RobotContainer.startPosition) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.rotate(Math.toRadians(Elements.positionZRobot), Elements.positionXRobot + myPicture2.getWidth() / 2d, Elements.positionYRobot + myPicture2.getHeight() / 2d);
            g2d.drawImage(myPicture2, Elements.positionXRobot, Elements.positionYRobot, null);
            g2d.dispose();
        } else {
            g.drawImage(myPicture2, Elements.positionXRobot, Elements.positionYRobot, null);
        }
        infaCoordinateX.setText("X: " + String.format("%.4f", Elements.positionX));
        infaCoordinateY.setText("Y: " + String.format("%.4f", Elements.positionY));
        infaCoordinateZ.setText("Z: " + String.format("%.4f", Elements.angle));

        infaModul.setText("modul: " + StateMachine.commandLogic);

        infaArray.setText("Array: " + StateMachine.currentArray);
        infaIndex.setText("Array: " + StateMachine.currentIndex);

        infaFrontUS.setText("FrontUS: " + String.format("%.4f", Elements.frontUS));
        infaRightUS.setText("RightUS: " + String.format("%.4f", Elements.rightUS));
        infaFrontIR.setText("FrontIR: " + String.format("%.4f", Elements.frontIR));
        infaBackIR.setText("BackIR: " + String.format("%.4f", Elements.backIR));


    }
}
