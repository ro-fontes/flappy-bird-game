package com.mygdx.game.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class game extends ApplicationAdapter {

    private SpriteBatch batch;
    private Random random;
    BitmapFont textoPontuacao;
    private boolean passouCano = false;
    private ShapeRenderer shadeRenderer;
    private Circle circuloPassaro;
    private Rectangle retanguloCanoCima;
    private Rectangle retanguloCanoBaixo;
    private float variacao = 0;
    private float posicaoInicialVerticalPassaro = 0;
    private float posicaoCanoVertical;
    private float posicaoCanoHorizontal;
    private float espacoEntreCanos;
    //variaveis da largura e altura da tela
    private float larguraDispositivo;
    private float alturaDispositivo;
    private Texture fundo;
    //texturas do passaro para fazer a animacao de voo
    private Texture[] passaros;
    private Texture canoBaixo;
    private Texture canoTopo;
    //variavies de movimento do player
    private int gravidade = 0;
    private int pontos = 0;


    @Override
    public void create() {

        inicializaTexturas();
        inicializaObjetos();
    }




    @Override
    public void render() {

        verificarEstadoJogo();
        validarPontos();
        desenharTexturas();
        detectarColisao();
    }

    private void detectarColisao() {

        circuloPassaro.set(50 + passaros[0].getWidth() / 2, posicaoInicialVerticalPassaro + passaros[0].getHeight() / 2, passaros[0].getWidth() / 2);

        retanguloCanoCima.set(posicaoCanoHorizontal, alturaDispositivo / 2 - canoTopo.getHeight() - espacoEntreCanos / 2 + posicaoCanoVertical, canoTopo.getWidth(), canoTopo.getHeight());
        retanguloCanoBaixo.set(posicaoCanoHorizontal, alturaDispositivo / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + posicaoCanoVertical, canoBaixo.getWidth(), canoBaixo.getHeight());
        boolean colisaoCanoCima = Intersector.overlaps(circuloPassaro, retanguloCanoCima);
        boolean colisaoCanobaixo = Intersector.overlaps(circuloPassaro, retanguloCanoBaixo);

        if( colisaoCanobaixo || colisaoCanoCima){

        }
    }

    private void desenharTexturas() {

        batch.begin();
        //criando o background com a largura e altura da tela
        batch.draw(fundo, 0, 0, larguraDispositivo, alturaDispositivo);
        //criando o passaro com sua posicao no meio da tela
        batch.draw(passaros[(int) variacao], 50, posicaoInicialVerticalPassaro);
        //criando os canos na cena
        batch.draw(canoBaixo, posicaoCanoHorizontal, alturaDispositivo / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + posicaoCanoVertical);
        batch.draw(canoTopo, posicaoCanoHorizontal, alturaDispositivo / 2 + espacoEntreCanos / 2 + posicaoCanoVertical);
        textoPontuacao.draw(batch, String.valueOf(pontos), larguraDispositivo / 2, alturaDispositivo - 100);
        batch.end();
    }

    private void validarPontos() {

        //verificando se o passarou passou pelo cano
        if(posicaoCanoHorizontal < 50 - passaros[0].getWidth()){
            if(!passouCano){
                pontos++;
                passouCano = true;
            }
        }
    }

    private void verificarEstadoJogo() {

        posicaoCanoHorizontal -= Gdx.graphics.getDeltaTime() * 200;
        if(posicaoCanoHorizontal <- canoBaixo.getWidth()){
            posicaoCanoHorizontal = larguraDispositivo;
            posicaoCanoVertical = random.nextInt(400) - 200;
            passouCano = false;
        }
        //Criando uma variavel para verificar se o player clicou na tela
        boolean toqueTela = Gdx.input.justTouched();

        //ao tocar na tela, o passaro voa para cima
        if(Gdx.input.justTouched()){
            gravidade = -25;
        }

        if(posicaoInicialVerticalPassaro > 0 || toqueTela)
            posicaoInicialVerticalPassaro -= gravidade;

        //Criando a animacao do passaro voando
        variacao += Gdx.graphics.getDeltaTime() * 10;

        //Resetando a variavel variacao ao chegar em 3 para cria um loop na animacao
        if(variacao > 3)
            variacao = 0;

        //Adicionando 1 a gravidade
        gravidade++;
    }

    private void inicializaObjetos() {

        batch = new SpriteBatch();
        random = new Random();
        //adicionando o tamanho da tela as variaveis
        larguraDispositivo = Gdx.graphics.getWidth();
        alturaDispositivo = Gdx.graphics.getHeight();
        //setando a posicao inicial do passaro, que sera no meio da tela
        posicaoInicialVerticalPassaro = alturaDispositivo / 2;
        posicaoCanoHorizontal = larguraDispositivo;
        espacoEntreCanos = 350;

        textoPontuacao = new BitmapFont();
        textoPontuacao.setColor(Color.WHITE);
        textoPontuacao.getData().setScale(10);
    }

    private void inicializaTexturas() {

        passaros = new Texture[3];
        //adicionando as texturas do passaro que estao na pasta assets para fazer uma animacao
        passaros[0] = new Texture("passaro1.png");
        passaros[1] = new Texture("passaro2.png");
        passaros[2] = new Texture("passaro3.png");
        //definindo o fundo com a textura que esta na pasta assets
        fundo = new Texture("fundo.png");
        canoBaixo = new Texture("cano_baixo_maior.png");
        canoTopo = new Texture("cano_topo_maior.png");
    }

    @Override
    public void dispose() {

    }
}
