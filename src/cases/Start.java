package cases;

import training.GUI;
import training.RobotContainer;

public class Start implements IState{

//    private GUI g = new GUI();
    @Override
    public boolean execute()
    {

        RobotContainer.el.resetCoordinates(0, 0);
        RobotContainer.el.resetGyro(0);
        RobotContainer.el.setAxisSpeed(0, 0, 0);
        System.out.println(GUI.robotMoving);
        return GUI.robotMoving;
    }
}
