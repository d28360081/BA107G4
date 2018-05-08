package com.comrf.model;

import java.util.List;

public interface ComRfDAOInterface {
  public ComRfVO selectByPrimaryKey(String comrfid);
  public ComRfVO insert (ComRfVO comrfvo);
  public void delete(String comrfid);
  public void update(ComRfVO comrfvo);
  public List<ComRfVO> selectAll();
}
