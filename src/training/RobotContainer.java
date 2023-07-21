package training;

import logic.InitLogic;

public class RobotContainer {


    public static boolean startPosition;
    public static InitLogic initlogic;
    public static Elements el;


    static
    {
        initlogic = new InitLogic(StateMachine.commandLogic);
        el = new Elements();
        startPosition = true;
    }

}
