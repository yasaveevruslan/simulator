package cases;

import com.sun.tools.javac.Main;
import logic.InitLogic;
import training.RobotContainer;
import training.StateMachine;

import static com.sun.tools.javac.Main.*;

public class Transition implements IState{

    private InitLogic log = RobotContainer.initlogic;

    @Override
    public boolean execute() {
        StateMachine.currentArray = log.indexMas.get(StateMachine.indexElementLogic)[0]+1;
        StateMachine.currentIndex=-1;
        // StateMachine.CurrentArray++;
        StateMachine.indexElementLogic++;
        return true;
    }
}
