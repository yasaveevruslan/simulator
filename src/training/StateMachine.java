package training;

public class StateMachine {

    public static int currentArray = 0;
    public static int currentIndex = 0;
    public static boolean isFirst = true;

    public static String commandLogic = "F";
    public static int numberPalace = 0;
    public static int indexElementLogic = 0;

    public static float startTime = 0;
    public static float time = 0;


    public void update()
    {
        if (States.state[currentArray][currentIndex].execute())
        {
            isFirst = true;
            startTime = time;
            currentIndex++;
        }
    }

    public void resetStateMachine(boolean reset)
    {
        if (reset)
        {
            currentArray = 0;
            currentIndex = 0;
            startTime = time;
            isFirst = false;
        }

    }
}
