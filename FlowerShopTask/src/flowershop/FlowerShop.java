package flowershop;

import flowershop.Report.TxtFileReporter;
import flowershop.bucket.Bucket;
import flowershop.bucket.NoMoreSlotsException;
import flowershop.bucket.NoSuchFlowerException;
import flowershop.bucket.WrongFlowerIndexException;
import flowershop.datasource.FlowerProvider;
import flowershop.datasource.txt.TxtFlowerProvider;
import flowershop.flower.Decorative;
import flowershop.flower.Flower;
import flowershop.flower.Lilac;
import flowershop.flower.Rose;
import flowershop.flower.Tulip;

public class FlowerShop {
	public static void main(String[] args) {

		FlowerProvider flowerProvider = new TxtFlowerProvider();

		Flower rose = flowerProvider.getFlowerByName(Rose.ROSE_FLOWER_NAME);
		Flower tulip = flowerProvider.getFlowerByName(Tulip.TULIP_FLOWER_NAME);
		Flower decorative = flowerProvider.getFlowerByName(Decorative.DECORATIVE_FLOWER);
		Flower lilac = flowerProvider.getFlowerByName(Lilac.LILAC_FLOWER_NAME);

		Bucket b1 = new Bucket(3);

		try {
			b1.addFlower(rose);
			b1.addFlower(tulip);
			b1.addFlower(decorative);
			b1.addFlower(lilac);
		// Exception to handle case when no slots available in the bucket
		} catch (NoMoreSlotsException e) {
			System.out.println("Cannot include that flower: " + e.getMessage());
		}

		// Exception to handle case when trying to remove not existing Flower
		try {
			b1.removeFlower(tulip);
		} catch (NoSuchFlowerException e) {
			System.out.println("Cannot delete that flower:  " + e.getMessage());
		}

		// Exception to handle case when trying to remove not existing Flower by its number
		try {
			b1.removeFlowerByIndex("0");
		} catch (WrongFlowerIndexException e) {
			System.out.println("There is no flower with such index: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Incorrect index:  " + e.getMessage());
		}

		System.out.print("Bucket price is: ");
		System.out.println(b1.calcPrice());
		TxtFileReporter.createReport(b1);
		System.out.print("Bucket price is written to file");

	}
}
