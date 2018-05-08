package android.com.comnof.model;

import java.util.List;

public interface ComNofInterface {
 public ComNofVO select(String nof_id);
 public void delete(String nof_id);
 public ComNofVO insert(ComNofVO comnofvo);
 public List<ComNofVO> selectAll(String mem_id);
 public void update(ComNofVO comnofvo);
 public List<ComNofVO> selectCom_Nof(String com_id);
}
