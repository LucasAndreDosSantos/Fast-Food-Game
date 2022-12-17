
package com.mygdx.fastfood;

import java.util.ArrayList;

public class Alimentos {
	
	private ArrayList<Comida> auxComidas;
	private int ComidasCriadas = 0,ComidasTela = 0, ComidasTotal = 3;
	private float Yimpossibilitados[] = {1000,1000,1000};
	private boolean PassouNivel = false,Passou = false;
	private TelaNivel PassouFase;
	private int quantidadeDeAumentosDeVelocidade = 0;
	
	public void CriarALimentos(){
		auxComidas = new ArrayList<Comida>();
		PassouFase = new TelaNivel();
		
		for (int i = 0; i < ComidasTotal; i++) {
			Comida comida = new Comida();
			auxComidas.add(comida);
		}
		// Cria os alimentos
		for (int i = 0; i < auxComidas.size(); i++) {
			auxComidas.get(i).CriaComida(Yimpossibilitados);
			Yimpossibilitados[i] = auxComidas.get(i).getCoordenadaY();
		}
	}
	
	public boolean PassagemNivelAlimento(VelocidadeAlimentos controlaVelocidade) {
		for (int i = 0; i < auxComidas.size(); i++) {
			ComidasCriadas += auxComidas.get(i).getComidasCriadas(); //Pega as comidas criadas em cada valor da array list
		} // fim for
		
		if (ComidasCriadas >= 50) {
			PassouNivel = PassouFase.PassouNivel(); //Chama o passa n�vel e traz um valor boolean informando se j� acabou a tela de passou de n�vel

			if (PassouNivel == true) {
				quantidadeDeAumentosDeVelocidade++;
				for (int i = 0; i < auxComidas.size(); i++) {

					// Reseta o contador de comidas criadas
					auxComidas.get(i).setComidasCriadas(-(auxComidas.get(i).getComidasCriadas()));

					// Chama o m�todo respons�vel por aumentar a velocidade
					controlaVelocidade.AumentaVelocidade(auxComidas.get(i), quantidadeDeAumentosDeVelocidade);
				} // fim for
				Passou = true;
				return false;
			}
			return true;
		}
		
		return false;
	}
	
	public void ModificaAlimentos(Boca auxBoca, Acertos auxPontuacao) {
		for (int i = 0; i < auxPontuacao.getNumAleatorio(); i++) {
			
			Yimpossibilitados[i] = auxComidas.get(i).getCoordenadaY();
			auxComidas.get(i).DesenhaAlimentos(); // Desenha as comidas
			// Movimenta as comidas
			auxComidas.get(i).MovimentaComida(auxBoca.getBocaRetangulo(), auxPontuacao,Yimpossibilitados);
			Yimpossibilitados[i] = auxComidas.get(i).getCoordenadaY();
			
		}	
		
		for (int i = 0; i < auxPontuacao.getNumAleatorio(); i++) {
			Yimpossibilitados[i] = auxComidas.get(i).getCoordenadaY();
			auxComidas.get(i).DesenhaAlimentos(); // Desenha as comidas
			// Movimenta as comidas
			auxComidas.get(i).MovimentaComida(auxBoca.getBocaRetangulo(), auxPontuacao,Yimpossibilitados);
			Yimpossibilitados[i] = auxComidas.get(i).getCoordenadaY();
		}	
		
	}
		
	public void NumeroAlimentos (Acertos auxPontuacao) {
		
		for(int i=0;i<auxComidas.size();i++) { //Define quantas comidas est�o na tela no final dessa rodada
			ComidasTela += auxComidas.get(i).getComidasNaTela();
		}
	
		for(int i=0;i<auxComidas.size();i++) { //Define que, se comeu alguma comida, vai gerar um n�mero aleatorio de novas comidas
			if(auxComidas.get(i).isSumiuComida() == true) {
				auxPontuacao.setNumAleatorio(ComidasTela,ComidasTotal);
				auxComidas.get(i).setSumiuComida(false);
			}
		}		
	
		for(int i=0;i<auxComidas.size();i++) { //Seta em todos os c�digos que tem 0 comidas na tela
			auxComidas.get(i).setComidasNaTela(0);
		}
	}
	
	public void ResetaNumeroAlimentos(){
		ComidasTela = 0; //Define que a cada rodada vai ter inicialmente 0 comidas na tela
		ComidasCriadas = 0; //Define que a cada rodada vai ter inicialmente 0 comidas criadas		
	}
	
	public void ResetaDerrota(VelocidadeAlimentos controlaVelocidade) {
		for (int i = 0; i < auxComidas.size(); i++) { //Define 0 para o n�merop de comidas criadas
			auxComidas.get(i).setComidasCriadas(-(auxComidas.get(i).getComidasCriadas()));
			controlaVelocidade.ReiniciaVelocidade(auxComidas.get(i));
		}
		quantidadeDeAumentosDeVelocidade = 0;
		CriarALimentos();
	}

	public boolean isPassou() {
		return Passou;
	}

	public void setPassou(boolean passou) {
		Passou = passou;
	}
}