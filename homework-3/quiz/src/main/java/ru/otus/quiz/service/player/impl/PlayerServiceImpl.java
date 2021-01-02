package ru.otus.quiz.service.player.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.quiz.domain.io.LocalizedIoService;
import ru.otus.quiz.domain.model.Player;
import ru.otus.quiz.service.player.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final LocalizedIoService localizedIoService;

    @Autowired
    public PlayerServiceImpl(LocalizedIoService localizedIoService) {
        this.localizedIoService = localizedIoService;
    }

    @Override
    public Player getPlayer() {
        String name = localizedIoService.askNotEmptyString("enter.player.name");
        return new Player(name);
    }
}
