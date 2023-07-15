package training;

import logic.InitLogic;

public class RobotContainer {


    public static InitLogic initlogic;

    public RobotContainer() {
        initlogic = new InitLogic(StateMachine.commandLogic);
    }

}
