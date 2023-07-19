package cases;

import logic.InitLogic;
import training.RobotContainer;
import training.StateMachine;

public class InitializeLogic implements IState{

    private InitLogic log = RobotContainer.initlogic;

    @Override
    public boolean execute()
    {

        if(StateMachine.commandLogic.equals("F")){
            log.addOrders(StateMachine.numberPalace);
            log.initLogic(StateMachine.numberPalace);
        }else if(StateMachine.commandLogic.equals("E")){
            log.initLogic();
        }else{
            log.initCmodule();
        }

        RobotContainer.el.setAxisSpeed(0, 0, 0);
        StateMachine.numberPalace++;
        return true;
    }
}
