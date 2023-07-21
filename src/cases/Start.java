package cases;

import training.GUI;
import training.RobotContainer;

public class Start implements IState{

    @Override
    public boolean execute()
    {
        RobotContainer.el.resetCoordinates(0, 0);
        RobotContainer.el.resetGyro(0);
        RobotContainer.el.setAxisSpeed(0, 0, 0);
        return GUI.robotMoving;
    }
}
