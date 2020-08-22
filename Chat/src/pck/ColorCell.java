package pck;

import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class ColorCell extends ListCell<String> {
	String str;
	String color;
	ColorCell(String str, String color){
		this.str = str;
		this.color = color;
	}
	ColorCell(){}
	
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(null);
        } else {
            setText(item);
            if (item.equals(str)) {
                setStyle("-fx-control-inner-background: " + Window.getColorCode(color) + ";");
            } else {
                setStyle("");
            }
        }
    }
}