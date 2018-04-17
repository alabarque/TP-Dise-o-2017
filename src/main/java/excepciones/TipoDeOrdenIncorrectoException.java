package excepciones;

public class TipoDeOrdenIncorrectoException extends Exception {

	public TipoDeOrdenIncorrectoException(String mensaje) {
		System.out.println(mensaje);
	}

}
