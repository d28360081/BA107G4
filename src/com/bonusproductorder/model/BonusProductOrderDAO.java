package com.bonusproductorder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bonusorderdetail.model.BonusProductOrderdetailDAO;
import com.bonusorderdetail.model.BonusProductOrderdetailVO;
import com.bonusproduct.model.BonusProductVO;
import com.bonusproduct.model.BonusproService;

public class BonusProductOrderDAO implements BonusProductOrder_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA107G4";
	String passwd = "123";

	private static final String INSERT_STMT = 
		"INSERT INTO Bonus_Product_Order (bns_rec_id,mem_id,sum_prc,rec_dlv_sts,mem_add,mem_ph,mem_name) VALUES ('BPO'||LPAD(to_char(BSN_PRD_ORD_SEQ.NEXTVAL),3,'0'),?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM Bonus_Product_Order order by bns_rec_id desc";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM Bonus_Product_Order where bns_rec_id = ?";
	private static final String GET_BYONEMEM_STMT = 
			"SELECT * FROM Bonus_Product_Order where MEM_ID = ? order by bns_rec_id desc";
	private static final String DELETE = 
		"DELETE FROM Bonus_Product_Order where bns_rec_id = ?";
	private static final String UPDATE = 
		"UPDATE Bonus_Product_Order set sum_prc=?, rec_dlv_sts=?, mem_add=?  ,mem_ph=?,mem_name=? where bns_rec_id = ?";
	private static final String UPDATE_STS = 
			"UPDATE Bonus_Product_Order set rec_dlv_sts=? where bns_rec_id = ?";

	@Override
	public void insert(BonusProductOrderVO bonusProductOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
					
			pstmt.setString(1, bonusProductOrderVO.getMem_id());
			pstmt.setInt(2, bonusProductOrderVO.getSum_prc());
			pstmt.setString(3, bonusProductOrderVO.getRec_dlv_sts());			
			pstmt.setString(4, bonusProductOrderVO.getMem_add());			
			pstmt.setString(5, bonusProductOrderVO.getMem_ph());
			pstmt.setString(6, bonusProductOrderVO.getMem_name());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		
	}

	@Override
	public void update(BonusProductOrderVO bonusProductOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			
			
					
			pstmt.setDouble(1, bonusProductOrderVO.getSum_prc());
			pstmt.setString(2, bonusProductOrderVO.getRec_dlv_sts());
			pstmt.setString(3, bonusProductOrderVO.getMem_add());
			pstmt.setString(4, bonusProductOrderVO.getMem_ph());
			pstmt.setString(5, bonusProductOrderVO.getMem_name());
			pstmt.setString(6, bonusProductOrderVO.getBns_rec_id());

			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(String bns_rec_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bns_rec_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public BonusProductOrderVO findByPrimaryKey(String bns_rec_id) {
		BonusProductOrderVO bonusProductOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, bns_rec_id);

			rs = pstmt.executeQuery();			
			while (rs.next()) {
				// empVo �]�٬� Domain objects
				bonusProductOrderVO = new BonusProductOrderVO();		
				bonusProductOrderVO.setBns_rec_id(rs.getString("bns_rec_id"));
				bonusProductOrderVO.setMem_id(rs.getString("mem_id"));
				bonusProductOrderVO.setPur_date(rs.getDate("pur_date"));
				bonusProductOrderVO.setSum_prc(rs.getInt("sum_prc"));
				bonusProductOrderVO.setRec_dlv_sts(rs.getString("rec_dlv_sts"));
				bonusProductOrderVO.setMem_add(rs.getString("mem_add"));
				bonusProductOrderVO.setMem_ph(rs.getString("mem_ph"));
				bonusProductOrderVO.setMem_name(rs.getString("mem_name"));
				
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return  bonusProductOrderVO;
	}

	@Override
	public List<BonusProductOrderVO> getAll() {
		List<BonusProductOrderVO> list = new ArrayList<BonusProductOrderVO>();
		BonusProductOrderVO bonusProductOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				bonusProductOrderVO = new BonusProductOrderVO();		
				bonusProductOrderVO.setBns_rec_id(rs.getString("bns_rec_id"));
				bonusProductOrderVO.setMem_id(rs.getString("mem_id"));
				bonusProductOrderVO.setPur_date(rs.getDate("pur_date"));
				bonusProductOrderVO.setSum_prc(rs.getInt("sum_prc"));
				bonusProductOrderVO.setRec_dlv_sts(rs.getString("rec_dlv_sts"));
				bonusProductOrderVO.setMem_add(rs.getString("mem_add"));
				bonusProductOrderVO.setMem_ph(rs.getString("mem_ph"));
				bonusProductOrderVO.setMem_name(rs.getString("mem_name"));
				list.add(bonusProductOrderVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public void insert2(BonusProductOrderVO bonusProductOrderVO, List<BonusProductOrderdetailVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			String cols[] = {"bns_rec_id"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setString(1, bonusProductOrderVO.getMem_id());
			pstmt.setInt(2, bonusProductOrderVO.getSum_prc());
			pstmt.setString(3, bonusProductOrderVO.getRec_dlv_sts());			
			pstmt.setString(4, bonusProductOrderVO.getMem_add());			
			pstmt.setString(5, bonusProductOrderVO.getMem_ph());
			pstmt.setString(6, bonusProductOrderVO.getMem_name());
			pstmt.executeUpdate();
			
			String next_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				next_id = rs.getString(1);
				System.out.println(next_id);
			}else{
				System.out.println("noGeneratedKeys");
			}
			rs.close();
			BonusProductOrderdetailDAO dao = new BonusProductOrderdetailDAO();
			BonusProductVO bnsVO = new BonusProductVO();
			String bns_it_id = bnsVO.getBns_it_id();
			for(BonusProductOrderdetailVO bnsordelVO : list){
				bnsordelVO.setBns_rec_id(next_id);
				bnsordelVO.setBns_it_id(bns_it_id);
				dao.insert2(bnsordelVO, con);
			}
			
			con.commit();
			con.setAutoCommit(true);

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void insert3(BonusProductOrderVO bonusProductOrderVO, HashMap<String, BonusProductVO> BNSDetailMap) {
		Connection con = null;
		PreparedStatement pstmt = null;
		HashMap<String, BonusProductOrderVO> map = new HashMap<String, BonusProductOrderVO>();
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			String cols[] = { "bns_rec_id" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			ResultSet rs = null;
				for(String s :map.keySet()){
					bonusProductOrderVO = map.get(s);
					pstmt.setString(1, bonusProductOrderVO.getMem_id());
					pstmt.setInt(2, bonusProductOrderVO.getSum_prc());
					pstmt.setString(3, bonusProductOrderVO.getRec_dlv_sts());			
					pstmt.setString(4, bonusProductOrderVO.getMem_add());			
					pstmt.setString(5, bonusProductOrderVO.getMem_ph());
					pstmt.setString(6, bonusProductOrderVO.getMem_name());
					pstmt.executeUpdate();
					
					rs = pstmt.getGeneratedKeys();
					String next_id = null;
				
				if(rs.next()){
					next_id = rs.getString(1);
					bonusProductOrderVO.setBns_rec_id(next_id);
					System.out.println(next_id);
				}
				rs.close();
				}
				BonusproService bnsSrc = new BonusproService();
				for(String s : BNSDetailMap.keySet()){
					BonusProductVO bnsVO = BNSDetailMap.get(s);
					for(String s2:map.keySet()){
						BonusProductOrderVO boo = map.get(s2);
						BonusProductOrderdetailVO orderdetail = new BonusProductOrderdetailVO();
						orderdetail.setBns_rec_id(boo.getBns_rec_id());
						orderdetail.setBns_it_id(bnsVO.getBns_it_id());
						orderdetail.setIt_num(1);
						
						bnsSrc.addDetail(orderdetail, con);
					}
				}
				con.commit();
				con.setAutoCommit(true);
			
		} catch (Exception se) {
			se.printStackTrace();
			if (con != null) {
				try {
					con.rollback();
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-storeOrder");
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}

			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void insert5(BonusProductOrderVO bonusProductOrderVO, String BNS_IT_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			String cols[] = {"bns_rec_id"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			pstmt.setString(1, bonusProductOrderVO.getMem_id());
			pstmt.setInt(2, bonusProductOrderVO.getSum_prc());
			pstmt.setString(3, bonusProductOrderVO.getRec_dlv_sts());			
			pstmt.setString(4, bonusProductOrderVO.getMem_add());			
			pstmt.setString(5, bonusProductOrderVO.getMem_ph());
			pstmt.setString(6, bonusProductOrderVO.getMem_name());
			pstmt.executeUpdate();
			
			String next_id = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				next_id = rs.getString(1);
				System.out.println(next_id);
			}else{
				System.out.println("noGeneratedKeys");
			}
			rs.close();
			BonusProductOrderdetailDAO dao = new BonusProductOrderdetailDAO();
			
			BonusProductOrderdetailVO bnsodVO = new BonusProductOrderdetailVO();
			
			
			bnsodVO.setBns_rec_id(next_id);
			bnsodVO.setBns_it_id(BNS_IT_ID);
			bnsodVO.setIt_num(1);
			dao.insert2(bnsodVO,con);
			
			con.commit();
			con.setAutoCommit(true);

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		
	}

	@Override
	public List<BonusProductOrderVO> findByMem_id(String mem_id) {
		BonusProductOrderVO bonusProductOrderVO = null;
		List<BonusProductOrderVO> list =  new ArrayList<BonusProductOrderVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BYONEMEM_STMT);

			pstmt.setString(1, mem_id);

			rs = pstmt.executeQuery();			
			while (rs.next()) {
				// empVO �]�٬� Domain objects
				bonusProductOrderVO = new BonusProductOrderVO();		
				bonusProductOrderVO.setBns_rec_id(rs.getString("bns_rec_id"));
				bonusProductOrderVO.setMem_id(rs.getString("mem_id"));
				bonusProductOrderVO.setPur_date(rs.getDate("pur_date"));
				bonusProductOrderVO.setSum_prc(rs.getInt("sum_prc"));
				bonusProductOrderVO.setRec_dlv_sts(rs.getString("rec_dlv_sts"));
				bonusProductOrderVO.setMem_add(rs.getString("mem_add"));
				bonusProductOrderVO.setMem_ph(rs.getString("mem_ph"));
				bonusProductOrderVO.setMem_name(rs.getString("mem_name"));
				list.add(bonusProductOrderVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return  list;
	}

	@Override
	public void updateSts(BonusProductOrderVO bonusProductOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STS);
			pstmt.setString(1, bonusProductOrderVO.getRec_dlv_sts());
			pstmt.setString(2, bonusProductOrderVO.getBns_rec_id());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

}
