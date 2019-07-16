package com.liu.dao;

import com.liu.domain.Score;

public interface ScoreDao {
    String lookComments(Score score);
    Integer updateComments(Score score);
    Integer deleteComments(Score score);
}
