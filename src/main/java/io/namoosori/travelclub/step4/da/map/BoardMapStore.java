package io.namoosori.travelclub.step4.da.map;

import io.namoosori.travelclub.step1.entity.board.SocialBoard;
import io.namoosori.travelclub.step4.da.map.io.MemoryMap;
import io.namoosori.travelclub.step4.store.BoardStore;
import io.namoosori.travelclub.step4.util.MemberDuplicationException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BoardMapStore implements BoardStore {
	//
	private Map<String, SocialBoard> boardMap;

	public BoardMapStore() {
		//
		this.boardMap = MemoryMap.getInstance().getBoardMap();
	}

	@Override
	public String create(SocialBoard board) {
		//
		Optional.ofNullable(boardMap.get(board.getId()))
				.ifPresent(targetBoard -> {
					throw new MemberDuplicationException("Member already exists with email: " + board.getId());
				});

		boardMap.put(board.getId(), board);
		return board.getId();
	}

	@Override
	public SocialBoard retrieve(String boardId) {
		//
		return boardMap.get(boardId);
	}

	@Override
	public List<SocialBoard> retrieveByName(String name) {
		//

		return boardMap.values().stream()
				.filter(board -> board.getName().equals(name))
				.collect(Collectors.toList());
	}

	@Override
	public void update(SocialBoard board) {
		//

		boardMap.put(board.getId(), board);
	}

	@Override
	public void delete(String boardId) {
		//
		boardMap.remove(boardId);
	}

	@Override
	public boolean exists(String boardId) {
		//
		return Optional.ofNullable(boardMap.get(boardId)).isPresent();
	}
}
