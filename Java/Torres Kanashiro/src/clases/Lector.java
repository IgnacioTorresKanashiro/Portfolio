package clases;

import java.io.FileInputStream;
import java.io.IOException;

public class Lector {

	public int[] leer()
	{
		FileInputStream in = null;
		int[] lista = new int[100];
		int num = 0;
		
		try {
			in = new FileInputStream("numeros_generados.txt");
			
			while(in.read()!=0)
			{
				lista[num]=in.read();
				num++;
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
		}
		
		return lista;
	}
	
	
}
