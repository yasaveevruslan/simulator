
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI {

    private static final String pathArea = "C:\\Users\\1\\IdeaProjects\\LogicSimulatorDriveRep\\simulator-master\\simulator\\src\\testFinal.png";
    private static final String pathRobot = "C:\\Users\\1\\IdeaProjects\\LogicSimulatorDriveRep\\simulator-master\\simulator\\src\\robot.png";

    private BufferedImage myPicture;
    private BufferedImage myPicture2;

    private float degrees = 90f;
    private int robotCoordinateX = 290;
    private int robotCoordinateY = 290;

    private JFrame frame;
    private JPanel panel;
    private JPanel smartPanel;

    private final JToggleButton start = new JToggleButton("START");
    private final JToggleButton stop = new JToggleButton("STOP");
    private final JToggleButton reset = new JToggleButton("RESET");
    private final JToggleButton smartBoard = new JToggleButton("ENABLE");

    private final JTextArea infaCoordinateX = new JTextArea(1, 7);
    private final JTextArea infaCoordinateY = new JTextArea(1, 7);


    private boolean robotMoving = false;
    private boolean resetClicked = false;
    private boolean stopClicked = false;

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
                System.out.println(robotCoordinateX + " " + robotCoordinateY);
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

        smartPanel.add(Box.createVerticalStrut(50));

        smartPanel.add(infaCoordinateX);
        smartPanel.add(infaCoordinateY);

    }


    private void createBlockInfa(){
        Font font = new Font("Arial", Font.BOLD, 16);
        infaCoordinateX.setFont(font);

        infaCoordinateX.setLineWrap(true);
        infaCoordinateX.setEditable(false);

        infaCoordinateY.setFont(font);

        infaCoordinateY.setLineWrap(true);
        infaCoordinateY.setEditable(false);

        Insets padding = new Insets(10, 20, 10, 20); // Верхний, правый, нижний, левый отступы
        infaCoordinateX.setMargin(padding);
        infaCoordinateY.setMargin(padding);


    }

    private void createButtons() {
        start.addActionListener(e -> {
            stopClicked = false;
            resetClicked = false;
            degrees = 90;
            robotMoving = true;
            Timer timer = new Timer(100, e1 -> {
                if ((!stopClicked || !resetClicked) && robotMoving && robotCoordinateX <= 290 + 200) {
                    robotCoordinateX += 10;
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
            degrees = 90;
            robotCoordinateX = 290;
            robotCoordinateY = 290;
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
        if (robotMoving) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.rotate(Math.toRadians(degrees), robotCoordinateX + myPicture2.getWidth() / 2d, robotCoordinateY + myPicture2.getHeight() / 2d);
            g2d.drawImage(myPicture2, robotCoordinateX, robotCoordinateY, null);
            g2d.dispose();
        } else {
            g.drawImage(myPicture2, robotCoordinateX, robotCoordinateY, null);
        }
        infaCoordinateX.setText("X: " + robotCoordinateX);
        infaCoordinateY.setText("Y: " + robotCoordinateY);
    }
}
