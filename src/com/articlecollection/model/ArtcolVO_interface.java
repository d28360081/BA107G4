package com.articlecollection.model;

import java.util.List;

public interface ArtcolVO_interface {
	public void insert(ArtcolVO art_colVO);
	public void delete(String mem_id , String art_id);
	public ArtcolVO findByPrimaryKey(String mem_id);
	public List<ArtcolVO> getAll();
	public List<ArtcolVO> findallByMem(String mem_id);
}
