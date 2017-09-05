package flowershop.bucket;

public class NoMoreSlotsException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String NO_MORE_SLOTS_MSG="NO SUCH INDEX";

    public NoMoreSlotsException() {
        super(NO_MORE_SLOTS_MSG);
    }

    public NoMoreSlotsException(Throwable cause) {
        super(NO_MORE_SLOTS_MSG, cause);

    }
}
