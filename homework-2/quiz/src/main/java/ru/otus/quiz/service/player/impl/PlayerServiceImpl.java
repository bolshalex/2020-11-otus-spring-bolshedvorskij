package ru.otus.quiz.service.player.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.io.ask.Asker;
import ru.otus.quiz.domain.model.Player;
import ru.otus.quiz.service.player.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final Asker asker;

    @Autowired
    public PlayerServiceImpl(Asker asker) {
        this.asker = asker;
    }

    @Override
    public Player getPlayer() {
        String name = asker.askNotEmptyString("Enter your name. The name must not be empty");
        return new Player(name);
    }
}
