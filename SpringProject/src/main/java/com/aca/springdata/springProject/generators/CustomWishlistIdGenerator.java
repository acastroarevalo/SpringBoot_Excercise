package com.aca.springdata.springProject.generators;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

@SuppressWarnings("serial")
public class CustomWishlistIdGenerator implements IdentifierGenerator {

	private CurrentTracker current = new CurrentTracker();
	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		File fileValidation = new File("WishCurrent.ser");

		if (fileValidation.exists()) {
			System.out.println("SAVE FILE EXISTS");

			FileInputStream file;
			try {
				file = new FileInputStream("WishCurrent.ser");
				ObjectInputStream in = new ObjectInputStream(file);
				current = (CurrentTracker) in.readObject();
				in.close();
				file.close();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("SAVE FILE DOESN'T EXIST");
		}

		current.setValue(current.getValue() + 1);

		// Save Value
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("WishCurrent.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(current);
			out.close();
			fileOut.close();

			System.out.println("Current info saved");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new BigDecimal(current.getValue());
	}

}
