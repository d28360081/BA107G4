package com.comapl.model;

import java.util.List;

public interface ComAplInterface {
  public ComAplVO insert(ComAplVO comaplvo);
  public void update(ComAplVO comaplvo);
  public void delete(String conaplid);
  public ComAplVO select(String conaplid);
  public List<ComAplVO> selectAll();
  
}
