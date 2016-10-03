package probe;

import com.vaadin.shared.ui.colorpicker.Color;

public class ColorAWT2Fx {

    public static Color convert(java.awt.Color color) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue());
    }

    /*
     * public static Font convert(java.awt.Font font) { return
     * javafx.scene.text.Font.font(font.getFontName(),font.getSize2D()); }
     */
}
