/**
 * Esta calse corresponde con la celula del tablero, contienen un boolean para 
 * saber si esta viva o muerta, sus coordenadas y una lista de sus vecinas
 * @author alu0100888102
 *
 */

import java.util.*;

public class Celula {
	int xcord, ycord;
	boolean viva;
	ArrayList<Celula> vecinas;
	
	public Celula (){
		xcord = 0;
		ycord = 0;
		viva = false;
		vecinas = new ArrayList<Celula>();
	}
	public Celula(int x,int y, boolean v){
		vecinas = new ArrayList<Celula>();
		xcord = x;
		ycord = y;
		viva = v;
	}
	/**
	 * Este metodo recoge las celulas vecinas una vez inizializada la celula
	 * @param tab
	 */
	public void initVecinas (Tablero tab){
		for(int i = xcord-1; i<= xcord+1; i++){
			for(int j = ycord-1; j<= ycord+1; j++){
				if(i == xcord && j == ycord)
					continue;
				if(i< tab.getxSize() && j<tab.getySize() && i>=0 && j>=0)
					vecinas.add(tab.getCell(i,j));
			}
		}
	}
	
	/**
	 * Setters y getters
	 */
	public int getXcord(){
		return xcord;
	}
	public int getYcord(){
		return ycord;
	}
	public boolean getViva(){
		return viva;
	}
	public ArrayList<Celula> getVecinas(){
		return vecinas;
	}
	public void setXcord(int x){
		xcord = x;
	}
	public void setYcord(int y){
		ycord = y;
	}
	public void setViva(boolean v){
		viva = v;
	}
	public void setVecinas(ArrayList<Celula> vec){
		vecinas = vec;
	}
	
	/**
	 * Esta funcion devuleve si la celula estara viva la sigueitne generacion
	 */
	public boolean nextGen(){
		int count =0;
		for(Celula cell : vecinas){
			if(cell.getViva())
				count++;
		}
		if(count < 2)
			return(false);
		if(count == 3)
			return(true);
		if(count > 3)
			return(false);
		return viva;
	}
	
	public String toString(){
		if(viva)
			return "o";
		else
			return"*";
	}
}
