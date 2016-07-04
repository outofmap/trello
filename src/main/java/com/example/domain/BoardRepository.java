package com.example.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface BoardRepository extends CrudRepository<Board,Long> {
	Board findByBoardId(Long boardId);
	
}
