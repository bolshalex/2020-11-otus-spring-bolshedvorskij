package ru.otus.quiz.dao.player.impl;

import ru.otus.quiz.dao.player.PlayerDao;
import ru.otus.quiz.model.Player;
import ru.otus.quiz.service.io.ask.impl.CmdAsker;

public class CmdPlayerDao implements PlayerDao {
    private final CmdAsker asker;

    public CmdPlayerDao(CmdAsker asker) {
        this.asker = asker;
    }

    @Override
    public Player getPlayer() {
        String name = asker.askNotEmptyString("Введите Ваше имя. Имя не должно быть пустым");
        return new Player(name);
    }
}
