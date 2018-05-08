package com.emppms.model;

import java.util.List;

public interface EmpPmsInterface{
      public EmpPmsVO insert(EmpPmsVO emppmsvo);
      public void update(EmpPmsVO emppmsvo,String pms_Id);
      public List<EmpPmsVO> selectByPrimaryKey(String empno);
      public List<EmpPmsVO> selectAll();
      public void delete(String empno,String pms_Id);
      public void deleteAll(String empno);
}
