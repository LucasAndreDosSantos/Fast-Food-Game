package com.mygdx.fastfood;

import com.badlogic.gdx.graphics.Texture;

public class ErrosPicles extends Pontuacao {
	private Texture imagemPiclesRoxo; //Respons�vel por ser a textura da imagem do picles roxo
	private TelaDerrota TelaDerrota= new TelaDerrota();
	private boolean Apareceu = false;
	
	
	public boolean Perdeu(Acertos pontos) {
		
		if(pontos.getErros() < 6) { //Verificando se o usuario ainda n�o chegou a 6 erros
			DesenharPicles(pontos);
			return false; //Retorna false se ele ainda n�o chegou a 6 derrotas
			
		}else { //Como ele chegou a 6, inicia o processo de derrota
			Apareceu = TelaDerrota.Derrota(); //Mostra a tela de derrota
			if(Apareceu == false) { //Define se ja aconteceu toda a tela de derrota
				
				pontos.MudaPontuacao(-(pontos.getPontosComer())); //Manda um pontua��o negativa para indicar que ele perdeu o jogo
				pontos.setErros(-6); //Reseta os erros do usuario
			
				return false; //Retorna false se ele j� foi derrotado completamente
			}else {
				return true; //Retorna true se ainda ainda est� passado a tela de derrota
			}
		}
			
	}
	public void DesenharPicles(Pontuacao pontos) {
		imagemPiclesRoxo = new Texture("Imagens/piclesRoxo64.png"); 
		getBatch().begin();
		for(int i=1;i<pontos.getErros()+1;i++) { //Desenhando piclesRoxo de erro at� a quantidade de erros atual do jogo
			getBatch().draw(imagemPiclesRoxo, 50 + (20 * (i + i)) , 504);
		}
		getBatch().end();		
	}
}
