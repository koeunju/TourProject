package common.pool;

import java.util.*;
import java.sql.*;

public class ConnectionPoolBean {

    private Hashtable<Connection, Boolean> ht;
    private String url, user, pwd;
    private int increment = 3; // 증가치

    public ConnectionPoolBean() throws ClassNotFoundException, SQLException {
        ht = new Hashtable<>();
        // 커넥션을 저장할 자료구조
        Class.forName("oracle.jdbc.driver.OracleDriver");
        url = "jdbc:oracle:thin:@192.168.219.105:1521:XE";
        user = "t4er";
        pwd = "t4er";
        // 커넥션을 미리 5개 생성해서 ht에 저장해 놓자
        Connection con = null;
        for (int i = 0; i < 5; i++) {
            con = DriverManager.getConnection(url, user, pwd);
            ht.put(con, Boolean.FALSE); // FALSE를 VALUE로 저장. 일하지 않는다는 으미
        }
        System.out.println("ConnectionPoolBean created");
    }

    public synchronized Connection getConnection() throws SQLException {
        Enumeration<Connection> en = ht.keys(); // key값들을 Enumeration 형태로 추출
        while (en.hasMoreElements()) {
            Connection con = en.nextElement();
            Boolean b = ht.get(con);
            if (b == Boolean.FALSE) {
                ht.put(con, Boolean.TRUE); // 일허라 간단 의미로 TRUE로 마킹
                return con;
            }
        }
        // 놀고있는 con이 하나도 없다면 증가치만큼 커넥션을 만들어 놓자
        for (int i = 0; i < increment; i++) {
            Connection con2 = DriverManager.getConnection(url, user, pwd);
            ht.put(con2, Boolean.FALSE);
        }
        return getConnection(); // 재귀 호출
    }

    public void returnConnection(Connection returnCon) throws SQLException {
        Set<Connection> set = ht.keySet(); // key값들을 반환
        for (Connection con : set) {
            if (con == returnCon) {
                // 반환되는 커넥션과 주소값이 같다면.. Flase로 표시해주자
                ht.put(con, Boolean.FALSE);
                break;
            }
        }
        removeCon();
    }

    private void removeCon() throws SQLException {
        Enumeration<Connection> en = ht.keys();
        int count = 0; // false인 con의 개수를 담을 변수
        while (en.hasMoreElements()) {
            Connection con = en.nextElement();
            Boolean b = ht.get(con);
            if (!b) {
                count++;
                if (count > 5) {
                    // false인 con이 5개 이상이라면 ht에서 제거한 뒤에
                    // con을 close()한다
                    ht.remove(con);
                    con.close();
                }
            }
        }
    }

    public void closeAll() {
        Set<Connection> set = ht.keySet();
        for (Connection con : set) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("closeAll()예외: " + e);
            }
        }
    }
}
