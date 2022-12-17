package com.mygdx.fastfood;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Pausa { 
	
	public boolean Pausar() { //Respons�vel por pausar a tela em certo momento
		
		if(Gdx.input.isKeyPressed(Keys.ANY_KEY)) { //Comando que espera o usuario digitar qualquer tecla para continuar
			return true; //Retorna true se � para voltar a rodar o c�digo
		}
		return false; //retorna falso se � para ainda continuar pausado
	}

}
