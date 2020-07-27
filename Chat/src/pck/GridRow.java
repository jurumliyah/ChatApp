package pck;

import javafx.scene.layout.RowConstraints;

public class GridRow extends RowConstraints {
	GridRow(int size){
		this.setPercentHeight(100.0 / size);
		this.setFillHeight(true);
	}
	GridRow(int pos, int value){	
		this.setPercentHeight(value);
		this.setFillHeight(true);
        
	}

}
