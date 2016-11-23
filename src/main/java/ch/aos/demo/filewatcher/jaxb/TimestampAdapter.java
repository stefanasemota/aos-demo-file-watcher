package ch.aos.demo.filewatcher.jaxb;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Timestamp adapter for jaxb
 * 
 * @author aos
 *
 */
public class TimestampAdapter extends XmlAdapter<Date, Timestamp> {

	public Date marshal(Timestamp value) {
		if (value == null) {
			return null;
		}
		return new Date(value.getTime());
	}

	public Timestamp unmarshal(Date value) {
		if (value == null) {
			return null;
		}
		return new Timestamp(value.getTime());
	}
}