package Main;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Value {
    public static double SCALE = 14;
    
    public static double SCENE_WIDTH = 1920;
    public static double SCENE_HIGHT = 700;
    public static double SCALE_X = 1;
    public static double SCALE_Y = 1;
    
    public static double BOARD_Y = 42*SCALE;
    public static double BOARD_SCALE = BOARD_Y/42;
    public static double BOARD_X = 96*BOARD_SCALE*SCALE_X;
    public static double CUTION_SIZE =  6*SCALE;
    public static double BOARD_POSITION_X =  SCENE_WIDTH/2-BOARD_X/2;
    public static double BOARD_POSITION_Y =  SCENE_HIGHT/2-BOARD_Y/2;
    
    public static double BALL_RADIUS = 2.25/1.7*BOARD_SCALE;
    public static double POCKET_RADIUS = 3.5*BOARD_SCALE;
    public static double CUE_LENGTH = 40*BOARD_SCALE;
    public static double CUE_RADIUS = .3*BOARD_SCALE;
    public static double BAULK_LINE = 29*BOARD_SCALE;
    public static double D_RADIUS = 11.5*BOARD_SCALE;
    
    
    
    public static double CUE_BALL_MASS = 6;
    public static double CUE_MAXIMUM_VELOCITY = 100;
    public static double CUE_ANGLE = 0;
    public static double BALL_MASS = 5.5;
    public static double BOARD_FRICTION = .2 ;
    public static int BALL_TOTAL = 16;
    
    
    private double maximumCueVelocity;          
    private double friction;
}
