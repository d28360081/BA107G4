package com.articlecommand.model;

import java.util.List;

public interface ArtcomVO_interface {
	public void insert(ArtcomVO art_comVO);
	public void delete(String art_msg_id);
	public void update(ArtcomVO art_comVO);
	public void updatelike(ArtcomVO art_comVO);
	public ArtcomVO findByPrimaryKey(String art_msg_id);
	public List<ArtcomVO> getAll();
	public List<ArtcomVO> getByartid(String art_id);
}
