package com.mygdx.game.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class game extends ApplicationAdapter {

    private SpriteBatch batch;
    private float variacao = 0;
    private float gravidade = 0;
    private float posicaoInicialVerticalPassaro = 0;
    //variaveis da largura e altura da tela
    private float larguraDispositivo;
    private float alturaDispositivo;
    private Texture fundo;
    //texturas do passaro para fazer a animacao de voo
    private Texture[] passaros;
    //variavies de movimento do player
    private int moveY = 0;
    private int moveX = 0;


    @Override
    public void create() {

        batch = new SpriteBatch();
        //adicionando as texturas do passaro que estao na pasta assets para fazer uma animacao
        passaros = new Texture[3];
        passaros[0] = new Texture("passaro1.png");
        passaros[1] = new Texture("passaro2.png");
        passaros[2] = new Texture("passaro3.png");
        //definindo as texturas criadas com as texturas que estao na pasta assets
        fundo = new Texture("fundo.png");
        //adicionando o tamanho da tela as variaveis
        larguraDispositivo = Gdx.graphics.getWidth();
        alturaDispositivo = Gdx.graphics.getHeight();
        //setando a posicao inicial do passaro, que sera no meio da tela
        posicaoInicialVerticalPassaro = alturaDispositivo / 2;
    }

    @Override
    public void render() {

        batch.begin();

        //Resetando a variavel variacao ao chegar em 3 para cria um loop na animacao
        if(variacao > 3)
            variacao = 0;

        //Criando uma variavel para verificar se o player clicou na tela
        boolean toqueTela = Gdx.input.justTouched();

        //ao tocar na tela, o passaro voa para cima
        if(Gdx.input.justTouched()){
            gravidade = -25;
        }

        if(posicaoInicialVerticalPassaro > 0 || toqueTela)
            posicaoInicialVerticalPassaro -= gravidade;

        //criando o background com a largura e altura da tela
        batch.draw(fundo, 0, 0, larguraDispositivo, alturaDispositivo);
        //criando o passaro com sua posicao no meio da tela
        batch.draw(passaros[(int) variacao], 30, posicaoInicialVerticalPassaro);

        //Criando a animacao do passaro voando
        variacao += Gdx.graphics.getDeltaTime() * 10;
        gravidade++;
        moveX++;
        moveY++;
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
