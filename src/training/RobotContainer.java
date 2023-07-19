package training;

import logic.InitLogic;

public class RobotContainer {


    public static boolean startPosition = false;
    public static InitLogic initlogic;
    public static Elements el;


    public RobotContainer() {

        initlogic = new InitLogic(StateMachine.commandLogic);
        el = new Elements();
        startPosition = true;
    }

}
