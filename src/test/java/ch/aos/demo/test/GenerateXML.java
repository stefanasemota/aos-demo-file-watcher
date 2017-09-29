package ch.aos.demo.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ch.aos.demo.filewatcher.jaxb.MyDocumentXML;
import ch.aos.demo.filewatcher.jaxb.MyFileXML;

/**
 * Creates a default folder on a mac in
 * /var/folders/r3/pwknsq7x4q560v9yx40ttk5c0000gn
 * /T/my-file-watcher6649961559675505596
 * 
 * @author aos
 *
 */
public class GenerateXML {

	public static void main(String[] args) throws IOException {
		try {
			/* Prepare files */
			Path temp = Files.createTempDirectory("my-file-watcher");
			File file = new File(temp.toString() + "/file-1.xml");

			MyFileXML myFile = new MyFileXML();

			MyDocumentXML document = new MyDocumentXML();
			document.setInsertTimestamp(new Timestamp(new java.util.Date()
					.getTime()));
			document.setMyFile(myFile);

			JAXBContext jaxbContext = JAXBContext
					.newInstance(MyDocumentXML.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(document, file);
			jaxbMarshaller.marshal(document, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
