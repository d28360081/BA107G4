package com.article.model;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import hibernate.util.HibernateUtil;

public class ArticleHibernateDAO implements ArticleVO_h_interface{

	@Override
	public void insert(ArticleVO_Hibernate articleVO_h) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(articleVO_h);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(ArticleVO_Hibernate articleVO_h) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(articleVO_h);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public void delete(String art_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

//        【此時多方(宜)可採用HQL刪除】
//			Query query = session.createQuery("delete ArticleVO_Hibernate where art_id=?");
//			query.setParameter(0, art_id);
//			System.out.println("刪除的筆數=" + query.executeUpdate());

//        【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
			ArticleVO_Hibernate articleVO_h = new ArticleVO_Hibernate();
			articleVO_h.setArt_id(art_id);;
			session.delete(art_id);

//        【此時多方不可(不宜)採用cascade聯級刪除】
//        【多方Article.hbm.xml如果設為 cascade="all"或 cascade="delete"將會刪除所有相關資料-包括所屬部門與同部門的其它員工將會一併被刪除】
//			EmpVO empVO = (EmpVO) session.get(EmpVO.class, empno);
//			session.delete(empVO);

			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void updateLike(ArticleVO_Hibernate articleVO_h) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("update ArticleVO_Hibernate set like_num=? where art_id=?");
			query.setParameter(0, articleVO_h.getLike_num());
			query.setParameter(1, articleVO_h.getArt_id());
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public void updatestatus(ArticleVO_Hibernate articleVO_h) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createSQLQuery("UPDATE ARTICLE set art_sts=? Where art_id=?");
			query.setParameter(0, articleVO_h.getArt_sts());
			query.setParameter(1, articleVO_h.getArt_id());
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		
	}

	@Override
	public ArticleVO_Hibernate findByPrimaryKey(String art_id) {
		ArticleVO_Hibernate artVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			artVO = (ArticleVO_Hibernate)session.get(ArticleVO_Hibernate.class, art_id);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return artVO;
	}

	@Override
	public List<ArticleVO_Hibernate> getAll() {
		List<ArticleVO_Hibernate> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ArticleVO_Hibernate where art_sts='正常' order by art_id desc");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<ArticleVO_Hibernate> findBymem(String mem_id) {
		List<ArticleVO_Hibernate> list = null;
		ArticleVO_Hibernate artVO = new ArticleVO_Hibernate();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from ArticleVO_Hibernate where mem_id=? order by art_id desc");
			query.setParameter(0, mem_id);
			list=query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	public static void main(String[]args){
		com.member.model.MemVO memVO = new com.member.model.MemVO();
//		memVO.setMem_id("M000002");
		ArticleHibernateDAO dao = new ArticleHibernateDAO();
		ArticleVO_Hibernate artVO = new ArticleVO_Hibernate();
//		artVO.setMemVO(memVO);
//		artVO.setArt_tlt("我居然成功用出來了");
//		artVO.setArt_cnt("RRRRRR2RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR工具類別猛RRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
//		artVO.setArt_date(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
//		dao.insert(artVO);
//		ArticleVO_Hibernate artVO = new ArticleVO_Hibernate();
//		artVO.setMemVO(memVO);
//		artVO.setArt_tlt("RRRRRRRRRR");
//		artVO.setArt_cnt("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
//		artVO.setArt_date(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
//		artVO.setArt_id("ART021");
//		artVO.setArt_sts("下架");
//		dao.update(artVO);
//		artVO.setArt_id("ART020");
//		artVO.setLike_num(5);
//		dao.updateLike(artVO);
//		artVO.setArt_id("ART005");
//		artVO.setArt_sts("下架");
//		dao.updatestatus(artVO);
//		ArticleVO_Hibernate art = dao.findByPrimaryKey("ART001");
//		System.out.print(art.getArt_id()+",");
//		System.out.print(art.getMemVO().getMem_id()+",");
//		System.out.print(art.getMemVO().getAcc()+",");
//		System.out.print(art.getArt_tlt()+",");
//		System.out.println("");
//		System.out.println(art.getArt_cnt()+"");
//		System.out.print(art.getArt_date()+",");
//		System.out.print(art.getLike_num()+",");
//		System.out.print(art.getArt_sts());
		List<ArticleVO_Hibernate> list = dao.getAll();
		for(ArticleVO_Hibernate art : list){
			System.out.print(art.getArt_id()+",");
			System.out.print(art.getMemVO().getMem_id()+",");
			System.out.print(art.getMemVO().getAcc()+",");
			System.out.print(art.getArt_tlt()+",");
			System.out.println("");
			System.out.println(art.getArt_cnt()+"");
			System.out.print(art.getArt_date()+",");
			System.out.print(art.getLike_num()+",");
			System.out.print(art.getArt_sts());
			System.out.println("=========================");
		}
//		List<ArticleVO_Hibernate> list = dao.searchArt("八卦");
//		for(ArticleVO_Hibernate art : list){
//			System.out.print(art.getArt_id()+",");
//			System.out.print(art.getMemVO().getMem_id()+",");
//			System.out.print(art.getMemVO().getAcc()+",");
//			System.out.print(art.getArt_tlt()+",");
//			System.out.println("");
//			System.out.println(art.getArt_cnt()+"");
//			System.out.print(art.getArt_date()+",");
//			System.out.print(art.getLike_num()+",");
//			System.out.print(art.getArt_sts());
//			System.out.println("");
//			System.out.println("=========================");
//		}
	}

	@Override
	public List<ArticleVO_Hibernate> searchArt(String value) {
		List<ArticleVO_Hibernate> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Criteria query = session.createCriteria(ArticleVO_Hibernate.class);
			query.add(Restrictions.like("art_tlt", "%"+value+"%"));
			query.addOrder(Order.desc("art_id"));
			list=query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

}
