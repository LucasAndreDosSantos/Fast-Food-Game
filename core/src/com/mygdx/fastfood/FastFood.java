
package com.mygdx.fastfood;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FastFood extends ApplicationAdapter {

	private Alimentos auxAlimentos;
	private Boca auxBoca;
	private boolean Perdeu = false,Rodando = false, PassouNivel = false;
	private ErrosPicles auxErros;
	private OrthographicCamera camera;
	private Acertos auxPontuacao;
	private SpriteBatch batch;
	private VelocidadeAlimentos controlaVelocidade;
	private Pausa Pausado;

	public void create() {

		//Inicializa vari�veis 
		auxBoca = new Boca();
		auxAlimentos = new Alimentos();
		auxPontuacao = new Acertos();
		auxErros = new ErrosPicles();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		controlaVelocidade = new VelocidadeAlimentos();
		Pausado = new Pausa();
		
		camera.setToOrtho(false, 800, 480);
		
		//Chamada dos m�todos de cria��o 
		auxBoca.CriarBoca(); // Cria a boca
		auxPontuacao.CriarPontuacao(); // Cria a pontua��o
		auxAlimentos.CriarALimentos();
	}

	public void render() {
		auxAlimentos.ResetaNumeroAlimentos();
		Gdx.gl.glClearColor(0, 0, 0, 0); // Define a cor do fundo
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		
		if (Perdeu == false) { //Percebe-se se o usuario ganhou ou perdeu o jogo
			PassouNivel = auxAlimentos.PassagemNivelAlimento(controlaVelocidade);
			
			if(auxAlimentos.isPassou()) Rodando = false;
			
			if (PassouNivel == false) {
				auxPontuacao.desenhaDivisao(camera); // Linha horizontal que fica no topo da tela
				auxPontuacao.DesenharPontuacao(); // Desenha a pontua��o
				Perdeu = auxErros.Perdeu(auxPontuacao); // Chama o m�todo que desenha os erros na tela

				//Chama os m�todos de desenhar e movimentar os objetos
				auxBoca.DesenhaBoca();
				
				if(Rodando == false) { //Define que o c�digo parou
					auxBoca.setParado(true); //Seta a parada da anima��o da boca
					Rodando = Pausado.Pausar(); //Pausa a tela 
					auxAlimentos.setPassou(false);
				}else {
					auxBoca.setParado(false);
					auxBoca.MovimentaBoca();
					auxAlimentos.ModificaAlimentos(auxBoca,auxPontuacao);
					auxAlimentos.NumeroAlimentos(auxPontuacao);
				}	
			}
		} else {

			Perdeu = auxErros.Perdeu(auxPontuacao); //Chama a anima��o de derrota, retornado se j� apareceu ou n�o 

			//Retorna a boca para a posi��o inicial
			auxBoca.setY(); 
			auxBoca.setX();	
			
			auxAlimentos.ResetaDerrota(controlaVelocidade);
			
			Rodando = false; //Define que o jogo vai ser pausado
			
		}
	}
	
	@Override
	public void dispose() {
		
	}
	
}