/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Elizabeth
 */
public class TipoDeEntradaInvalida extends Exception {

    /**
     * Creates a new instance of <code>TipoDeEntradaInvalida</code> without
     * detail message.
     */
    public TipoDeEntradaInvalida() {
    }

    /**
     * Constructs an instance of <code>TipoDeEntradaInvalida</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public TipoDeEntradaInvalida(String msg) {
        super(msg);
    }
}
