package exception;

public class VillageSansChefException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final String message = "[Error] : Le village n'a pas de chef";

	public VillageSansChefException() {
		super(message);
	}

	/**
	 * @param message Le village n'a pas de chef
	 */
	public VillageSansChefException(String message) {
		super(message);
	}

	/**
	 * @param message Le village n'a pas de chef
	 */
	public VillageSansChefException(Throwable cause) {
		super(cause);
	}

	public VillageSansChefException(String message, Throwable cause) {
		super(message, cause);
	}

}
