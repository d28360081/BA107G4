package com.article.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;







public class StockCodeGenerator implements IdentifierGenerator{
	
	private static Logger log = Logger.getLogger(StockCodeGenerator.class);
	
	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		String prefix="ART";
		Connection con = session.connection();
		try {
			PreparedStatement pstmt = con.prepareStatement("SELECT ART_SEQ.NEXTVAL from dual");
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int id = rs.getInt("nextval");
				String code = prefix+StringUtils.leftPad(""+id,3,'0');
				log.debug("Generated code:"+code);
				return code;
			}
		}catch(SQLException se) {
			log.error(se);
			throw new HibernateException(
              "Unable to generate Stock Code Sequence");
		}
		
		return null;
	}

}
