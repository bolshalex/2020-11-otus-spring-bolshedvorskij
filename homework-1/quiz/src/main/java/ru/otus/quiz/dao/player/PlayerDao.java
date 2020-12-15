package ru.otus.quiz.dao.player;

import ru.otus.quiz.dao.player.exception.PlayerDaoException;
import ru.otus.quiz.model.Player;

import java.util.List;

public interface PlayerDao {

    Player getPlayer() throws PlayerDaoException;
}
