
import java.io.*;

public class Main {
	public static void main(String args[]){
		int pasos = Integer.parseInt(args[0]);
		File input = new File(args[1]);
		File output = new File(args[2]);
		boolean debug=false;
		if(args.length == 4)
			if(args[3].matches("1"))
				debug = true;
		Tablero juego = new Tablero(input,output);
		juego.fastForward(pasos,debug);
	}
}
