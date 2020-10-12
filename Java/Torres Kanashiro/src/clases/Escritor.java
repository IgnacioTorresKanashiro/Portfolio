package clases;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Escritor {
	
	public void escribir(int[] listaNum)
	{
		PrintWriter out = null;
		
		try{
			out = new PrintWriter(new FileWriter("numeros_generados.txt"));
			for(int num : listaNum)
			{
				out.println(num);
			}
		}catch(IOException e){
			System.out.println("Error al escribir el archivo");
		}finally{
			out.close();
		}
	}
}
