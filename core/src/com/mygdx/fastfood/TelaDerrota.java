package com.mygdx.fastfood;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class TelaDerrota extends Telas {
	private int Telay = 600; //Define a posi��o y inicial da palavra
	
	public boolean Derrota() {
		InicializaTela(10,Color.MAGENTA,"Sons/perdeu16bits.ogg");
		if(Telay >= 130) { //Roda at� o texto chegar a parte baixa da tela
			Telay -= 120 * Gdx.graphics.getDeltaTime(); //Movimenta a escrita para baixo
			getBatch().begin();
			getFonte().draw(getBatch(), "ARROTOU!", 15, Telay); //Desenha a palavra ARROTOU de derrota na tela
			getBatch().end();
			
			TocaMusica(); //Toca a m�sica de derrota
			return true; //retorna true se ainda est� aparecendo
		} else {
			Telay = 600;
		 return false; //Retorna false se j� apareceu
		}
	}
	
	
	
}
