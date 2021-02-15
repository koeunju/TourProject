package common.model;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class DAOMyBatisBase {

    protected SqlSession ses;

    public SqlSessionFactory getSessionFactory() {

        String resource = "common/config/mybatis-config.xml";
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream(resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        return factory;
    }

    public void close() {
        if(ses != null) ses.close();
    }
}
