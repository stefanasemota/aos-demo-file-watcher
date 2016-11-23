package ch.aos.demo.filewatcher.common;

/**
 * Exception class
 * 
 * @author aos
 *
 */
public abstract class AbstractDocumentException extends Exception {

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	protected AbstractDocumentException() {
		super();
	}

	protected AbstractDocumentException(String message) {
		super(message);
	}

	/**
	 * {@link String}
	 */
	public abstract String getMessage();
}