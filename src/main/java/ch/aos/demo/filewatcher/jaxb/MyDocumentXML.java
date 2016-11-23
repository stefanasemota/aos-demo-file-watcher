package ch.aos.demo.filewatcher.jaxb;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Sample representation of an xml document file on the share
 * 
 * @author aos
 *
 */
@XmlRootElement(name = "MyDocument")
public class MyDocumentXML implements Serializable {

	private static final long serialVersionUID = 7454497559989279127L;

	private MyFileXML myFile;

	private Timestamp insertTimestamp;

	public MyDocumentXML() {

	}

	public MyFileXML getMyFile() {
		return myFile;
	}

	@XmlJavaTypeAdapter(value = TimestampAdapter.class)
	public Timestamp getInsertTimestamp() {
		return insertTimestamp;
	}

	public void setInsertTimestamp(Timestamp insertTimestamp) {
		this.insertTimestamp = insertTimestamp;
	}

	public void setMyFile(MyFileXML dossier) {
		this.myFile = dossier;
	}

}