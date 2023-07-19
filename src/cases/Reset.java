package cases;

import training.Elements;
import training.RobotContainer;
import training.States;

public class Reset implements IState{

    private float x, y, z;
    private String element;

    private Elements el = RobotContainer.el;

    public Reset(String element, float x, float y, float z)
    {
        this.element = element;
        this.x = x;
        this.y = y;
        this.z = z;
    }


    @Override
    public boolean execute() {
        switch (this.element){
            case States.ALL_RESET:
                el.resetCoordinates(this.x, this.y);
                el.resetGyro(this.z);
                break;

            case States.GYRO_RESET:
                el.resetGyro(this.z);
                break;
        }
        return true;
    }
}
