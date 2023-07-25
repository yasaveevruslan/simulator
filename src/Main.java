import training.GUI;
import training.RobotContainer;
import training.StateMachine;

public class Main
{


    static StateMachine sm = new StateMachine();

    public static void main(String[] args)
    {

        GUI.showGUI();

        new Thread( () ->
        {
            while (!Thread.interrupted())
            {
                try
                {
                    initialize();
                    StateMachine.time += 0.005f;
                    Thread.sleep(50);

//                    StateMachine.time += 1f;
//                    Thread.sleep(1000);
//                    System.out.println("array: " + StateMachine.currentArray + " index: " + StateMachine.currentIndex);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void initialize()
    {
        new RobotContainer();
        RobotContainer.el.calculateCoordinates();
        RobotContainer.el.calculateSensors();
        RobotContainer.el.resetButton(GUI.resetClicked);
        sm.resetStateMachine(GUI.resetClicked);
        sm.update();
        RobotContainer.el.checkErrorPosition();
        RobotContainer.el.setPositionOnWindow();
    }
}