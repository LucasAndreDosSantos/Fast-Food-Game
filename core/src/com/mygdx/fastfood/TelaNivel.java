package com.mygdx.fastfood;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class TelaNivel extends Telas {
	private int TelayPalavra1 = 600; //Define a posi��o y inicial da palavra
	private int TelayPalavra2 = 480; //Define a posi��o y inicial da palavra
	private int TelayPalavra3 = 360; //Define a posi��o y inicial da palavra
	
	public boolean PassouNivel() {
		InicializaTela(7,Color.YELLOW,"Sons/levelUp16bits.ogg");
		
		if(TelayPalavra3 >= 130) { //Roda at� o texto chegar a parte baixa da tela
			TelayPalavra1 -= 100 * Gdx.graphics.getDeltaTime(); //Movimenta a escrita para baixo
			TelayPalavra2 -= 100 * Gdx.graphics.getDeltaTime(); //Movimenta a escrita para baixo
			TelayPalavra3 -= 100 * Gdx.graphics.getDeltaTime(); //Movimenta a escrita para baixo
			getBatch().begin();
			getFonte().draw(getBatch(), "        VOC�", 15, TelayPalavra1); //Desenha a palavra VOC� de derrota na tela
			getFonte().draw(getBatch(), "        EST�", 15, TelayPalavra2); //Desenha a palavra EST� de derrota na tela
			getFonte().draw(getBatch(), "ENGORDANDO!", 15, TelayPalavra3); //Desenha a palavra ENGORDANDO de derrota na tela
			getBatch().end();
			
			TocaMusica();//Toca a m�sica de vit�ria
			
			return false; //retorna false se ainda est� aparecendo
		} else {
			TelayPalavra1 = 600;
			TelayPalavra2 = 480;
			TelayPalavra3 = 360;
			return true; //Retorna true se j� apareceu
		}		
	}
}