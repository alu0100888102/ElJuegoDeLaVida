/**
 * Esta calse representa el tablero del juego de la vida.
 * @author alu0100888102
 *
 */

import java.util.*;
import java.io.*;

public class Tablero {
	ArrayList<ArrayList<Celula>> tablero;
	int xsize, ysize;
	File outFile;
	
	public Tablero(){
		tablero = new ArrayList<ArrayList<Celula>>();
		xsize = 0;
		ysize = 0;
		outFile = null;
		this.write(outFile, false);
	}
	public Tablero(Tablero original){
		xsize = original.getxSize();
		ysize = original.getySize();
		outFile = original.getOutFile();
		tablero = new ArrayList<ArrayList<Celula>>();
		for(int i =0; i<ysize; i++){
			ArrayList<Celula> nf = new ArrayList<Celula>();
			for(int j = 0; j< xsize; j++){
				nf.add(new Celula(j, i, original.getCell(j,i).getViva()));
			}
			tablero.add(nf);
		}
		this.write(outFile, false);
	}
	
	/**
	 * Crea el tablero a partir de un fichero
	 * @param input
	 */
	public Tablero(File input, File output){
		outFile = output;
		tablero = new ArrayList<ArrayList<Celula>>();
		try{
			FileInputStream istream = new FileInputStream(input);
			BufferedReader bufferreader = new BufferedReader(new InputStreamReader(istream));
			String line = null;
			line = bufferreader.readLine();
			ysize= Integer.parseInt(line);
			line = bufferreader.readLine();
			xsize= Integer.parseInt(line);
			int lineNumber=0;
			ArrayList<Celula> temp;
			while ((line = bufferreader.readLine()) != null) {
				temp = new ArrayList<Celula>();
				for(int i =0; i<line.length(); i++){
					if(line.charAt(i) == 'o')
						temp.add(new Celula(i,lineNumber,true));
					else
						temp.add(new Celula(i,lineNumber,false));
				}
				lineNumber++;
				tablero.add(temp);
			}
			bufferreader.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error en el fichero: no se encuentra " + e);
			System.exit(1);
		}
		catch(IOException e){
			System.out.println("Error en el fichero: error de entrada/salida " + e);
			System.exit(1);
		}
		catch(IllegalArgumentException e){
			System.out.println(" Error en el fichero: error de entrada/salida " + e);
			System.exit(1);
		}
		for(ArrayList<Celula> fila : tablero){
			for(Celula cell : fila){
				cell.initVecinas(this);
			}
		}
		this.write(outFile, false);
	}
	
	/**
	 * Setters y getters
	 */
	public int getxSize(){
		return xsize;
	}
	public int getySize(){
		return ysize;
	}
	public ArrayList<ArrayList<Celula>> getTablero(){
		return tablero;
	}
	public void setxSize(int x){
		xsize = x;
	}
	public void setySize(int y){
		ysize = y;
	}
	public void setTablero(ArrayList<ArrayList<Celula>> t){
		tablero = t;
	}
	public Celula getCell(int x, int y){
		return tablero.get(y).get(x);
	}
	public void setCell(Celula cell){
		tablero.get(cell.getYcord()).set(cell.getXcord(), cell);
		for(ArrayList<Celula> fila : tablero){
			for(Celula celula : fila){
				celula.initVecinas(this);
			}
		}
	}
	public void setOutFile(File out){
		outFile = out;
	}
	public File getOutFile(){
		return outFile;
	}
	
	
	/**
	 * actualiza al tablero para la siguiente generacion.
	 */
	public void update(){
		ArrayList<ArrayList<Celula>> temp = new ArrayList<ArrayList<Celula>>();
		for(int i =0; i<ysize; i++){
			ArrayList<Celula> nf = new ArrayList<Celula>();
			for(int j = 0; j< xsize; j++){
				nf.add(new Celula(j, i, getCell(j,i).nextGen()));
			}
			temp.add(nf);
		}
		tablero = temp;
		for(ArrayList<Celula> fila : tablero){
			for(Celula cell : fila){
				cell.initVecinas(this);
			}
		}
	}
	
	public void fastForward(int n, boolean debug){
		for(int i = 0; i<n; i++){
			update();
			if(debug)
				write(outFile, debug);
		}
		if(!debug)
			write(outFile, debug);
	}
	
	public String toString(){
		String output = new String();
		for(ArrayList<Celula> line : tablero){
			for(Celula cell : line){
				output+=cell;
			}
			output+="\n";
		}
		return output;
	}

	
	public void write(File output,boolean debug){
		try{
			FileWriter fw = new FileWriter(output, debug);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw);
			out.println("\n"+ this +"\n");
			out.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error en el fichero: no se encuentra " + e);
			System.exit(1);
		}
		catch(IOException e){
			System.out.println("Error en el fichero: error de entrada/salida " + e);
			System.exit(1);
		}
	}
}
