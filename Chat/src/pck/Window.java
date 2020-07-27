package pck;
import java.awt.Color;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class Window {
	String redcode = "#3d0100;";
	String greencode = "#276932;";
	String bluecode = "#282b4d;";
	String defaultcolorcode = "#e6e6e6;"; 
	ArrayList <String> users = new ArrayList();
	ArrayList <String> rooms = new ArrayList();

	
	BorderPane createWindow() {
		BorderPane window = new BorderPane();
		GridPane colors = new GridPane();
		GridPane right = new GridPane();
		GridPane bottom = new GridPane();
		GridPane center = new GridPane();
		
		Text text = new Text("eeee");
		ScrollPane scrollpane = new ScrollPane();
		ScrollPane scrollpane2 = new ScrollPane();
		ScrollPane scrollpane3 = new ScrollPane();
		scrollpane.setContent(text);
		center.getColumnConstraints().add(new GridCol(1));
		center.getRowConstraints().add(new GridRow(1));
		center.add(scrollpane, 0, 0);

		
		scrollpane2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		scrollpane2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		right.getRowConstraints().add(new GridRow(0,35));
		right.getRowConstraints().add(new GridRow(1,65));
		right.getColumnConstraints().add(new GridCol(1));
		right.add(scrollpane2, 0, 0);
		right.add(scrollpane3, 0, 1);
		right.setMinWidth(120);
	
		//CREATING  BOTTOM PANE WITH SEND BUTTON AND TEXTFIELD//
		TextField type = new TextField();
		Button button = new Button("Send");
		type.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		bottom.getRowConstraints().add(new GridRow(1));
		bottom.getColumnConstraints().add(new GridCol(0,80));
		bottom.getColumnConstraints().add(new GridCol(1,20));
		bottom.add(type, 0, 0);
		bottom.add(button, 1, 0);
		//bottom.setAlignment(Pos.CENTER);
		bottom.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		bottom.setPadding(new Insets(0,120,0,0));
        
		Button red = new Button("click");
		Button blue = new Button("click");
		Button green = new Button("click");
		Button defaultcolor = new Button("click");
		red.setStyle  ("-fx-background-color :" + redcode);
		blue.setStyle("-fx-background-color :" + bluecode);
		green.setStyle("-fx-background-color :" + greencode);
		defaultcolor.setStyle("-fx-background-color :" + defaultcolorcode);

        red.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        blue.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        green.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        defaultcolor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        
        red.setOnAction(e -> {scrollpane.setStyle("-fx-background-color:" + redcode + "-fx-background:" + redcode);
        text.setStyle("-fx-fill:white;");
        });
        blue.setOnAction(e -> {scrollpane.setStyle("-fx-background-color:" + bluecode + "-fx-background:" + bluecode);
        text.setStyle("-fx-fill:white;");
        });
        green.setOnAction(e -> {scrollpane.setStyle("-fx-background-color:" + greencode + "-fx-background:" + greencode);
        text.setStyle("-fx-fill:white;");
        });
        defaultcolor.setOnAction(e -> {scrollpane.setStyle("-fx-background-color:" + defaultcolorcode + "-fx-background:" + defaultcolorcode);
        text.setStyle("-fx-fill:black;");
        });
        
        for (int i = 0; i<4; i++) {colors.getColumnConstraints().add(new GridCol(4));}
		colors.getRowConstraints().add(new GridRow(1));
		colors.add(defaultcolor, 0, 0);
		colors.add(blue, 1, 0);
        colors.add(red, 2, 0);
        colors.add(green, 3, 0);
        colors.setPadding(new Insets(0,120,0,0));
		
		//for (int i = 0; i<2; i++) {right.getRowConstraints().add(new GridRow(2));}
		
        
		window.setTop(colors);
		window.setCenter(center);
		window.setRight(right);
		window.setBottom(bottom);

		
		return window;
	}

}
