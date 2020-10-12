package main;

import clases.Escritor;
import clases.Lector;
import clases.TareaRunnable;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread[] hilos = new Thread[10];
		int[] listaNum = new int[100];
		//int[] listaLeida = new int[100];
		Escritor esc = new Escritor();
		//7Lector lec = new Lector();
		
		hilos[0]=new Thread(new TareaRunnable(listaNum, 0));
		hilos[1]=new Thread(new TareaRunnable(listaNum, 10));
		hilos[2]=new Thread(new TareaRunnable(listaNum, 20));
		hilos[3]=new Thread(new TareaRunnable(listaNum, 30));
		hilos[4]=new Thread(new TareaRunnable(listaNum, 40));
		hilos[5]=new Thread(new TareaRunnable(listaNum, 50));
		hilos[6]=new Thread(new TareaRunnable(listaNum, 60));
		hilos[7]=new Thread(new TareaRunnable(listaNum, 70));
		hilos[8]=new Thread(new TareaRunnable(listaNum, 80));
		hilos[9]=new Thread(new TareaRunnable(listaNum, 90));
		
		for(int i=0 ; i<10 ; i++)
		{
			hilos[i].start();
		}
		
		for(int i=0 ; i<10 ; i++)
		{
			try{
				hilos[i].join();
			}catch(InterruptedException e){
				System.out.println("Error al intentar joinear los hilos");
			}finally{
				
			}
		}
		
		esc.escribir(listaNum);
		mostrarLista(listaNum);
		calcularPromedio(listaNum);
		//listaLeida=lec.leer();
		//mostrarLista(listaLeida);
		//calcularPromedio(listaLeida);
		
	}

	
	static void mostrarLista(int[] lista)
	{
		for(int i=0 ; i<100 ; i++)
		{
			System.out.println("Número: "+lista[i]);
		}
	}
	
	static void calcularPromedio(int[] lista)
	{
		double num=0;
		
		for(int numero : lista)
		{
			num=num+numero;
		}
		
		System.out.println("El promedio de todos los números generados es: "+(num/100.0));
	}
}
