package org.dionysius.content;

import java.util.Set;

import org.dionysius.game.Environment;
import org.dionysius.game.ImageExtractor;
import org.dionysius.game.StaticGraphic;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Sykarus extends Environment {

	public static final Sykarus SYKARUS_DAY = new Sykarus(
			new ImageExtractor(new Image("art/tileset/DionysiusDay.png").getPixelReader(), 3), Color.WHEAT);
	public static final Sykarus SYKARUS_NIGHT = new Sykarus(
			new ImageExtractor(new Image("art/tileset/DionysiusNight.png").getPixelReader(), 3), Color.gray(0.125));

	public Sykarus(ImageExtractor extractor, Color background) {
		getGraphics().addAll(Set.of(//
				new StaticGraphic(extractor.extract(448, 0, 511, 63), 380, 20), //
				new StaticGraphic(extractor.extract(80, 16, 127, 63), 50, 180), //
				new StaticGraphic(extractor.extract(193, 80, 271, 127), 120, 210), //
				new StaticGraphic(extractor.extract(304, 112, 511, 118), 0, 300)//
		));
		setBackgroundColor(background);
	}

}
