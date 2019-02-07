package Main;

/**
 *
 * @author Md Mehedi Hasan
 */
public interface Value {

      boolean MY_TURN = true;
      
      public static double SCALE = 11;

      public static double SCENE_WIDTH = 1920 / 1.3;
      public static double SCENE_HIGHT = 1000 / 1.3;
      public static double SCALE_X = 1;
      public static double SCALE_Y = 1;

      public static double BOARD_Y = 588 * SCALE / 14;
      public static double BOARD_X = 1038 * SCALE / 14;
      public static double CUTION_SIZE = 4.8 * SCALE;
      public static double BOARD_POSITION_CENTER_X = SCENE_WIDTH / 2 - BOARD_X / 2;
      public static double BOARD_POSITION_CENTER_Y = SCENE_HIGHT / 2 - BOARD_Y / 2;

      public static double BALL_RADIUS = 2.25 / 2 * SCALE;
      public static double POCKET_RADIUS = 1.8 * SCALE;
      public static double CUE_LENGTH = 40 * SCALE;
      public static double CUE_RADIUS = .3 * SCALE;
      public static double BAULK_LINE = 29 * SCALE;
      public static double D_RADIUS = 11.5 * SCALE;

      public static double CUE_BALL_MASS = 6;
      public static double CUE_MAXIMUM_VELOCITY = 80;
      public static double CUE_ANGLE = 0;
      public static double BALL_MASS = 5.5;
      public static double BOARD_FRICTION = .2;
      public static int BALL_TOTAL = 16;
      public static String BOARD_PICTURE_LOCATION = "/PictureBall/Board/Board-1.png";

      public static double pocketX1 = 410 * SCALE / 18;
      public static double pocketX2 = 930 * SCALE / 18;
      public static double pocketX3 = 988 * SCALE / 18;
      public static double pocketX4 = 1512 * SCALE / 18;
      public static double pocketY1 = 243 * SCALE / 18;
      public static double pocketY2 = 750 * SCALE / 18;
//    public static double pocketX1 = 0;
//    public static double pocketX2 = 0;
//    public static double pocketX3 = 0;
//    public static double pocketX4 = 0;
//    public static double pocketY1 = 0;
//    public static double pocketY2 = 0;
      public static String CUE_PICTURE_LOCATION = "/PictureBall/Cue/Cue-4.png";

//    public static double round(double value, int offset){
//          value = 1234567.89012;
//          offset = 2;
//          int a = (int)value*10;
//          value = value/100.0;
//    }
}
