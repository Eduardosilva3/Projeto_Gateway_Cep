package br.com.gateway.api.exception;

import java.util.logging.Logger;

public class GatewayException extends RuntimeException{

    private static Logger logger = Logger.getLogger(GatewayException.class.getName());

    public GatewayException() {
    }

    public GatewayException(String msg) {
        super(msg);
        logger.warning(msg);
    }

    public GatewayException(String msg, Throwable t) {
        super(msg, t);
    }

    public GatewayException(Throwable t) {
        super(t);
    }
}
