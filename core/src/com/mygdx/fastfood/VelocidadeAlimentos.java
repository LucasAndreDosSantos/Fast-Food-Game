package com.mygdx.fastfood;

public class VelocidadeAlimentos {
	public void AumentaVelocidade(Comida comida, int quantidadeDeAumentos) {
		// Impossibilita o aumento da velocidade ap�s o n�vel 3
		
		if (quantidadeDeAumentos < 3) {
			// Aumenta a velocidade
			comida.setTempoSpawn(comida.getTempoSpawn() / 2);
			comida.setVelocidadeAlimento(comida.getVelocidadeAlimento() * 2);

		} 
	}

	public void ReiniciaVelocidade(Comida comida) {
		comida.setTempoSpawn(4000);
		comida.setVelocidadeAlimento(155);
	}
}
