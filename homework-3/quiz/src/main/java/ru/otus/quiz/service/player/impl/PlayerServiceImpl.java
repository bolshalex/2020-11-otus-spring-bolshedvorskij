package ru.otus.quiz.service.player.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.LocalizedMessageProvider;
import ru.otus.quiz.domain.io.ask.Asker;
import ru.otus.quiz.domain.model.Player;
import ru.otus.quiz.service.player.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final Asker asker;
    private final LocalizedMessageProvider messageProvider;

    @Autowired
    public PlayerServiceImpl(Asker asker, LocalizedMessageProvider messageProvider) {
        this.asker = asker;
        this.messageProvider = messageProvider;
    }

    @Override
    public Player getPlayer() {
        String name = asker.askNotEmptyString(messageProvider.getMessage("enter.player.name"));
        return new Player(name);
    }
}
