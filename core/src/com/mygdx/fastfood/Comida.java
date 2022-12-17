
package com.mygdx.fastfood;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Comida {

	private ArrayList<Rectangle> alimentos;
	private int numeroComida, ComidasNaTela = 0;
	private int tempoSpawn = 4000;
	private int ComidasCriadas = 0;
	private float velocidadeAlimento = 155, coordenadaY;
	private long ultimoSpawn;
	private Sound somComeuComidaBoa; // som de quando a boca come uma comida boa
	private Sound somComidaSaiDeTela; // som de quando o cachorro-quente sai da tela
	private Sound somComeuPiclesRoxo; // som de quanto a boca come um picles roxo
	private SpriteBatch batch;
	private Texture imagemAlimento;
	private boolean SumiuComida = false;
	
	private void DefineImagem() {
		Random numeroAleatorio = new Random();
		numeroComida = numeroAleatorio.nextInt(13) + 1;

		switch (numeroComida) {
		case 1:
			imagemAlimento = new Texture("Imagens/batataFrita64.png");
			break;

		case 2:
			imagemAlimento = new Texture("Imagens/CachorroQuente64.png");
			break;

		case 3:
			imagemAlimento = new Texture("Imagens/cerveja64.png");
			break;
		case 4:
			imagemAlimento = new Texture("Imagens/cola64.png");
			break;

		case 5:
			imagemAlimento = new Texture("Imagens/hamburguer64.png");
			break;

		case 6:
			imagemAlimento = new Texture("Imagens/milkshake64.png");
			break;

		case 7:
			imagemAlimento = new Texture("Imagens/picles64.png");
			break;

		case 8:
			imagemAlimento = new Texture("Imagens/piclesRoxo64.png");
			break;

		case 9:
			imagemAlimento = new Texture("Imagens/picole64.png");
			break;
			
		case 10:
			imagemAlimento = new Texture("Imagens/pizza64.png");
			break;

		case 11:
			imagemAlimento = new Texture("Imagens/sanduiche64.png");
			break;

		case 12:
			imagemAlimento = new Texture("Imagens/sodaPop64.png");
			break;
			
		case 13:
			imagemAlimento = new Texture("Imagens/sorvete64.png");
			break;
		}
	}

	private boolean DecidiCriacao(Rectangle alimento,float[] Yimpossibilitados) { //Respons�vel por verificar se tem comida no espa�o desejado e assim decidindo se vai criar tal alimento 
		boolean VaiSpawnar = true;
		
		for(int i=0;i<3;i++) {
			if(alimento.y < (Yimpossibilitados[i] + 64) && alimento.y > (Yimpossibilitados[i]- 64)){
				VaiSpawnar = false;
			}
		}
		if(VaiSpawnar == true) {
			alimentos.add(alimento);
			ultimoSpawn = TimeUtils.millis();
		}
		
		return VaiSpawnar;
	}
	
	private boolean PosicionaComida(float[] Yimpossibilitados) {
		Rectangle alimento = new Rectangle();
		
		coordenadaY = MathUtils.random(0, 430);
		alimento.x = 32;
		alimento.y = coordenadaY;
		
		alimento.width = 64;
		alimento.height = 64;
		
		return DecidiCriacao(alimento,Yimpossibilitados);
	}

	public void CriaComida(float[] Yimpossibilitados) {
		batch = new SpriteBatch(); // Inicializa o batch
		alimentos = new ArrayList<Rectangle>(); // Inicializa o vetor de comidas
		
		// Inicializa os sons
		somComidaSaiDeTela = Gdx.audio.newSound(Gdx.files.internal("Sons/comidaSaiuDeTela16bits.ogg")); 
		somComeuComidaBoa = Gdx.audio.newSound(Gdx.files.internal("Sons/comeuCoisaBoa16bits.ogg")); 
		somComeuPiclesRoxo = Gdx.audio.newSound(Gdx.files.internal("Sons/comeuPiclesRoxo16bits.ogg"));

		DefineImagem(); // Define qual ser� o alimento a ser criado
		PosicionaComida(Yimpossibilitados);

	}

	public void DesenhaAlimentos() {
		
		batch.begin();
		for (Rectangle alimento : alimentos) {
			batch.draw(imagemAlimento, alimento.x, alimento.y);

		}
		batch.end();
	}

	public void MovimentaComida(Rectangle boca, Acertos placar,float[] Yimpossibilitados) {		
		Iterator<Rectangle> iter = alimentos.iterator();
		if (TimeUtils.millis() - ultimoSpawn > tempoSpawn) {
			CriaComida(Yimpossibilitados);
		}

		while (iter.hasNext()) {
			Rectangle alimento = iter.next();
			
			alimento.x += velocidadeAlimento * Gdx.graphics.getDeltaTime();

			// Remove as alimentos que sa�ram dos limites da tela
			if (alimento.x + 64 > 800) {
				somComidaSaiDeTela.play();
				iter.remove();
				setComidasCriadas(1);
				SumiuComida = true;
			}else if (alimento.overlaps(boca)) { // Remove as alimentos que foram comidos
				if (getNumeroComida() == 8) {
					somComeuPiclesRoxo.play();
				} else {
					somComeuComidaBoa.play();	
				}
				placar.Pontuacaoganha(getNumeroComida());
				iter.remove();
				setComidasCriadas(1);
				SumiuComida = true;
			}else {
				ComidasNaTela++;
			}
		}
	}
	
	//GETTERS E SETTERS 
	
	public int getComidasNaTela() {
		return ComidasNaTela;
	}

	public void setComidasNaTela(int comidasNaTela) {
		ComidasNaTela = comidasNaTela;
	}

	public boolean isSumiuComida() {
		return SumiuComida;
	}

	public void setSumiuComida(boolean sumiuComida) {
		SumiuComida = sumiuComida;
	}

	// numeroComida
	public int getNumeroComida() {
		return numeroComida;
	}

	public void setNumeroComida(int numeroComida) {
		this.numeroComida = numeroComida;
	}
	
	// tempoSpawn
	public int getTempoSpawn() {
		return tempoSpawn;
	}

	public void setTempoSpawn(int tempoSpawn) {
		this.tempoSpawn = tempoSpawn;
	}

	// velocidadeAlimento
	public float getVelocidadeAlimento() {
		return velocidadeAlimento;
	}

	public void setVelocidadeAlimento(float velocidadeAlimento) {
		this.velocidadeAlimento = velocidadeAlimento;
	}

	// comidasCriadas
	public int getComidasCriadas() {
		return ComidasCriadas;
	}

	public void setComidasCriadas(int comidasCriadas) {
		this.ComidasCriadas += comidasCriadas;
	}
	
	// comidasCriadas
	public float getCoordenadaY() {
		return coordenadaY;
	}

	public void setCoordenadaY(int coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
	
}
