package com.mygdx.fastfood;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Boca extends HitboxBoca {
	private Texture BocaImagem; 
	private Animation<TextureRegion> Boca; //Respons�vel por gerar a anima��o da boca fechar e abrir
	private SpriteBatch batch;
	float delayGif = 0f; // Respons�vel por gerar o delay do gif de trocar de frames
	private int y = 230,x = 1500 / 2 - 64 / 2,yinicial,xinicial; // Variavel respons�vel por definir a posi��o onde se encontra a boca
	private TextureRegion [] BocaFrames;
	private TextureRegion Frames[][];
	private boolean Parado = false;
	/*
	* Anima��o da boca feita com base no tutorial presente no Git Hub: https://github.com/libgdx/libgdx/wiki/2D-Animation
	*/

	public void CriarBoca() {
		BocaImagem = new Texture(Gdx.files.internal("Imagens/Boca.png")); 
		yinicial = y;
		xinicial = x;
		CriaAnimacaoBoca();
		Boca = new Animation<TextureRegion>(0.000000025f,BocaFrames); // Criar a nova anima��o, colocando o delay inicial de tempo e os frames da boca
		batch = new SpriteBatch();
		CriarRetanguloBoca(BocaImagem,x,y);		
	}
	
	private void CriaAnimacaoBoca() { // Respons�vel por criar a anima��o do gif da boca
		Frames = TextureRegion.split(BocaImagem,BocaImagem.getWidth()/2,BocaImagem.getHeight()/2); //Separa a boca em diversos frames, j� que a imagem figuras separadas em 2x2 

		BocaFrames = new TextureRegion[4]; //Define um vetor de TextureFrame com 4 posi��es
		int index = 0; 
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				BocaFrames[index++] = Frames[i][j]; //Colocar os frames separados dentro do BocaFrames
			}
		}	
	}
	
	public void DesenhaBoca() { //Respons�vel por desenhar a boca na tela
		delayGif += Gdx.graphics.getDeltaTime() * 0.00000040; //Aplicando o delay de passagem de frames
		batch.begin(); 
		if(Parado == false) { //Define se a imagem est� parada ou em movimento
			batch.draw(Boca.getKeyFrame(delayGif, true), x,y,BocaImagem.getWidth()/2, BocaImagem.getHeight()/2); //Desenhando o frame atual na posi��o especificada com o tamanho da imagem
		}else {
			batch.draw(BocaFrames[0], x,y,BocaImagem.getWidth()/2, BocaImagem.getHeight()/2); //Desenhando o frame atual na posi��o especificada com o tamanho da imagem
		}
		batch.end();		
	}
	
	public void MovimentaBoca() { //Respons�vel por mover a boca quando � clicada uma tela, sendo que ele move o retangulo e a boca
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			y -= 400 * Gdx.graphics.getDeltaTime();
			setBocaRetanguloY(y);
		}
	    if(Gdx.input.isKeyPressed(Keys.UP )) {
	    	y += 400 * Gdx.graphics.getDeltaTime();
	    	setBocaRetanguloY(y);
	    }
	    if(Gdx.input.isKeyPressed(Keys.LEFT)) {
	    	x -= 400 * Gdx.graphics.getDeltaTime();
	    	setBocaRetanguloX(x);
	    }
	    if(Gdx.input.isKeyPressed(Keys.RIGHT )) {
	    	x += 400 * Gdx.graphics.getDeltaTime();
	    	setBocaRetanguloX(x);
	    }
	    LimiteBorda ();
	}
	
	private void LimiteBorda() { //Respons�vel por limitar para que a boca n�o ultrapasse a borda
		if(x < 0) {
			x = 0;
			setBocaRetanguloX(x);
		}
	    if(x > 800 - 64) {
	    	x = 800 - 64;
	    	setBocaRetanguloX(x);
	    }
		if(y < 0) {
			y = 0;
			setBocaRetanguloX(y);
		}
	    if(y > 500 - 64) {
	    	y = 500 - 64;
	    	setBocaRetanguloX(y);
	    }
	}

	public void setY() { //Volta o Y ao valor inicial
		y = yinicial;
		setBocaRetanguloY(y);
	}

	public void setX() { //Volta o X ao valor inicial
		x = xinicial;
		setBocaRetanguloX(x);
	}

	public void setParado(boolean parado) {//Respons�vel por parara a anima��o da boca
		Parado = parado;
	}	
}