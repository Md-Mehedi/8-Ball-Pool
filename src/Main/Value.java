package Main;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Value {
    public static double SCALE = 14;
    
    public static double SCENE_WIDTH = 1920;
    public static double SCENE_HIGHT = 1000;
    public static double SCALE_X = 1;
    public static double SCALE_Y = 1;
    
    public static double BOARD_Y = 588*SCALE/14;
    public static double BOARD_X = 1038*SCALE/14;
    public static double CUTION_SIZE =  4.8*SCALE;
    public static double BOARD_POSITION_CENTER_X =  SCENE_WIDTH/2-BOARD_X/2;
    public static double BOARD_POSITION_CENTER_Y =  SCENE_HIGHT/2-BOARD_Y/2;
    
    public static double BALL_RADIUS = 2.25/2*SCALE;
    public static double POCKET_RADIUS = 1.8*SCALE;
    public static double CUE_LENGTH = 40*SCALE;
    public static double CUE_RADIUS = .3*SCALE;
    public static double BAULK_LINE = 29*SCALE;
    public static double D_RADIUS = 11.5*SCALE;
    
    public static double CUE_BALL_MASS = 6;
    public static double CUE_MAXIMUM_VELOCITY = 60;
    public static double CUE_ANGLE = 0;
    public static double BALL_MASS = 5.5;
    public static double BOARD_FRICTION = .2 ;
    public static int BALL_TOTAL = 16;
    public static String BOARD_PICTURE_LOCATION = "/PictureBall/Board/Board-1.png";
}
