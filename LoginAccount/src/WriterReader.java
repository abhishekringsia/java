import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WriterReader {

	public static void main(String[] args) {

		LoginAccount p1 = new LoginAccount("accountId", "userId","userName","emailAddress","guid",
                "firstName",
                "lastName","displayName","corporateId", true,
                 1,
                1);

		try {
			FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(p1);
			
			o.close();
			f.close();

			FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			LoginAccount pr1 = (LoginAccount) oi.readObject();
			

			System.out.println(pr1.toString());
			

			oi.close();
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}