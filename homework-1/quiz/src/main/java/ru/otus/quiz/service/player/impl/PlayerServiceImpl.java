package ru.otus.quiz.service.player.impl;

import ru.otus.quiz.dao.player.PlayerDao;
import ru.otus.quiz.dao.player.exception.PlayerDaoException;
import ru.otus.quiz.model.Player;
import ru.otus.quiz.service.player.PlayerService;
import ru.otus.quiz.service.player.exception.PlayerServiceException;

public class PlayerServiceImpl implements PlayerService {
    private final PlayerDao playerDao;

    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public Player getPlayer() throws PlayerServiceException {
        try {
            return playerDao.getPlayer();
        } catch (PlayerDaoException e) {
            throw new PlayerServiceException(e);
        }
    }
}
