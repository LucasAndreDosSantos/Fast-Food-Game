package com.mygdx.fastfood;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pontuacao {
	private long PontosComer = 0; 
	private int erros = 0; //Pontos e erros apresentados na tela
	private SpriteBatch batch = new SpriteBatch();
	
	public long getPontosComer() {
		return PontosComer;
	}

	public int getErros() { //Respons�vel por permitir que outros c�digos tenham acesso aos erros
		return erros;
	}

	public void setErros(int erros) { //Respons�vel por permitir que outros c�digos possam modificar os erros
		this.erros += erros;
	}

	public void setPontosComer(long pontosComer) {
		PontosComer = pontosComer;
	}

	public SpriteBatch getBatch() {
		return batch;
	}
	
	
}
