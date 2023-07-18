package cases;

import logic.InitLogic;
import training.RobotContainer;
import training.StateMachine;

public class ResetLogic implements IState
{

    private InitLogic log = RobotContainer.initlogic;

    @Override
    public boolean execute()
    {
        log.resetLogic();
        RobotContainer.el.setAxisSpeed(0, 0, 0);
        StateMachine.indexElementLogic = 0;
        return true;
    }
}
