package com.mygdx.game.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class game extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture passaro;
    private Texture fundo;

    //variavies de movimento do player
    private int moveY = 0;
    private int moveX = 0;


    //variaveis da largura e altura da tela
    private float larguraDispositivo;
    private float alturaDispositivo;

    @Override
    public void create() {

        batch = new SpriteBatch();
        //definindo as texturas criadas com as texturas que estao na pasta assets
        passaro = new Texture("passaro1.png");
        fundo = new Texture("fundo.png");

        //adicionando o tamanho da tela as variaveis
        larguraDispositivo = Gdx.graphics.getWidth();
        alturaDispositivo = Gdx.graphics.getHeight();

    }

    @Override
    public void render() {

        batch.begin();
        //criando o background com a largura e altura da tela
        batch.draw(fundo, 0, 0, larguraDispositivo, alturaDispositivo);
        batch.draw(passaro, 50, 50, moveX, moveY);
        moveX++;
        moveY++;

        batch.end();
    }

    @Override
    public void dispose() {

    }
}
