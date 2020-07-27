package pck;

import javafx.scene.layout.ColumnConstraints;

public class GridCol extends ColumnConstraints {
	GridCol(int size){	
            this.setPercentWidth(100.0 / size);
            this.setFillWidth(true);	
	}
	GridCol(int pos, int value){	
		this.setPercentWidth(value);
        this.setFillWidth(true);	
}
        
}

