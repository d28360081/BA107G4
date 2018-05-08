package com.member.model;

import java.util.List;

public interface MemDAO_interface {
	public String authuser(MemVO memVO);
	boolean isUserIdExist(String acc);
	public void insert(MemVO memvo);
	public void update(MemVO memvo);
	public void delete(String mem_id);
	public void updateBonus(MemVO memVO);
	public void updateAuth(MemVO memVO);
	public MemVO findByPrimaryKey(String mem_id);
	public List<MemVO> getAll();
	public void readPic(String acc);
	public MemVO findByAcc(String acc);
}
