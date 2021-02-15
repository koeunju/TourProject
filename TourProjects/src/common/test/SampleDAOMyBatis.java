package common.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class SampleDAOMyBatis {

    // 네임스페이스 SamlpeMapper.xml에 기술된 네임스페이스 속성값을 갖도록
    private final String NS = "common.mapper.SampleMapper";

    // SqlSession 객체를 얻기 위해서 SqlSessionFactory를 얻어오자.
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

    /* t4er계정의 테이블 수를 반환하는 메소드 */
    public int getTableCount() {
        SqlSession ses = this.getSessionFactory().openSession();
        int count = ses.selectOne(NS + ".totalCount");
        if(ses != null) ses.close();
        return count;
    }
}
