package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Md Mehedi Hasan
 */
public class Board {
    Pane pane;
    List<Pocket> pockets;
    Point2D start;
    Point2D end;

    public Board(Pane pane) {
        this.pane = pane;
        pockets = new ArrayList<>();
        start = new Point2D(Value.BOARD_POSITION_CENTER_X,Value.BOARD_POSITION_CENTER_Y);
        end = new Point2D(Value.BOARD_POSITION_CENTER_X+Value.BOARD_X,Value.BOARD_POSITION_CENTER_Y+Value.BOARD_Y);
        pockets = new ArrayList<Pocket>();
//        prepareBoard();
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public List<Pocket> getPockets() {
        return pockets;
    }

    public void setPockets(List<Pocket> pockets) {
        this.pockets = pockets;
    }

    public Point2D getStart() {
        return start;
    }

    public void setStart(Point2D boardStart) {
        this.start = boardStart;
    }

    public Point2D getEnd() {
        return end;
    }

    public void setEnd(Point2D boardEnd) {
        this.end = boardEnd;
    }
    void drawBoard() throws IOException{
        Parent board = (AnchorPane)FXMLLoader.load(getClass().getResource("Board.fxml"));  
        pane.getChildren().add(board);
        board.setLayoutX(Value.BOARD_POSITION_CENTER_X);
        board.setLayoutY(Value.BOARD_POSITION_CENTER_Y);
        
        preparePocket();
    }
    void drawBoard1() {
        Image board = new Image(getClass().getResourceAsStream("/PictureBall/Board.png"));
        
        StackPane imageContainer = new StackPane();
        imageContainer.setMaxSize(Value.BOARD_X, Value.BOARD_Y);
        imageContainer.setPrefSize(Value.BOARD_X, Value.BOARD_Y);
        imageContainer.setMinSize(Value.BOARD_X, Value.BOARD_Y);
        imageContainer.setLayoutX(Value.BOARD_POSITION_CENTER_X);
        imageContainer.setLayoutY(Value.BOARD_POSITION_CENTER_Y);
        Rectangle back = new Rectangle(Value.BOARD_X+Value.CUTION_SIZE,Value.BOARD_Y+Value.CUTION_SIZE);
        
        Image image = new Image(getClass().getResourceAsStream("/PictureBall/Board/Board-1.png"));
        ImageView imageView = new ImageView(image);
        
        imageContainer.getChildren().addAll(back,imageView);
        
        back.setFill(Color.BROWN);
//        Rectangle front = new Rectangle(Value.BOARD_X,Value.BOARD_Y);
//        front.setFill(Color.GREEN);
        
//        front.setLayoutX(Value.BOARD_POSITION_X);
//        front.setLayoutY(Value.BOARD_POSITION_Y);
        
        back.setLayoutX(Value.BOARD_POSITION_CENTER_X-Value.CUTION_SIZE/2);
        back.setLayoutY(Value.BOARD_POSITION_CENTER_Y-Value.CUTION_SIZE/2);
        pane.getChildren().addAll(imageContainer);
        
//        PhongMaterial material = new PhongMaterial();
//        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/PictureBall/Board/Board-1.png")));
 
        
        preparePocket();
        
        Line baulkLine = new Line(Value.BOARD_POSITION_CENTER_X+Value.BAULK_LINE, Value.BOARD_POSITION_CENTER_Y, Value.BOARD_POSITION_CENTER_X+Value.BAULK_LINE, Value.BOARD_POSITION_CENTER_Y+Value.BOARD_Y);
        baulkLine.setFill(Color.WHITE);
        pane.getChildren().add(baulkLine);
        baulkLine.setStyle("-fx-stroke: white;");
    }

    private void preparePocket() {
        for(int i=0;i<6;i++){
            pockets.add(new Pocket(pane,new Point2D(0,0)));
        }
        pockets.get(0).setLayout(new Point2D(Value.BOARD_POSITION_CENTER_X, Value.BOARD_POSITION_CENTER_Y));
        pockets.get(1).setLayout(new Point2D(Value.BOARD_POSITION_CENTER_X, Value.BOARD_POSITION_CENTER_Y+Value.BOARD_Y));
        pockets.get(2).setLayout(new Point2D(Value.BOARD_POSITION_CENTER_X + Value.BOARD_X/2, Value.BOARD_POSITION_CENTER_Y));
        pockets.get(3).setLayout(new Point2D(Value.BOARD_POSITION_CENTER_X + Value.BOARD_X/2, Value.BOARD_POSITION_CENTER_Y + Value.BOARD_Y));
        pockets.get(4).setLayout(new Point2D(Value.BOARD_POSITION_CENTER_X + Value.BOARD_X, Value.BOARD_POSITION_CENTER_Y));
        pockets.get(5).setLayout(new Point2D(Value.BOARD_POSITION_CENTER_X + Value.BOARD_X, Value.BOARD_POSITION_CENTER_Y + Value.BOARD_Y));
//        pockets.get(0).setLayout(new Point2D(Value.BOARD_POSITION_X + Value.BOARD_X/2, Value.BOARD_POSITION_Y + Value.BOARD_Y/2));
    }
}
