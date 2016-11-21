package br.com.senac.cantinaserver.exception;

import br.com.senac.cantinaserver.util.messages.error.ErrorCodes;

public class CustomException1 extends BaseException {

    public CustomException1() {
        super(ErrorCodes.CUSTOM_EXCEPTION_1);
    }
}
