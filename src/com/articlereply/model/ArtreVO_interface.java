package com.articlereply.model;

import java.util.List;

public interface ArtreVO_interface {
	public void insert(ArtreVO art_reVO);
	public void update(ArtreVO art_reVO);
	public void updatests(ArtreVO art_reVO);
	public void delete(String re_id);
	public ArtreVO findByPrimaryKey(String re_id);
	public List<ArtreVO> getAll();
	public List<ArtreVO> getByMem(String Mem_id);
	public List<ArtreVO> getByArt_id(String art_id);
}
