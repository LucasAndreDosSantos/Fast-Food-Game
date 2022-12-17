package com.mygdx.fastfood;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class HitboxBoca {
	private Rectangle BocaRetangulo; //Quadrado que fica atr�s da boca com a fun��o de definir os limites da boca, para poder ser utilizado depois para definir que comeu algo
	
	public void CriarRetanguloBoca(Texture BocaImagem, int y,int x) { //Respons�vel por criar o ret�ngulo atr�s da boca, com mesmo tamanho e posi��o da Boca animada
		BocaRetangulo = new Rectangle();
		BocaRetangulo.x = x; 
		BocaRetangulo.y = y; 
		BocaRetangulo.width = BocaImagem.getWidth()/2;
		BocaRetangulo.height = BocaImagem.getHeight()/2; 
	}
	
	public Rectangle getBocaRetangulo() { //Respons�vel por permitir que outros c�digos acessem a posi��o atual e o tamanho da boca
		return BocaRetangulo;
	}
	
	public void setBocaRetanguloY(int Y) {
		BocaRetangulo.y = Y;
	}
	
	public void setBocaRetanguloX(int X) {
		BocaRetangulo.x = X;
	}


}
