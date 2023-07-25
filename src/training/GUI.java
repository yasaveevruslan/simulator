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

    protected static final String PATH_AREA = "src/paint.png";
    protected static final String PATH_AREA_COUNTER = "src/paint.png";
    private static final String PATH_ROBOT = "src/none.png";
    private BufferedImage myPicture;
    private BufferedImage myPicture2;

    private JFrame frame;
    private JPanel panel;
    private JPanel smartPanel;

    private final JToggleButton start = new JToggleButton("START");
    private final JToggleButton stop = new JToggleButton("STOP");
    private final JToggleButton reset = new JToggleButton("RESET");
    private final JToggleButton smartBoard = new JToggleButton("ENABLE");

    private final JTextArea infoCoordinateX = new JTextArea(1, 7);
    private final JTextArea infoCoordinateY = new JTextArea(1, 7);
    private final JTextArea infoCoordinateZ = new JTextArea(1, 7);

    private final JTextArea infoModule = new JTextArea(1, 7);

    private final JTextArea infoArray = new JTextArea(1, 7);
    private final JTextArea infoIndex = new JTextArea(1, 7);

    private final JTextArea infoFrontUS = new JTextArea(1, 7);
    private final JTextArea infoRightUS = new JTextArea(1, 7);
    private final JTextArea infoFrontIR = new JTextArea(1, 7);
    private final JTextArea infoBackIR = new JTextArea(1, 7);

    public static boolean robotMoving;
    public static boolean resetClicked;
    public static boolean stopClicked;

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
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
            }
        };
        panel.setLayout(new OverlayLayout(panel));
        panel.setPreferredSize(new Dimension(1200, 800));
        panel.setBackground(new Color(68, 65, 65));
    }

    private void createSmartPanel() {
        createBlockInfo();
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

        smartPanel.add(infoCoordinateX);
        smartPanel.add(infoCoordinateY);
        smartPanel.add(infoCoordinateZ);

        smartPanel.add(infoModule);

        smartPanel.add(infoArray);
        smartPanel.add(infoIndex);

        smartPanel.add(infoFrontUS);
        smartPanel.add(infoRightUS);
        smartPanel.add(infoFrontIR);
        smartPanel.add(infoBackIR);

    }


    private void createBlockInfo(){
        Font font = new Font("Arial", Font.BOLD, 16);

        infoCoordinateX.setFont(font);
        infoCoordinateY.setFont(font);
        infoCoordinateZ.setFont(font);

        infoModule.setFont(font);

        infoArray.setFont(font);
        infoIndex.setFont(font);

        infoFrontUS.setFont(font);
        infoRightUS.setFont(font);
        infoFrontIR.setFont(font);
        infoBackIR.setFont(font);

        infoCoordinateX.setLineWrap(true);
        infoCoordinateX.setEditable(false);

        infoCoordinateY.setLineWrap(true);
        infoCoordinateY.setEditable(false);

        infoCoordinateZ.setLineWrap(true);
        infoCoordinateZ.setEditable(false);


        infoModule.setLineWrap(true);
        infoModule.setEditable(false);


        infoArray.setLineWrap(true);
        infoArray.setEditable(false);

        infoIndex.setLineWrap(true);
        infoIndex.setEditable(false);


        infoFrontUS.setLineWrap(true);
        infoFrontUS.setEditable(false);

        infoRightUS.setLineWrap(true);
        infoRightUS.setEditable(false);

        infoFrontIR.setLineWrap(true);
        infoFrontIR.setEditable(false);

        infoBackIR.setLineWrap(true);
        infoBackIR.setEditable(false);

        Insets padding = new Insets(10, 20, 10, 20); // Верхний, правый, нижний, левый отступы
        infoCoordinateX.setMargin(padding);
        infoCoordinateY.setMargin(padding);
        infoCoordinateZ.setMargin(padding);

        infoModule.setMargin(padding);

        infoArray.setMargin(padding);
        infoIndex.setMargin(padding);

        infoFrontUS.setMargin(padding);
        infoRightUS.setMargin(padding);
        infoFrontIR.setMargin(padding);
        infoBackIR.setMargin(padding);

    }

    private void createButtons() {
        start.addActionListener(e -> {
            stopClicked = false;
            resetClicked = false;
            robotMoving = true;
            Timer timer = new Timer(50, e1 -> {
                if ((!stopClicked || !resetClicked) && robotMoving) {
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
                smartPanel.setVisible(!smartPanel.isVisible());
                panel.repaint();

            });


    }

    private void loadImages() {
        try {
            myPicture = ImageIO.read(new File(PATH_AREA));
            myPicture2 = ImageIO.read(new File(PATH_ROBOT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addRobotInFrame(Graphics g) {
        LogicBorders board = new LogicBorders();

        if (RobotContainer.startPosition) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.rotate(Math.toRadians(Elements.positionZRobot), Elements.positionXRobot + myPicture2.getWidth() / 2d, Elements.positionYRobot + myPicture2.getHeight() / 2d);
            g2d.drawImage(myPicture2, Elements.positionXRobot, Elements.positionYRobot, null);
            g2d.dispose();
        } else {
            g.drawImage(myPicture2, Elements.positionXRobot, Elements.positionYRobot, null);
        }

//        board.findNearestUpContours(Elements.positionXRobot,Elements.positionYRobot);
        board.calculateBorders(Elements.positionXRobot,Elements.positionYRobot);

        infoCoordinateX.setText("X: " + String.format("%.2f", Elements.positionX));
        infoCoordinateY.setText("Y: " + String.format("%.2f", Elements.positionY));
        infoCoordinateZ.setText("Z: " + String.format("%.2f", Elements.angle));

        infoModule.setText("Module: " + StateMachine.commandLogic);

        infoArray.setText("Array: " + StateMachine.currentArray);
        infoIndex.setText("Index: " + StateMachine.currentIndex);

        infoFrontUS.setText("FrontUS: " + String.format("%.2f", Elements.frontUS));
        infoRightUS.setText("RightUS: " + String.format("%.2f", Elements.rightUS));
        infoFrontIR.setText("FrontIR: " + String.format("%.2f", Elements.frontIR));
        infoBackIR.setText("BackIR: " + String.format("%.2f", Elements.backIR));


    }
}
