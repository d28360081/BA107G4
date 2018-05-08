package com.emppmscls.model;

import java.util.List;

public interface EmpPmsClsDAOInterface {
   public EmpPmsClsVO select(String pmsid);
   public EmpPmsClsVO insert(EmpPmsClsVO emppmsclsvo);
   public void delete(String pmsid);
   public void update(EmpPmsClsVO emppmsclsvo);
   public List<EmpPmsClsVO> selectall();
}
