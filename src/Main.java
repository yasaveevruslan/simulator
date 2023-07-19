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
                    sm.Time += 0.005f;
                    Thread.sleep(50);
                    System.out.println("array: " + StateMachine.currentArray + " index: " + StateMachine.currentIndex);
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
        sm.ResetStateMachine(GUI.resetClicked);
        sm.Update();
    }
}