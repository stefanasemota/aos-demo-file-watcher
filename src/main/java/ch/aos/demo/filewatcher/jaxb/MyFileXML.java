package ch.aos.demo.filewatcher.jaxb;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Sample example of an xml file on the share
 * 
 * @author aos
 *
 */
@XmlRootElement(name = "MyFile")
public class MyFileXML implements Serializable {

	private static final long serialVersionUID = -1602627713706264522L;

	private String text;

	/**
     * 
     */
	public MyFileXML() {
	}

	/**
	 * @param dossierId
	 */
	public MyFileXML(Integer dossierId) {
		super();
	}
}