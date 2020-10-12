package clases;

import java.util.Random;

public class TareaRunnable implements Runnable{
	int[] listaNum = null;
	int num;

	public TareaRunnable(int[] listaNum, int num) {
		super();
		this.listaNum = listaNum;
		this.num = num;
	}

	@Override
	public void run() {
		Random rnd = new Random();
		
		synchronized(this)
		{
			for(int i=0 ; i<10 ; i++)
			{
				listaNum[i+num] = rnd.nextInt(9);
				
				try{
					Thread.sleep(new Random().nextInt((500-300+1)+300));
				}catch(InterruptedException e){
					System.out.println("Error al ejecutar la tarea");
				}finally{
					
				}
			}
		}
	}
	
	
}
