package Main;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Value {

      public static boolean WORK_WITH_NETWORK = false;
      
      public static double SCALE = 8;

      public static double SCENE_WIDTH = 1910 * SCALE/17;
      public static double SCENE_HIGHT = 990 * SCALE/17;
      public static double SCALE_X = 1;
      public static double SCALE_Y = 1;

      public static double BOARD_Y = 588 * SCALE / 14;
      public static double BOARD_X = 1038 * SCALE / 14;
      public static double CUTION_SIZE = 4.676 * SCALE;
      public static double BOARD_POSITION_CENTER_X = SCENE_WIDTH / 2 - BOARD_X / 2;
      public static double BOARD_POSITION_CENTER_Y = SCENE_HIGHT / 2 - BOARD_Y / 2 + 8*SCALE;

      public static double BALL_RADIUS = 2.25 / 2.2 * SCALE;
      public static double POCKET_RADIUS = 6 * SCALE;
      public static double CUE_LENGTH = 40 * SCALE;
      public static double CUE_RADIUS = .3 * SCALE;
      public static double BAULK_LINE = 20.875 * SCALE;
      public static double D_RADIUS = 11.5 * SCALE;

      public static double CUE_BALL_MASS = 6;
      public static double CUE_MAXIMUM_VELOCITY = 60;
      public static double CUE_ANGLE = 0;
      public static double BALL_MASS = 5.5;
      public static double BOARD_FRICTION = 0.2;
      public static int BALL_TOTAL = 16;
      public static String BOARD_PICTURE_LOCATION = "/PictureBall/Board/Board-1.png";

      public static double pocketX1 = SCENE_WIDTH/2 - 540*SCALE/17 + BALL_RADIUS;
      public static double pocketX2 = SCENE_WIDTH/2 - 30*SCALE/17;
      public static double pocketX3 = SCENE_WIDTH/2 + 30*SCALE/17;
      public static double pocketX4 = SCENE_WIDTH/2 + 540*SCALE/17 - BALL_RADIUS;
      public static double pocketY1 = BOARD_POSITION_CENTER_Y + 120*SCALE/17 - BALL_RADIUS;
      public static double pocketY2 = BOARD_POSITION_CENTER_Y +BOARD_Y - 120*SCALE/17 + BALL_RADIUS;
      public static String CUE_PICTURE_LOCATION = "/PictureBall/Cue/Cue-4.png";
      
      public static double slope(double x1, double y1, double x2, double y2) {
            if (x2 - x1 >= 0) {
                  return Math.toDegrees(Math.atan((y2 - y1) / (x2 - x1)));
            }
            return 180 + Math.toDegrees(Math.atan((y2 - y1) / (x2 - x1)));
      }
}
