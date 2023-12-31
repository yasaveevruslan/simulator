package training;

import cases.*;

public class States {


    public static final String ABSOLUTE_ODOMETRY = "absolute";
    public static final String RELATIVE_ODOMETRY = "relative";

    public static final String USE_FRONT_SHARP = "front";
    public static final String USE_BACK_SHARP = "back";
    public static final String USE_RIGHT_SONIC = "right";
    public static final String DEFAULT_SENSOR = "default";

    public static final String ALL_RESET = "all";
    public static final String GYRO_RESET = "gyro";

    public static IState[][] state = new IState[][]{
            {

                    new Start(),
//                    new Odometry(States.ABSOLUTE_ODOMETRY, 0, 650, 0, SmoothEnum.OFF),
                    new Sensors(0, 15, States.USE_RIGHT_SONIC, 0, SmoothEnum.OFF),
                    new Sensors(0, 15, States.USE_BACK_SHARP, 0, SmoothEnum.OFF),
                    new Sensors(15, 15, States.USE_BACK_SHARP, 0, SmoothEnum.OFF),
                    new End(),

//                    new Start(),
//                    new Odometry(States.ABSOLUTE_ODOMETRY, 0, 0, 360, SmoothEnum.OFF),
//                    new Odometry(States.ABSOLUTE_ODOMETRY, 0, 0, -180, SmoothEnum.OFF),
//                    new Odometry(States.ABSOLUTE_ODOMETRY, 0, 0, 0, SmoothEnum.OFF),
//                    new End(),

//                    new Start(),
//                    new Odometry(States.ABSOLUTE_ODOMETRY, 4000, 0, 0, SmoothEnum.OFF),
//                    new Odometry(States.ABSOLUTE_ODOMETRY, 4000, -1500, 0, SmoothEnum.OFF),
//                    new Odometry(States.ABSOLUTE_ODOMETRY, 4000, 2500, 0, SmoothEnum.OFF),
//                    new Odometry(States.ABSOLUTE_ODOMETRY, -1000, 2500, 0, SmoothEnum.OFF),
//                    new Odometry(States.ABSOLUTE_ODOMETRY, 0, 0, 0, SmoothEnum.OFF),
//                    new End(),

            }
    };
}
