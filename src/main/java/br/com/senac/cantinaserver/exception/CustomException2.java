package br.com.senac.cantinaserver.exception;

import br.com.senac.cantinaserver.util.messages.error.ErrorCodes;

public class CustomException2 extends BaseException {

    public CustomException2() {
        super(ErrorCodes.CUSTOM_EXCEPTION_2);
    }
}
