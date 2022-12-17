package com.mygdx.fastfood;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Telas {
	private SpriteBatch batch;
	private BitmapFont fonte; //Respons�vel por definir a fonte da boca
	private Sound som;
	public void InicializaTela(int TamanhoFonte, Color cor,String FileSom) {
		batch = new SpriteBatch();
		fonte = new BitmapFont();	
		
		fonte.getData().setScale(TamanhoFonte,TamanhoFonte); //Define o tamanho da fonte da palavra
		
		fonte.setColor(cor);
		
		som = Gdx.audio.newSound(Gdx.files.internal(FileSom));
		
	}
	public SpriteBatch getBatch() {
		return batch;
	}
	public BitmapFont getFonte() {
		return fonte;
	}
	
	public void TocaMusica() {
		som.play();
	}
	
	
}
