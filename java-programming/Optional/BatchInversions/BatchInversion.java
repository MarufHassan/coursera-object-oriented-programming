import edu.duke.*;
import java.io.File;

public class BatchInversion {
	public ImageResource makeInversion(ImageResource inImage) {
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

		for (Pixel pixel: outImage.pixels()) {
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			pixel.setRed(255 - inPixel.getRed());
			pixel.setGreen(255 - inPixel.getGreen());
			pixel.setBlue(255 - inPixel.getBlue());
		}
		return outImage;
	}

	public void selectAndConvert() {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource image = new ImageResource(f);
			ImageResource convertedImage = makeInversion(image);
			String newName = "inverted_images/inverted-" + image.getFileName();
			convertedImage.setFileName(newName);
			convertedImage.draw();
			convertedImage.save();
		}
	}
}
