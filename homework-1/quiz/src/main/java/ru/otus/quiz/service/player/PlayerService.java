package ru.otus.quiz.service.player;

import ru.otus.quiz.model.Player;
import ru.otus.quiz.service.player.exception.PlayerServiceException;

public interface PlayerService {

    Player getPlayer() throws PlayerServiceException;
}
