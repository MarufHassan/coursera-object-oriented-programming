import edu.duke.*;
import java.io.File;

public class GrayScaleConverter {
	public ImageResource makeInversion(ImageResource inImage) {
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

		for (Pixel pixel: outImage.pixels()) {
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
			pixel.setRed(average);
			pixel.setGreen(average);
			pixel.setBlue(average);
		}
		return outImage;
	}

	public void selectAndConvert() {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource image = new ImageResource(f);
			ImageResource convertedImage = makeGray(image);
			String newName = "images/copy-" + image.getFileName();
			convertedImage.setFileName(newName);
			convertedImage.draw();
			convertedImage.save();
		}
	}
}
