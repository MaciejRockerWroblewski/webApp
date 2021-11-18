package com.example.webapp.service;

import com.example.webapp.api.Match;
import com.example.webapp.repository.MatchEntity;
import com.example.webapp.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository repository;

    public void create(Match match) {
        if (match.getFirstTeam().isEmpty() || match.getSecondTeam().isEmpty()) {
            throw new IllegalStateException("Nie podano zespołów biorących udział w meczu.");
        }

        repository.save(MatchEntity.builder()
                .firstTeam(match.getFirstTeam())
                .secondTeam(match.getSecondTeam())
                .startTime(match.getStartTime())
                .build());

    }

    public void update(Match match) {
        repository.save(MatchEntity.builder()
                .id(match.getId())
                .firstTeam(match.getFirstTeam())
                .secondTeam(match.getSecondTeam())
                .startTime(match.getStartTime())
                .build());
    }

    public void delete(Long id) {
        repository.delete(id);

    }

    public List<Match> getAll() {
        return repository.getAll().stream()
                .map(ent -> Match.builder()
                        .id(ent.getId())
                        .firstTeam(ent.getFirstTeam())
                        .secondTeam(ent.getSecondTeam())
                        .startTime(ent.getStartTime())
                        .build())
                .collect(Collectors.toList());

    }

}

