package com.cocmt.model;

import java.util.List;

public interface ComComtDAOInterface {
    public ComComtVO selectByPrimaryKey(String comid,String comtid);
    public ComComtVO insert(ComComtVO comcomtvo);
    public void delete(String comid,String comtid);
    public void update(ComComtVO comcomtvo);
    public List<ComComtVO> selectAll(String comid);
    
}
