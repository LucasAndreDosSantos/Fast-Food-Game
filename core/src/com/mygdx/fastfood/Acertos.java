
package com.mygdx.fastfood;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Acertos extends Pontuacao {
	
	private BitmapFont fonte; //Respons�vel por definir a fonte da pontua��o
	
	private Random num = new Random();
	
	private ShapeRenderer divisoria;
	/**
	 * Fonte:
	 * https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/graphics/glutils/ShapeRenderer.html
	 **/
	
	private int acumulador = 1,numAleatorio = num.nextInt(3) + 1;; //Respons�vel por definir quantas vezes + 1 que ele acumulou 500 pontos
	
	public void CriarPontuacao(){
		fonte = new BitmapFont();
		fonte.getData().setScale(4,3); //Definindo a fonte do placar da pontua��o
	}
	
	public void DesenharPontuacao() {
		String Pontuacao = String.valueOf(getPontosComer()); //Transformando o placar inteiro em String
		getBatch().begin();
		fonte.setColor(Color.YELLOW); //Setando a cor do placar
        fonte.draw(getBatch(), Pontuacao, 410, 595); //Colocando o placar na tela na posi��o especificada
        getBatch().end();		
	}
	
	public void MudaPontuacao(long PontosNovos) {
		if(getPontosComer()+ PontosNovos < 1999999999) {
			setPontosComer(getPontosComer()+ PontosNovos); 
		}
		
		if((getPontosComer() >= acumulador * 500) && (getErros() != 0)) { //Verificando se foi acumulado 500 pontos
			setErros(-1); //Recuperando a vida caso tenha passado e j� tenha comido algum picles roxo
			acumulador ++; //Aumentando o acumulador para na pr�xima vez que ele chegar em 500 ele recupera a vida
		}else if((getPontosComer() >= acumulador * 500) && (getErros() == 0)){
			acumulador ++; // Aumentando j� que o usuario ainda n�o comeu picles roxo
		}
	}
	
	public void Pontuacaoganha(int NumeroComida) {//Respons�vel por definir as pontua��es ganhas por comer cada comida
		switch (NumeroComida) {
		case 1: //Pontos batata frita 
			MudaPontuacao(10); 
			break;

		case 2: //Pontos cachorro quente
			MudaPontuacao(5);
			break;

		case 3: //Pontos cerveja
			MudaPontuacao(3);
			break;
		case 4: //Pontos refrigerante de cola
			MudaPontuacao(4);
			break;

		case 5: //Pontos hamburguer
			MudaPontuacao(6);
			break;

		case 6: //Pontos milkshake
			MudaPontuacao(7);
			break;

		case 7: //Pontos picles
			MudaPontuacao(1);
			break;

		case 8: //Erros picles roxo
			setErros(1);
			break;

		case 9: //Pontos picole
			MudaPontuacao(7);
			break;
			
		case 10: //Pontos pizza
			MudaPontuacao(10);
			break;

		case 11: //Pontos sanduiche 
			MudaPontuacao(20);
			break;

		case 12: //Pontos soda 
			MudaPontuacao(5);
			break;
			
		case 13: //Pontos sorvete 
			MudaPontuacao(9);
			break;	
		}		
	}
	
	public void desenhaDivisao(OrthographicCamera camera) {
		divisoria = new ShapeRenderer();

		divisoria.setProjectionMatrix(camera.combined);

		divisoria.begin(ShapeType.Filled);
		divisoria.setColor(Color.YELLOW);
		divisoria.rect(50, 401, 700, 3);
		divisoria.end();

	}
	
	public int getNumAleatorio() { //Pega esse numero aleatorio
		return this.numAleatorio;
	}

	public void setNumAleatorio(int ComidasNaTela, int ComidasTotal) {
		this.numAleatorio = ComidasNaTela +  (num.nextInt(ComidasTotal) + 1); //Criar um n�mero aleat�rios de comida para spawnar na tela
		
		if(this.numAleatorio > ComidasTotal) { //Define que pode no m�ximo 3 comidas na tela
			this.numAleatorio = ComidasTotal;
		}
	}
	
}
