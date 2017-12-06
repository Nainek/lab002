package ua.kpi.nc.model.exceptions;

public class ServerException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerException(Throwable cause) {
        super(cause);
    }

}
